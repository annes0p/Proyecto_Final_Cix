error id: file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/dto/promotion/PromotionSaveDTO.java:
file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/dto/promotion/PromotionSaveDTO.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1323
uri: file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/dto/promotion/PromotionSaveDTO.java
text:
```scala
package com.example.cixoil.dto.promotion;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PromotionSaveDTO(
        @NotBlank(message = "El nombre de la promoción es obligatorio")
        @Size(max = 150, message = "El nombre no debe superar los 150 caracteres")
        String name,

        @NotNull(message = "El producto disparador es obligatorio")
        Long idTriggerProduct,

        @NotNull(message = "La cantidad disparadora es obligatoria")
        @Positive(message = "La cantidad disparadora debe ser mayor a 0")
        Long triggerQuantity,

        @NotNull(message = "El producto de bonificación es obligatorio")
        Long idBonusProduct,

        @Min(value = 1, message = "El mes de inicio debe estar entre 1 y 12")
        @Max(value = 12, message = "El mes de inicio debe estar entre 1 y 12")
        Integer startMonth,

        @Min(value = 1, message = "El día de inicio debe estar entre 1 y 31")
        @Max(value = 31, message = "El día de inicio debe estar entre 1 y 31")
        Integer startDay,

        @Min(value@@ = 1, message = "El mes de fin debe estar entre 1 y 12")
        @Max(value = 12, message = "El mes de fin debe estar entre 1 y 12")
        Integer endMonth,

        @Min(value = 1, message = "El día de fin debe estar entre 1 y 31")
        @Max(value = 31, message = "El día de fin debe estar entre 1 y 31")
        Integer endDay,

        @NotNull(message = "El tipo de promoción es obligatorio")
        Long idPromotionType,

        Boolean autoActivate
) {
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 