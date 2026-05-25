package com.example.cixoil.mapper;

import com.example.cixoil.dto.notification.NotificationDTO;
import com.example.cixoil.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDTO(Notification notification);
}
