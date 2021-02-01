package com.kakaopay.history.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.history.code.Codes;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("각 년도별로 가장 많은 거래를 한 고객을 추출합니다")
    @Test
    void request_and_get_mostAmount_account_per_Years() throws Exception {

        mockMvc.perform(post("/api/inquire/most-amount")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(Codes.S0000.code))
                .andExpect(jsonPath("body[0].year").value(2018))
                .andExpect(jsonPath("body[0].name").value("테드"))
                .andExpect(jsonPath("body[0].acctNo").value("11111114"))
                .andExpect(jsonPath("body[0].sumAmt")
                        .value(Matchers.comparesEqualTo(5000000.0)))
                .andExpect(jsonPath("body[1].year").value(2019))
                .andExpect(jsonPath("body[1].name").value("에이스"))
                .andExpect(jsonPath("body[1].acctNo").value("11111112"))
                .andExpect(jsonPath("body[1].sumAmt")
                        .value(Matchers.comparesEqualTo(29999000.0)));
    }

    @DisplayName("각 년도별로 거래가 없는 고객을 추출합니다")
    @Test
    void request_and_get_not_transfer_user_per_Years() throws Exception {
        mockMvc.perform(post("/api/inquire/no-trans")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("body[0].year").value(2019))
                .andExpect(jsonPath("body[0].acctNo").value("11111114"))
                .andExpect(jsonPath("body[0].acctNm").value("테드"));
    }


    @DisplayName("지점별 거래금액을 추출합니다")
    @Test
    void request_and_get_branch_amount_per_Years() throws Exception {
        mockMvc.perform(post("/api/inquire/branch-amount-list")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("body[0].year").value(2018))
                .andExpect(jsonPath("body[0].dataList[0].brName").value("분당점"))
                .andExpect(jsonPath("body[0].dataList[0].brCode").value("B"))
                .andExpect(jsonPath("body[0].dataList[0].sumAmt").value(5000000))
                .andExpect(jsonPath("body[0].dataList[1].brName").value("판교점"))
                .andExpect(jsonPath("body[0].dataList[1].brCode").value("A"))
                .andExpect(jsonPath("body[0].dataList[1].sumAmt").value(2498900))

                .andExpect(jsonPath("body[1].year").value(2019))
                .andExpect(jsonPath("body[1].dataList[0].brName").value("판교점"))
                .andExpect(jsonPath("body[1].dataList[0].brCode").value("A"))
                .andExpect(jsonPath("body[1].dataList[0].sumAmt").value(30098500));
    }

    @DisplayName("입력받은 지점의 총 금액을 추출합니다")
    @Test
    void request_and_get_amount() throws Exception {

        ApiRequest request = new ApiRequest("A");

        mockMvc.perform(post("/api/inquire/branch-amount")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(Codes.S0000.code))
                .andExpect(jsonPath("body.brName").value("판교점"))
                .andExpect(jsonPath("body.sumAmt").value(32597400));

    }
}
