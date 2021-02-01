package com.kakaopay.history.controller;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.dto.BranchListDto;
import com.kakaopay.history.service.InquireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final InquireService inquireService;

    @GetMapping(value = "/api/inquire/most-amount"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AmountDto>>> inquireSumAmount() {

        List<AmountDto> sumAmount = inquireService.getMostAmountAccount(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(sumAmount));
    }

    @GetMapping(value = "/api/inquire/no-tran"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AccountDto>>> inquireNoTransUser() {

        List<AccountDto> noTransUser = inquireService.getNoTransUser(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(noTransUser));
    }

    @GetMapping(value = "/api/inquire/branch"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BranchListDto>>> inquireBranchAmount() {

        List<BranchListDto> branchAmount = inquireService.getBranchAmount();

        return ResponseEntity.ok().body(ApiResponse.success(branchAmount));
    }

}
