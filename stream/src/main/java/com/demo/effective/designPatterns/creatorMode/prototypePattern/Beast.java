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
public abstract class Beast implements Prototype{
    public Beast(Beast source) {
    }

    @Override
    public abstract Beast copy();
}
