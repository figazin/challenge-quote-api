package com.alejo.challenge.external;

import com.alejo.challenge.exception.ExternalApiCallException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class InterestService {
    final RetryableInterestService retryableInterestService;
    InterestService(RetryableInterestService retryableInterestService) {
        this.retryableInterestService = retryableInterestService;
    }
    @Cacheable("InterestCache")
    public Double getCurrentInterest() throws ExternalApiCallException {
        return retryableInterestService.callInterestService();
    }
}
