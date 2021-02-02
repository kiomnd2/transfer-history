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


    /**
     * 연도별 합계 금액이 가장 많은 고객을 추출합니다
     * @return 고객 거래금액 합산 리스트 정보
     */
    @PostMapping(value = "/api/inquire/amount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AmountDto>>> inquireSumAmount() {

        List<AmountDto> sumAmount = inquireService.getMostAmountAccount(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(sumAmount));
    }

    /**
     * 거래가 없는 고객을 추출합니다.
     * @return 거래없는 고객 리스트 정보
     */
    @PostMapping(value = "/api/inquire/no/deal/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AccountDto>>> inquireNoTransUser() {

        List<AccountDto> noTransUser = inquireService.getNoTransUser(2018, 2019);

        return ResponseEntity.ok().body(ApiResponse.success(noTransUser));
    }


    /**
     * 연도별 관리점별 합계를 추출합니다
     * @return 연도별 지점 거래총액 리스트
     */
    @PostMapping(value = "/api/inquire/branch/list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BranchListDto>>> inquireBranchAmountList() {

        List<BranchListDto> branchAmount = inquireService.getBranchAmount();

        return ResponseEntity.ok().body(ApiResponse.success(branchAmount));
    }

    /**
     * 해당 지점의 거래금액 추출 합니다
     * @param apiRequest { "brCode": "" }
     * @return 지점 거래정보
     */
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
