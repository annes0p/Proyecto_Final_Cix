package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    INACTIVE(0),
    ACTIVE(1),
    DELETED(2);

    private final Integer value;

}
