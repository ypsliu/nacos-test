package com.demo.effective.designPatterns.creatorMode.abstractFactoryPattern;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/17
 * Time: 13:36
 * Description: The factory of kingdom factories.
 */
@Data
public class Kingdom {
    private King king;
    private Castle castle;
    private Army army;

    public static class FactoryMaker{
        /**
         * Enumeration for the different types of Kingdoms.
         */
        public enum KingdomType {
            ELF, ORC
        }

        /**
         * The factory method to create KingdomFactory concrete objects.
         */
        public static KingdomFactory makeFactory(KingdomType type) {
            switch (type) {
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType not supported.");
            }
        }
    }

    /**
     * Creates kingdom.
     * @param kingdomType type of Kingdom
     */
    public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
        final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
        this.setKing(kingdomFactory.createKing());
        this.setCastle(kingdomFactory.createCastle());
        this.setArmy(kingdomFactory.createArmy());
    }
}
