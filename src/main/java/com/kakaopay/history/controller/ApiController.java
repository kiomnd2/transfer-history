package com.kakaopay.history.controller;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.dto.BranchListDto;
import com.kakaopay.history.exception.BranchCodeNotFoundException;
import com.kakaopay.history.service.InquireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final InquireService inquireService;


    @PostMapping(value = "/api/inquire/amount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AmountDto>>> inquireSumAmount() {

        List<AmountDto> sumAmount = inquireService.getMostAmountAccount(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(sumAmount));
    }

    @PostMapping(value = "/api/inquire/no/deal/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AccountDto>>> inquireNoTransUser() {

        List<AccountDto> noTransUser = inquireService.getNoTransUser(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(noTransUser));
    }


    @PostMapping(value = "/api/inquire/branch/list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BranchListDto>>> inquireBranchAmountList() {

        List<BranchListDto> branchAmount = inquireService.getBranchAmount();

        return ResponseEntity.ok().body(ApiResponse.success(branchAmount));
    }

    @PostMapping(value = "/api/inquire/branch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<BranchDto>> inquireBranchAmount(@RequestBody @Valid ApiRequest apiRequest) {

        if ("B".equals(apiRequest.getBrCode())) {
            throw new BranchCodeNotFoundException();
        }

        BranchDto branch = inquireService.getBranch(apiRequest.getBrCode());

        return ResponseEntity.ok().body(ApiResponse.success(branch));
    }
}
