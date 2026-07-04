package com.example.cixoil;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class CixoilApplication {

	static {
		// El servidor (Render) corre en UTC por defecto. Forzamos la hora de
		// Peru como zona horaria de toda la aplicacion para que
		// @CreationTimestamp/@UpdateTimestamp (createdAt/updatedAt de
		// AuditableEntity) y cualquier LocalDateTime.now() sin zona explicita
		// no salgan adelantados 5 horas.
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CixoilApplication.class, args);
	}

}
