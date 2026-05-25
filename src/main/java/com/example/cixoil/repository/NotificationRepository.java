package com.example.cixoil.repository;

import com.example.cixoil.enums.NotificationStatus;
import com.example.cixoil.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAllByUserId(Long id, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.notificationStatus = :status WHERE n.id = :id AND n.user.id = :userId")
    int updateStatus(
            @Param("id") Long id,
            @Param("userId") Long userId,
            @Param("status") NotificationStatus status
    );

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.notificationStatus = :status WHERE n.user.id = :userId")
    int updateAllStatus(
            @Param("userId") Long userId,
            @Param("status") NotificationStatus status
    );

    long countByUserIdAndNotificationStatus(Long userId, NotificationStatus status);
}
