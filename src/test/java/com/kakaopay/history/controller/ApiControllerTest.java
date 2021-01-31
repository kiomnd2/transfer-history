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

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @DisplayName("")
    @Test
    void request_and_get_mostAmount_account_byYears() throws Exception {

        mockMvc.perform(get("/api/inquire/most-amount")
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
                        .value(Matchers.comparesEqualTo(29999000.0)))
        ;
    }

}
