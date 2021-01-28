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
    /**
     * 계좌 번호
     */
    @Id
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private final List<History> historyList = new ArrayList<>();

    @Builder
    public Account(String acctNo, String acctNm, Branch branch) {
        this.acctNo = acctNo;
        this.acctNm = acctNm;
        this.branch = branch;
    }

    public void addHistory(History history) {
        history.setAccount(this);
        this.historyList.add(history);
    }
}
