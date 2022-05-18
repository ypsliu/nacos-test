package com.demo.effective.designPatterns.creatorMode.builderPattern;

import lombok.AllArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 14:25
 * Description: No Description
 */
@AllArgsConstructor
public enum Armor {
    CLOTHES("clothes"),
    LEATHER("leather"),
    CHAIN_MAIL("chain mail"),
    PLATE_MAIL("plate mail");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
