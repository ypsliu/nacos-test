package com.demo.repaymentPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/15
 * Time: 0:04
 * Created with IntelliJ IDEA
 * Description: 储蓄卡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebitCard {
    /**
     * 卡号
     */
    private String cardId;
}
