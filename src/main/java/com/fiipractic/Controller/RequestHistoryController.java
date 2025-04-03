package com.fiipractic.controller;

import com.fiipractic.entity.RequestHistory;
import com.fiipractic.service.RequestHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;

    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<RequestHistory> createRequestHistory(@PathVariable Long userId,
                                                               @RequestBody RequestHistory requestHistory) {
        try {
            RequestHistory requestHistory1 = requestHistoryService.createRequestHistory(userId, requestHistory);

            return ResponseEntity.ok(requestHistory1);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<RequestHistory>> getRequestHistoryByUserId(@PathVariable Long userId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(requestHistoryService.getRequestHistoryByUserId(userId, page, size));
    }
}
