package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:13
 * Description: KingdomFactory factory interface.
 */
public interface KingdomFactory {
    Castle createCastle();

    King createKing();

    Army createArmy();
}
