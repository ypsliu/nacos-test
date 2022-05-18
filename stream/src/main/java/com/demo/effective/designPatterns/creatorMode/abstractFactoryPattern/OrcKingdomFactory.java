package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:22
 * Description: OrcKingdomFactory concrete factory
 */
public class OrcKingdomFactory implements KingdomFactory{
    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}
