package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:39
 * Description: No Description
 */
@Slf4j
public class App implements Runnable{
    private final Kingdom kingdom = new Kingdom();
    public Kingdom getKingdom() {
        return kingdom;
    }
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        var app = new App();
        app.run();
    }

    @Override
    public void run() {
        log.info("elf kingdom");
        kingdom.createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());

        log.info("orc kingdom");
        kingdom.createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());
    }

}
