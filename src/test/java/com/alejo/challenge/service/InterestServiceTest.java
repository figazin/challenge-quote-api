package com.alejo.challenge.service;

import com.alejo.challenge.exception.ExternalApiCallException;
import com.alejo.challenge.external.InterestService;
import com.alejo.challenge.external.RetryableInterestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InterestServiceTest {
    @Mock
    private RetryableInterestService retryableInterestService;
    @InjectMocks
    private InterestService interestService;

    @Test
    void whenGetCurrentInterest_shouldReturnResponseValue() throws ExternalApiCallException {
        when(retryableInterestService.callInterestService()).thenReturn(10.0);
        Double result = interestService.getCurrentInterest();
        assertEquals(10.0, result);
    }
}
