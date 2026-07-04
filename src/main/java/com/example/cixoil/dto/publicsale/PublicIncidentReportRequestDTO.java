package com.example.cixoil.dto.publicsale;

import com.example.cixoil.enums.DocumentType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PublicIncidentReportRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
        String name,

        @NotBlank(message = "El apellido paterno es obligatorio")
        @Size(max = 100, message = "El apellido paterno no debe superar los 100 caracteres")
        String fatherLastName,

        @Size(max = 100, message = "El apellido materno no debe superar los 100 caracteres")
        String motherLastName,

        @NotNull(message = "El tipo de documento es obligatorio")
        DocumentType documentType,

        @NotBlank(message = "El número de documento es obligatorio")
        @Pattern(regexp = "\\d{8}|\\d{11}", message = "El número de documento debe tener 8 dígitos (DNI) u 11 dígitos (RUC)")
        String docNumber,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{6,9}", message = "El número de teléfono debe contener entre 6 y 9 dígitos")
        String phoneNumber,

        @Email(message = "El correo electrónico no tiene un formato válido")
        String email,

        @Size(max = 255, message = "La dirección no debe superar los 255 caracteres")
        String address,

        @NotNull(message = "Selecciona el tipo de incidencia")
        Long idIncidentType,

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no debe superar los 150 caracteres")
        String title,

        @NotBlank(message = "Describe brevemente el problema")
        @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
        String description
) {
}
