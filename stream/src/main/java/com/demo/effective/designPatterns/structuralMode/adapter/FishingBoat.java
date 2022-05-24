package com.demo.effective.designPatterns.structuralMode.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/24
 * Time: 13:53
 * Description: Device class (adaptee in the pattern). We want to reuse this class. Fishing boat moves by
 * sailing.
 */
@Slf4j
final class FishingBoat {
    void sail() {
        log.info("The fishing boat is sailing");
    }
}
