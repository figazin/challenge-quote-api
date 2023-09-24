package com.alejo.challenge.web;

import com.alejo.challenge.dto.QuoteDTO;
import com.alejo.challenge.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class QuoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    public void givenInput_whenGetAdjustedValues_thenStatus200() throws Exception {
        QuoteDTO quoteDTO = new QuoteDTO(10.0);
        Page<QuoteDTO> pagedResult = new PageImpl<>(Arrays.asList(quoteDTO));
        when(quoteService.adjustQuotes(anyInt(),
                anyInt(), anyInt(), anyInt())).thenReturn(pagedResult);

        String result = mvc.perform(get("/api/quotes/adjusted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("value1", "10")
                        .param("value2", "10")
                        .param("size", "0")
                        .param("page", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.contains("{\"quote\":10.0}"));
    }
}
