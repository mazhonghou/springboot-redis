package com.mazh;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class Prize {

    private Map<String, Object> prizeInfo;
    private String firstOwner;
    private Set<String> secondOwner;
    private Set<String> thirdOwner;
    private Set<String> unOwner;
    private String allStaff;

    @Override
    public String toString() {
        return
                "奖品信息:" + prizeInfo +
                        "\n 参与抽奖者:" + allStaff +
                        "\n 一等奖获得者:" + firstOwner +
                        "\n 二等奖获得者:" + secondOwner +
                        "\n 三等奖获得者:" + thirdOwner +
                        "\n 未获奖人员:" + unOwner;
    }
}
