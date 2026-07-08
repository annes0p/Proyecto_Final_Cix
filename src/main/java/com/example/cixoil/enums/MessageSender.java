package com.example.cixoil.enums;

/**
 * Quien envio un mensaje del chat de seguimiento (Trip <-> Cliente).
 * No implementa SelectableEnum porque no se usa en ningun select/dropdown,
 * solo para distinguir el lado del chat.
 */
public enum MessageSender {
    CLIENT,
    STAFF
}
