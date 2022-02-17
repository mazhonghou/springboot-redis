package com.mazh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mazh
 * @date 2022/2/15
 * @description 抽奖控制器
 */

@RestController
public class LuckyController {

    @Autowired
    private LuckyService luckyService;

    /**
     * 抽取一等奖
     * @return
     */
    @GetMapping("/first")
    public Object first() {
        return this.luckyService.firstPrize();
    }

    /**
     * 抽取二等奖
     * @return
     */
    @GetMapping("/second")
    public Object second() {
        return this.luckyService.secondPrize();
    }

    /**
     * 抽取三等奖
     * @return
     */
    @GetMapping("/third")
    public Object third() {
        return this.luckyService.thirdPrize();
    }

    /**
     * 重置抽奖结果
     * @return
     */
    @GetMapping("/reset")
    public Object reset() {
        return this.luckyService.reset();
    }

    /**
     * 抽奖结果和奖品等相关信息查看
     * @return
     */
    @GetMapping("/complete")
    public Object complete() {
        return this.luckyService.complete();
    }

    /**
     * 自动完成抽奖操作
     * @return
     */
    @GetMapping("/auto")
    public Object auto() {
        return this.luckyService.auto();
    }

}
