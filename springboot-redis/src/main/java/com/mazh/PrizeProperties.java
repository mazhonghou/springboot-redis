package com.mazh;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author mazh
 * @date 2022/2/15
 * @description 读取信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "prize")
@PropertySource(value = "classpath:prize.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
public class PrizeProperties {

    private String firstPrize;

    private int firstNumber;

    private String secondPrize;

    private int secondNumber;

    private String thirdPrize;

    private int thirdNumber;

    private String joinStaff;

    public Set<String> acquireJoinStaff() {
        return StringUtils.commaDelimitedListToSet(this.joinStaff);
    }

}
