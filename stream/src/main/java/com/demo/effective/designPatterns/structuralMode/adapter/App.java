package com.demo.effective.designPatterns.structuralMode.adapter;

import lombok.var;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/24
 * Time: 13:56
 * Description: 适配器模式
 */
public class App {
    private App() {
    }

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(final String[] args) {
        // The captain can only operate rowing boats but with adapter he is able to
        // use fishing boats as well
        var captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
