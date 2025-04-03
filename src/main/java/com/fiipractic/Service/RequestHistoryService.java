package com.fiipractic.service;

import com.fiipractic.entity.RequestHistory;
import com.fiipractic.entity.User;
import com.fiipractic.repository.RequestHistoryRepository;
import com.fiipractic.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestHistoryService {
    private final RequestHistoryRepository requestHistoryRepository;
    private final UserRepository userRepository;

    public RequestHistoryService(RequestHistoryRepository requestHistoryRepository, UserRepository userRepository) {
        this.requestHistoryRepository = requestHistoryRepository;
        this.userRepository = userRepository;
    }

    public RequestHistory createRequestHistory(Long userId, RequestHistory requestHistory) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with id: " + userId + " does not exist");
        }

        User user = userOptional.get();
        requestHistory.setUser(user);

        if (user.getRequestHistories() != null) {
            user.getRequestHistories().add(requestHistory);
        }

        return requestHistoryRepository.save(requestHistory);
    }

    public Page<RequestHistory> getRequestHistoryByUserId(Long userId, int page, int size) {
        return requestHistoryRepository.findByUserId(userId, PageRequest.of(page, size));
    }
}
