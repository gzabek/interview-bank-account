package com.gzabek.bank.rest;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gzabek.bank.model.dto.AccountBalanceRequest;
import com.gzabek.bank.service.BalanceService;

@RunWith(SpringRunner.class)
@WebMvcTest
class CustomerAccountBalanceControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected BalanceService balanceService;

    private final static String API_URL = "/bank/customer";

    @Before
    public void setUp() {
        Mockito.reset(balanceService);
    }

    @Test
    void getTotalBalance() throws Exception {

        // when
        when(balanceService.getAccountsBalanceForCustomer(1L)).thenReturn(new BigDecimal(1000));

        // then
        mockMvc.perform(get(API_URL + "/1/balance").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("1000"));

    }

    @Test
    void getTotalBalanceForAccount() throws Exception {

        // when
        when(balanceService.getAccountBalanceForCustomerAndAccount(1L, 1L)).thenReturn(new BigDecimal(1000));

        // then
        mockMvc.perform(get(API_URL + "/1/balance/account/1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("1000"));

    }

    @Test
    void shouldUpdateBalanceForCustomerAccount() throws Exception {

        //given
        String requestBody = "{\"accountId\":\"1\", \"amount\":\"1000\"}";
        AccountBalanceRequest request = new AccountBalanceRequest(1L, new BigDecimal(1000));

        // when
        when(balanceService.addMoneyToAccountForCustomer(1L, request)).thenReturn(new BigDecimal(1500));
        // then
        mockMvc.perform(post(API_URL + "/1/balance/add")
                                .content(requestBody)
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("1500"));

        ArgumentCaptor<AccountBalanceRequest> requestArgumentCaptor = ArgumentCaptor.forClass(AccountBalanceRequest.class);
        ArgumentCaptor<Long> customerArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(balanceService).addMoneyToAccountForCustomer(customerArgumentCaptor.capture(), requestArgumentCaptor.capture());
        assertEquals(request, requestArgumentCaptor.getValue());
        assertEquals(1L, customerArgumentCaptor.getValue());
    }
}