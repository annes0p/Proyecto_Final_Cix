package com.example.cixoil.enums;

import com.example.cixoil.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IncidentStatus {
    OPEN("Abierto"),
    IN_PROCESS("En proceso"),
    RESOLVED("Resuelto"),
    CLOSED("Cerrado"),
    CANCELED("Cancelado");

    private final String value;

    public IncidentStatus next() {
        return switch (this) {
            case OPEN -> IN_PROCESS;
            case IN_PROCESS -> RESOLVED;
            case RESOLVED -> CLOSED;
            case CLOSED, CANCELED ->
                    throw new BusinessException("El estado " + this + " no tiene estado siguiente.");
        };
    }

    public boolean canReopen() {
        return this == RESOLVED || this == CLOSED;
    }

    public boolean canProcess() {
        return this == OPEN;
    }

    public boolean canResolve() {
        return this == IN_PROCESS;
    }

    public boolean canClose() {
        return this == RESOLVED;
    }

    public boolean canCancel() {
        return this == OPEN || this == IN_PROCESS;
    }

    public boolean canChangeTo(IncidentStatus newStatus) {
        return switch (newStatus) {
            case OPEN -> this.canReopen();
            case IN_PROCESS -> this.canProcess();
            case RESOLVED -> this.canResolve();
            case CLOSED -> this.canClose();
            case CANCELED -> canCancel();
        };
    }
}
