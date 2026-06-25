package com.example.cixoil.controller;

import com.example.cixoil.security.JwtFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class BaseControllerTest {
    @MockBean
    protected JwtFilter jwtFilter;
}
