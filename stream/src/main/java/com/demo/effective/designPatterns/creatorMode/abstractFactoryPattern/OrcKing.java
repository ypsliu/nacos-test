package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:23
 * Description: OrcKing.
 */
public class OrcKing implements King{
    static final String DESCRIPTION = "This is the orc king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
