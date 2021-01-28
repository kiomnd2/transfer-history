package com.kakaopay.history.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 거래내역
 */

@Table(name = "transfer_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 거래 일자
     */
    @Column(name = "transfer_date")
    private LocalDate trDate;

    /**
     * 계좌번호
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    /**
     * 거래번호
     */
    @Column(name = "transfer_no")
    private String trNo;

    /**
     * 금액
     */
    @Column(name = "amount")
    private BigDecimal amt;

    /**
     * 수수료
     */
    @Column(name = "fee")
    private BigDecimal fee;

    /**
     * 취소 여부
     */
    @Column(name = "cancel")
    private boolean isCnl;

    public void setAccount(Account account) {
        this.account = account;
    }

    @Builder
    public History(LocalDate trDate, String trNo, BigDecimal amt, BigDecimal fee, boolean isCnl) {
        this.trDate = trDate;
        this.trNo = trNo;
        this.amt = amt;
        this.fee = fee;
        this.isCnl = isCnl;
    }
}
