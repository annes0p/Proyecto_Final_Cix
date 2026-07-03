package com.example.cixoil.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Genera y valida el token publico de seguimiento de un viaje (Trip),
 * sin necesidad de guardar nada nuevo en la base de datos ni tocar el
 * modelo de Trip. El token es el id del viaje + una firma HMAC, usando
 * el mismo secreto que ya se usa para los JWT.
 */
@Component
public class TrackingTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generate(Long idTrip) {
        String idCodificado = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(idTrip.toString().getBytes(StandardCharsets.UTF_8));
        return idCodificado + "." + firmar(idTrip);
    }

    public Long verificarYExtraerTripId(String token) {
        if (token == null) return null;
        String[] partes = token.split("\\.");
        if (partes.length != 2) return null;

        Long idTrip;
        try {
            String idDecodificado = new String(
                    Base64.getUrlDecoder().decode(partes[0]), StandardCharsets.UTF_8);
            idTrip = Long.parseLong(idDecodificado);
        } catch (Exception e) {
            return null;
        }

        if (!firmar(idTrip).equals(partes[1])) return null;

        return idTrip;
    }

    private String firmar(Long idTrip) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(idTrip.toString().getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo generar el token de seguimiento", e);
        }
    }
}
