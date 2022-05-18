package com.demo.effective.designPatterns.creatorMode.builderPattern;

import lombok.AllArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 14:26
 * Description: No Description
 */
@AllArgsConstructor
public enum HairType {
    BALD("bald"),
    SHORT("short"),
    CURLY("curly"),
    LONG_STRAIGHT("long straight"),
    LONG_CURLY("long curly");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
