package com.kakaopay.history.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 계좌정보
 */
@Table(name = "account_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 계좌 번호
     */
    @Column(name = "account_no")
    private String acctNo;

    /**
     * 계좌 명
     */
    @Column(name = "account_name")
    private String acctNm;

    /**
     * 걔좌별 지점
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Builder
    public Account(String acctNo, String acctNm, Branch branch) {
        this.acctNo = acctNo;
        this.acctNm = acctNm;
        this.branch = branch;
    }

}
