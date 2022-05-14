package com.demo.repaymentPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/15
 * Time: 0:04
 * Created with IntelliJ IDEA
 * Description: 信用卡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCard {
    /**
     * 卡号
     */
    private String cardId;
    /**
     * 总额度
     */
    private BigDecimal totalAmount;
    /**
     * 已用额度
     */
    private BigDecimal usedAmount;
    /**
     * 还款日
     */
    private LocalDate repaymentDate;
}
