package com.aagpazos.bibliotecavideojuegospractica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/** Datos mal formados o fuera de rango. HTTP 400. */
public class DatosInvalidosException extends ResponseStatusException {
    public DatosInvalidosException(String mensaje) {
        super(HttpStatus.BAD_REQUEST, mensaje);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
