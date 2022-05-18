package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:19
 * Description: ElfKingdomFactory concrete factory.
 */
public class ElfKingdomFactory implements KingdomFactory{
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }

}
