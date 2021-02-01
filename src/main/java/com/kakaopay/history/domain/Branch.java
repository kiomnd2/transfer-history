package com.kakaopay.history.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 관리점
 */
@Table(name = "branch_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Branch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 관리점 코드
     */
    @Column(name = "branch_code")
    private String brCode;

    /**
     * 관리지점명
     */
    @Column(name = "branch_name")
    private String brName;

    @Builder
    public Branch(String brCode, String brName) {
        this.brCode = brCode;
        this.brName = brName;
    }


}
