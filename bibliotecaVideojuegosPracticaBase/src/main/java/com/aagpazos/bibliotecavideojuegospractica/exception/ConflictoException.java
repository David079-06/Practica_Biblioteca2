package com.aagpazos.bibliotecavideojuegospractica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/** Conflicto con el estado de los datos: duplicados, borrados imposibles... HTTP 409. */
public class ConflictoException extends ResponseStatusException {
    public ConflictoException(String mensaje) {
        super(HttpStatus.CONFLICT, mensaje);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
