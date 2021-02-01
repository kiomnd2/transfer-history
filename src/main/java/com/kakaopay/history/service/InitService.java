package com.kakaopay.history.service;

import com.kakaopay.history.domain.Account;
import com.kakaopay.history.domain.Branch;
import com.kakaopay.history.domain.History;
import com.kakaopay.history.repository.account.AccountRepository;
import com.kakaopay.history.repository.branch.BranchRepository;
import com.kakaopay.history.repository.history.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class InitService {

    private final AccountRepository accountRepository;

    private final BranchRepository branchRepository;

    private final HistoryRepository historyRepository;

    /**
     * 모든 csv데이터를 초기화 합니다.
     * @throws IOException
     */
    @Transactional
    @PostConstruct
    public void initTransferData() throws IOException {

        saveBranchInfo();

        saveAccountInfo();

        collectHistoryInfo();

    }

    protected void collectHistoryInfo() throws IOException {

        Resource historyResource = new ClassPathResource("데이터_거래내역.csv");

        List<History> historyList = Files.readAllLines(historyResource.getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .skip(1)
                .map(line -> {
                    String[] splits = line.split(",");
                    //거래일자,계좌번호,거래번호,금액,수수료,취소여부
                    Account acc = accountRepository.findByAcctNo(splits[1])
                            .orElseThrow(RuntimeException::new);// 계좌번호

                    return History.builder()
                            .trDate(LocalDate.parse(splits[0], DateTimeFormatter.ofPattern("yyyyMMdd")))
                            .trNo(splits[2])
                            .amt(new BigDecimal(splits[3]))
                            .account(acc)
                            .fee(new BigDecimal(splits[4]))
                            .isCnl((splits[5].equals("Y")))
                            .build();
                }).collect(Collectors.toList());
        historyRepository.saveAll(historyList);
    }

    protected void saveAccountInfo() throws IOException {

        Resource accountResource = new ClassPathResource("데이터_계좌정보.csv");

        List<Account> accountList = Files.readAllLines(accountResource.getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .skip(1)
                .map(line -> {
                    String[] splits = line.split(",");
                    Branch branch = branchRepository.findByBrCode(splits[2])
                            .orElseThrow(RuntimeException::new);

                    return Account.builder()
                            .acctNo(splits[0])
                            .acctNm(splits[1])
                            .branch(branch) // 지점이 없다면 오류
                            .build();
                }).collect(Collectors.toList());

        accountRepository.saveAll(accountList);
    }


    protected void saveBranchInfo() throws IOException {
        Resource branchResource  = new ClassPathResource("데이터_관리점정보.csv");

        List<Branch> branchList = Files.readAllLines(branchResource.getFile().toPath(), StandardCharsets.UTF_8)
                .stream()
                .skip(1)
                .map(line -> {
                    String[] splits = line.split(",");

                    return Branch.builder()
                            .brCode(splits[0])
                            .brName(splits[1])
                            .build();
                }).collect(Collectors.toList());

        branchRepository.saveAll(branchList);
    }
}
