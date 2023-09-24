package com.alejo.challenge.web;

import com.alejo.challenge.dto.QuoteDTO;
import com.alejo.challenge.exception.ExternalApiCallException;
import com.alejo.challenge.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    final QuoteService quoteService;

    private QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value = "/adjusted")
    @ResponseBody
    public ResponseEntity<Page<QuoteDTO>> adjustValues (
            @RequestParam(value="value1") String value1,
            @RequestParam(value="value2") String value2,
            @RequestParam("page") int page,
            @RequestParam("size") int size) throws ExternalApiCallException {
        Page<QuoteDTO> result = quoteService.adjustQuotes(
                                        Integer.valueOf(value1),
                                        Integer.valueOf(value2),
                                        page, size);
        return ResponseEntity.ok().body(result);
    }
}
