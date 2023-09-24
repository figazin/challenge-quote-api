package com.alejo.challenge.external;

import com.alejo.challenge.exception.ExternalApiCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RetryableInterestService {

    final RestTemplate restTemplate;
    protected Double lastResult = null;
    RetryableInterestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    String uri = "http://localhost:8082/api/interest";
    @Retryable(value = {RestClientException.class}, maxAttempts = 3, backoff = @Backoff(200))
    public Double callInterestService() {
        log.info("Calling Interest service using uri: {}", uri);
        lastResult = restTemplate.getForObject(uri, Double.class);
        log.info("Success. Returning interest value: {}", lastResult);
        return lastResult;
    }

    @Recover
    public Double returnLastValue() throws ExternalApiCallException {
        log.info("Returning last stored value: {}", lastResult);
        if(lastResult != null) {
            return lastResult;
        } else {
            throw new ExternalApiCallException("There wasn't a backup Interest value to return");
        }
    }
}
