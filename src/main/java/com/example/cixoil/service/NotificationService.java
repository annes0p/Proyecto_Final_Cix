package com.example.cixoil.service;

import com.example.cixoil.dto.notification.NotificationDTO;
import com.example.cixoil.enums.NotificationStatus;
import com.example.cixoil.mapper.NotificationMapper;
import com.example.cixoil.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    public Page<NotificationDTO> getUserNotifications(Long id, Pageable pageable) {
        Sort sort = pageable.getSort().isUnsorted()
                ? Sort.by("createdAt").descending()
                : pageable.getSort();

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
        return notificationRepository.findAllByUserId(id, sortedPageable).map(notificationMapper::toDTO);
    }

    @Transactional
    public boolean updateStatus(Long id, Long userId) {
        int rows = notificationRepository.updateStatus(id, userId, NotificationStatus.READ);
        return rows > 0;
    }

    @Transactional
    public int updateAllStatus(Long userId) {
        return notificationRepository.updateAllStatus(userId, NotificationStatus.READ);
    }

    @Transactional(readOnly = true)
    public long countUnread(Long userId) {
        return notificationRepository.countByUserIdAndNotificationStatus(userId, NotificationStatus.UNREAD);
    }
}
