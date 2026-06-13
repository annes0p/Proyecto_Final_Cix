package com.example.cixoil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public String recommend(
            String model,
            String useType,
            String products
    ) {
        String prompt = """
                Eres un sistema experto para recomendación de productos automotrices.
                
                Tu tarea es seleccionar el MEJOR producto según compatibilidad con el vehículo y el tipo de uso que se le da.
                
                ### CONTEXTO
                Modelo vehicular:
                %s
                
                Tipo de uso del vehículo:
                %s
                
                ### LISTA DE PRODUCTOS DISPONIBLES
                %s
                
                ### REGLAS
                - Debes elegir SOLO 1 producto de la lista.
                - Evalúa a detalle la compatibilidad técnica, uso y adecuación general.
                - No inventes productos, toma como posibilidades solo los de la lista proporcionada.
                
                ### FORMATO DE RESPUESTA (OBLIGATORIO)
                No uses markdown, no uses bloques de código, no agregues texto adicional.
                Devuelve ÚNICAMENTE un JSON VÁLIDO, sin texto adicional, sin "Aquí tienes el json" o similar. Solo el JSON siguiendo la estructura:
                
                {
                    "idProduct": <aquí el número id del producto más adecuado>,
                    "reason": "<explicación breve no mayor a 255 caracteres de por qué es el mejor>",
                    "priority": "<HIGH | MEDIUM | LOW>"
                }
                
                ### DEFINICIÓN DE PRIORIDAD:
                - HIGH: el producto es altamente adecuado y recomendado
                - MEDIUM: es aceptable pero no ideal
                - LOW: funciona, pero no es recomendable frente a otros
                
                """.formatted(
                        model, useType, products
        );

        return chatClient.prompt().user(prompt).call().content();
    }
}
