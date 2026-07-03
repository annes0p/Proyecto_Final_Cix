package com.example.cixoil.enums;

import com.example.cixoil.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    INACTIVE(0),
    ACTIVE(1),
    DELETED(2);

    private final Integer value;

    public static Status fromValue(Integer value) {
        for (Status status : values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }

        throw new BusinessException("Estado inválido: " + value);
    }

    public Status toggle() {
        return switch (this) {
            case ACTIVE -> INACTIVE;
            case INACTIVE -> ACTIVE;
            default -> throw new BusinessException("El estado " + this + " no puede alternarse");
        };
    }

    public static Integer toggleByValue(Integer value) {
        Status status = fromValue(value);
        return status.toggle().getValue();
    }

}
