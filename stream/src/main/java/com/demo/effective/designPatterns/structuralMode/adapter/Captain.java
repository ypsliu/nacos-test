package com.demo.effective.designPatterns.structuralMode.adapter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/24
 * Time: 13:52
 * Description: The Captain uses {@link RowingBoat} to sail. <br> This is the client in the pattern.
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Captain {
    private RowingBoat rowingBoat;

    void row() {
        rowingBoat.row();
    }
}
