package com.alejo.challenge.service;

import com.alejo.challenge.dto.QuoteDTO;
import com.alejo.challenge.exception.ExternalApiCallException;
import com.alejo.challenge.external.InterestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private InterestService interestService;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void givenValidInput_whenAdjustQuotes_shouldReturnAdjustedValue() throws ExternalApiCallException {
        when(interestService.getCurrentInterest()).thenReturn(20.0);

        Page<QuoteDTO> response = quoteService.adjustQuotes(10, 10, 0, 10);
        assertEquals(response, new PageImpl<>(Arrays.asList(new QuoteDTO(24.0))));
    }

    @Test
    public void givenSecondInput_whenAdjustQuotes_shouldReturnCorrectValue() throws ExternalApiCallException {
        when(interestService.getCurrentInterest()).thenReturn(20.0);

        Page<QuoteDTO> response = quoteService.adjustQuotes(50, 50, 0, 10);
        assertEquals(response, new PageImpl<>(Arrays.asList(new QuoteDTO(120.0))));
    }
}
