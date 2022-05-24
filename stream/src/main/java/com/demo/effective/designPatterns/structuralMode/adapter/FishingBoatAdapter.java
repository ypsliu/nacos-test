package com.demo.effective.designPatterns.structuralMode.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/24
 * Time: 13:55
 * Description: Adapter class. Adapts the interface of the device ({@link FishingBoat}) into {@link RowingBoat}
 * interface expected by the client ({@link Captain}).
 */
public class FishingBoatAdapter implements RowingBoat{
    private final FishingBoat boat = new FishingBoat();
    @Override
    public void row() {
        boat.sail();
    }
}
