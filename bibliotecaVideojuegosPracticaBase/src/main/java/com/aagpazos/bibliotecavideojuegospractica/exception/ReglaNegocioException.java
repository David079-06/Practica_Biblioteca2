package com.aagpazos.bibliotecavideojuegospractica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/** Se incumple una regla de negocio (RN-1...). Se traduce a HTTP 422. */
public class ReglaNegocioException extends ResponseStatusException {
    private final String regla;

    public ReglaNegocioException(String regla, String mensaje) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, mensaje);
        this.regla = regla;
    }

    @Override
    public String getMessage() {
        return getReason();
    }

    public String getRegla() {
        return regla;
    }

    public String obtenerMensajeUsuario() {
        return "[" + regla + "] " + getMessage();
    }
}
