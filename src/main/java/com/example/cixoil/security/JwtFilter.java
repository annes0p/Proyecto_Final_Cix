package com.example.cixoil.security;

import com.example.cixoil.dto.auth.AuthUserDTO;
import com.example.cixoil.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {

            String userId = jwtService.extractUserId(token);

            if (userId == null || jwtService.isTokenExpired(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            AuthUserDTO principal = userService.getAuthDTOById(Long.valueOf(userId)).orElse(null);

            if (principal == null || !jwtService.isAccessTokenValid(token, principal)) {
                filterChain.doFilter(request, response);
                return;
            }

//            var authorities = List.of(
//                    new SimpleGrantedAuthority(
//                            "ROLE_" + user.getRole().getName()
//                    )
//            );

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    principal,
                    null,
//                    authorities
                    Collections.emptyList()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            System.out.println("JWT error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid JWT\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
