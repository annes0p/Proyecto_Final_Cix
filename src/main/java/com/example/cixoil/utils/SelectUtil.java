package com.example.cixoil.utils;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.enums.SelectableEnum;

import java.util.Arrays;
import java.util.List;

public final class SelectUtil {

    private SelectUtil() {}

    public static <E extends Enum<E> & SelectableEnum>
    List<SelectDTO<String>> fromEnum(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> new SelectDTO<>(e.name(), e.getValue()))
                .toList();
    }
}
