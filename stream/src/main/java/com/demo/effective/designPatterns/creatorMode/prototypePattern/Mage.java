package com.demo.effective.designPatterns.creatorMode.prototypePattern;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 15:05
 * Description: No Description
 */
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Mage implements Prototype{
    public Mage(Mage source) {
    }

    @Override
    public abstract Mage copy();
}
