package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:21
 * Description: ElfKing
 */
public class ElfKing implements King{
    static final String DESCRIPTION = "This is the elven king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
