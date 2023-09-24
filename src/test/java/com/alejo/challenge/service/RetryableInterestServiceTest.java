package com.alejo.challenge.service;

import com.alejo.challenge.ChallengeApplication;
import com.alejo.challenge.ChallengeApplicationTests;
import com.alejo.challenge.exception.ExternalApiCallException;
import com.alejo.challenge.external.RetryableInterestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ChallengeApplicationTests.class, ChallengeApplication.class})
public class RetryableInterestServiceTest {
    @MockBean
    RestTemplate restTemplate;
    @Autowired
    RetryableInterestService retryableInterestService;

    @Test
    void whenCallInterestService_shouldReturnDouble() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(10.0);
        Double result = retryableInterestService.callInterestService();
        assertEquals(result, 10.0);
    }

    @Test
    void givenException_whenCallInterestService_shouldRetry() {
        when(restTemplate.getForObject(anyString(), any()))
                .thenThrow(new RestClientException(""))
                .thenThrow(new RestClientException(""))
                .thenReturn(10.0);
        Double result = retryableInterestService.callInterestService();
        verify(restTemplate, times(3)).getForObject(anyString(), any());
        assertEquals(10.0, result);
    }

    @Test
    void givenExceptionAndNullResult_whenGetCurrentInterest_shouldThrowException() {
        when(restTemplate.getForObject(anyString(), any()))
                .thenThrow(new RestClientException("Rest Client Exception"));
        assertThrows(Exception.class, () -> {
            retryableInterestService.callInterestService();
        });
        verify(restTemplate, times(3)).getForObject(anyString(), any());
    }

    @Test
    void givenExceptionAndStoredResult_whenGetCurrentInterest_shouldReturnStoredResult() throws ExternalApiCallException {
        ReflectionTestUtils.setField(retryableInterestService, "lastResult", 10.0);
        when(restTemplate.getForObject(anyString(), any()))
                .thenThrow(new RestClientException("Rest Client Exception"));
        Double result = retryableInterestService.callInterestService();
        assertEquals(10.0, result);
    }
}
