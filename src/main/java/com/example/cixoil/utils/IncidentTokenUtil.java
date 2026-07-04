package com.example.cixoil.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Mismo patron que TrackingTokenUtil (id + firma HMAC, sin guardar nada
 * nuevo en base de datos), pero para incidentes: el link publico que se
 * le manda al cliente para que califique como se resolvio su incidencia.
 * Se firma con un prefijo distinto ("incident:") para que un token de
 * seguimiento de ruta no sirva por error como token de incidente.
 */
@Component
public class IncidentTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generate(Long idIncident) {
        String idCodificado = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(idIncident.toString().getBytes(StandardCharsets.UTF_8));
        return idCodificado + "." + firmar(idIncident);
    }

    public Long verificarYExtraerIncidentId(String token) {
        if (token == null) return null;
        String[] partes = token.split("\\.");
        if (partes.length != 2) return null;

        Long idIncident;
        try {
            String idDecodificado = new String(
                    Base64.getUrlDecoder().decode(partes[0]), StandardCharsets.UTF_8);
            idIncident = Long.parseLong(idDecodificado);
        } catch (Exception e) {
            return null;
        }

        if (!firmar(idIncident).equals(partes[1])) return null;

        return idIncident;
    }

    private String firmar(Long idIncident) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(("incident:" + idIncident).getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo generar el token de calificación", e);
        }
    }
}
