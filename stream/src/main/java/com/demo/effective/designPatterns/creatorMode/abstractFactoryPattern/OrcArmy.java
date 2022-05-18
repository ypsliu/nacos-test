package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:23
 * Description: OrcArmy
 */
public class OrcArmy implements Army{
    static final String DESCRIPTION = "This is the orc army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
