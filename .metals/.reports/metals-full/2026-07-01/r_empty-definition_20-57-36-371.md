error id: file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/controller/AuthController.java:_empty_/ResponseEntity#
file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/controller/AuthController.java
empty definition using pc, found symbol in pc: _empty_/ResponseEntity#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 710
uri: file:///C:/Users/Antony/Documents/Nueva%20carpeta%20(14)/Proyecto_Final_Cix/src/main/java/com/example/cixoil/controller/AuthController.java
text:
```scala
package com.example.cixoil.controller;

import com.example.cixoil.dto.auth.LoginRequestDTO;
import com.example.cixoil.dto.auth.LoginResponseDTO;
import com.example.cixoil.dto.auth.RefreshTokenRequestDTO;
import com.example.cixoil.dto.auth.RefreshTokenResponseDTO;
import com.example.cixoil.service.AuthService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEn@@tity<?> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO data = authService.login(request);
        return ResponseUtil.ok("Autenticación exitosa", data);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequestDTO request) {
        RefreshTokenResponseDTO data = authService.refresh(request);
        return ResponseUtil.ok("Token regenerado exitosamente", data);
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/ResponseEntity#