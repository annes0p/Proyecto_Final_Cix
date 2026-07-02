package com.example.cixoil.dto.supplier;

import com.example.cixoil.enums.DocumentType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SupplierSaveDTO(
        @NotBlank(message = "La razón social es obligatoria")
        @Size(max = 150, message = "La razón social no debe superar los 150 caracteres")
        String legalName,

        @NotNull(message = "El tipo de documento es obligatorio")
        DocumentType documentType,

        @NotBlank(message = "El número de documento es obligatorio")
        @Pattern(regexp = "\\d{8}|\\d{11}", message = "El número de documento debe tener 8 dígitos (DNI) u 11 dígitos (RUC)")
        String docNumber,

        @Pattern(regexp = "\\d{6,9}", message = "El número de teléfono debe contener entre 6 y 9 dígitos")
        String phoneNumber,

        @Email(message = "El correo electrónico no tiene un formato válido")
        String email,

        @Size(max = 255, message = "La dirección no debe superar los 255 caracteres")
        String address
) {
}
