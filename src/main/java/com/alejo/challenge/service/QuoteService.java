package com.alejo.challenge.service;

import com.alejo.challenge.dto.QuoteDTO;
import com.alejo.challenge.exception.ExternalApiCallException;
import com.alejo.challenge.external.InterestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QuoteService {
    final InterestService interestService;
    QuoteService(InterestService interestService) {
        this.interestService = interestService;
    }
    public Page<QuoteDTO> adjustQuotes(Integer value1, Integer value2,
                                 int page, int size) throws ExternalApiCallException {
        Pageable paging = PageRequest.of(page, size);
        Double interest = getFinalInterest();
        Double sum = (double) (value1 + value2);
        QuoteDTO result = new QuoteDTO(sum + ((interest / 100) * sum));
        return new PageImpl<>(Arrays.asList(result));
    }

    private Double getFinalInterest() throws ExternalApiCallException {
        return interestService.getCurrentInterest();
    }
}
