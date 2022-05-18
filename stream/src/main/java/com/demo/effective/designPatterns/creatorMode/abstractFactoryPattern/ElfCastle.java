package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:20
 * Description: ElfCastle
 */
public class ElfCastle implements Castle{
    static final String DESCRIPTION = "This is the elven castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
