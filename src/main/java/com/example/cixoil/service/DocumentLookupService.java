package com.example.cixoil.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.cixoil.dto.publicsale.PublicDocumentLookupDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Consulta datos publicos de DNI/RUC contra apiperu.dev, para
 * autocompletar nombre y avisar si el documento no parece valido en
 * el portal publico (tienda / reportar incidencia). IMPORTANTE:
 * - Esto NO es RENIEC oficial (el propio proveedor aclara que el DNI
 *   sale del "padron reducido" de SUNAT, con cobertura parcial), asi
 *   que un "no encontrado" no debe bloquear la compra, solo evita el
 *   autocompletado.
 * - La consulta se hace siempre desde el backend (nunca desde el
 *   navegador) para no exponer el token.
 * - Si el token no esta configurado (variable de entorno APIPERU_TOKEN
 *   vacia), el servicio simplemente responde "no encontrado" sin
 *   lanzar error, para no romper el flujo de compra.
 */
@Service
public class DocumentLookupService {

    private static final String DNI_URL = "https://apiperu.dev/api/dni";
    private static final String RUC_URL = "https://apiperu.dev/api/ruc";

    @Value("${apiperu.token:}")
    private String token;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public DocumentLookupService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PublicDocumentLookupDTO buscarDni(String dni) {
        if (token == null || token.isBlank()) return noEncontrado();

        try {
            String cuerpo = objectMapper.writeValueAsString(Map.of("dni", dni));
            JsonNode root = enviarConsulta(DNI_URL, cuerpo);
            if (root == null || !root.path("success").asBoolean(false)) return noEncontrado();

            JsonNode data = root.path("data");
            return new PublicDocumentLookupDTO(
                    true,
                    textoOr(data, "nombres"),
                    textoOr(data, "apellido_paterno"),
                    textoOr(data, "apellido_materno"),
                    null,
                    null
            );
        } catch (Exception e) {
            return noEncontrado();
        }
    }

    public PublicDocumentLookupDTO buscarRuc(String ruc) {
        if (token == null || token.isBlank()) return noEncontrado();

        try {
            String cuerpo = objectMapper.writeValueAsString(Map.of("ruc", ruc));
            JsonNode root = enviarConsulta(RUC_URL, cuerpo);
            if (root == null || !root.path("success").asBoolean(false)) return noEncontrado();

            JsonNode data = root.path("data");
            return new PublicDocumentLookupDTO(
                    true,
                    textoOr(data, "nombre_o_razon_social"),
                    null,
                    null,
                    textoOr(data, "estado"),
                    textoOr(data, "condicion")
            );
        } catch (Exception e) {
            return noEncontrado();
        }
    }

    private JsonNode enviarConsulta(String url, String cuerpoJson) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .timeout(Duration.ofSeconds(6))
                .POST(HttpRequest.BodyPublishers.ofString(cuerpoJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) return null;

        return objectMapper.readTree(response.body());
    }

    private String textoOr(JsonNode data, String campo) {
        JsonNode valor = data.path(campo);
        return valor.isMissingNode() || valor.isNull() ? null : valor.asText();
    }

    private PublicDocumentLookupDTO noEncontrado() {
        return new PublicDocumentLookupDTO(false, null, null, null, null, null);
    }
}
