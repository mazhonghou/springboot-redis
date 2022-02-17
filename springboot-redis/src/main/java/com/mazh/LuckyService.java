package com.mazh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mazh
 * @date 2022/2/15
 * @description 抽奖实现类
 */

@Service
public class LuckyService {

    @Autowired
    private PrizeProperties prizeProperties;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 记录已获奖人员
     */
    private final String key1 = "owner1";
    private final String key2 = "owner2";
    private final String key3 = "owner3";
    private final String key = "prise";

    @PostConstruct
    private void initStaff() {
        this.redisUtil.delete(key1);
        this.redisUtil.delete(key2);
        this.redisUtil.delete(key3);
        this.redisUtil.delete(key);
        Set<String> set = prizeProperties.acquireJoinStaff();
        this.redisUtil.sadd(key, set.toArray(new String[set.size()]));
    }

    public Object firstPrize() {
        boolean exist = this.redisUtil.isExist(key1);
        if (exist) {
            return "一等奖已抽奖完毕，获奖者 '" + this.redisUtil.get(key1) + "'";
        }
        Object spop = this.redisUtil.spop(key);
        this.redisUtil.set(key1, String.valueOf(spop));
        return spop;
    }

    public Object secondPrize() {
        Set smembers = this.redisUtil.smembers(key2);
        if (smembers.size() == this.prizeProperties.getSecondNumber()) {
            return "二等奖已抽奖完毕，获奖者 '" + smembers + "'";
        }
        Object spop = this.redisUtil.spop(key);
        this.redisUtil.sadd(key2, String.valueOf(spop));
        return spop;
    }

    public Object thirdPrize() {
        Set smembers = this.redisUtil.smembers(key3);
        if (smembers.size() == this.prizeProperties.getThirdNumber()) {
            return "三等奖已抽奖完毕，获奖者 '" + smembers + "'";
        }
        Object spop = this.redisUtil.spop(key);
        this.redisUtil.sadd(key3, String.valueOf(spop));
        return spop;
    }

    public Object reset() {
        this.initStaff();
        return "重置成功";
    }

    public Object complete() {
        Prize prize = new Prize();
        Map<String, Object> prizeMap = new LinkedHashMap<>();
        prizeMap.put("一等奖", this.prizeProperties.getFirstPrize() + "*" + this.prizeProperties.getFirstNumber());
        prizeMap.put("二等奖", this.prizeProperties.getSecondPrize() + "*" + this.prizeProperties.getSecondNumber());
        prizeMap.put("三等奖", this.prizeProperties.getThirdPrize() + "*" + this.prizeProperties.getThirdNumber());
        prize.setPrizeInfo(prizeMap);
        prize.setFirstOwner(String.valueOf(this.redisUtil.get(key1)));
        prize.setSecondOwner(this.redisUtil.smembers(key2));
        prize.setThirdOwner(this.redisUtil.smembers(key3));
        prize.setUnOwner(this.redisUtil.smembers(key));
        prize.setAllStaff(this.prizeProperties.getJoinStaff());
        System.out.println(prize);
        return prize;
    }

    public Object auto() {
        this.reset();
        this.firstPrize();
        for (int i = 0; i < this.prizeProperties.getSecondNumber(); i++) {
            this.secondPrize();
        }
        for (int i = 0; i < this.prizeProperties.getThirdNumber(); i++) {
            this.thirdPrize();
        }
        return complete();
    }
}
