package com.aagpazos.bibliotecavideojuegospractica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/** Un id (de la ruta o del cuerpo) no existe. HTTP 404. */
public class RecursoNoEncontradoException extends ResponseStatusException {
    public RecursoNoEncontradoException(String mensaje) {
        super(HttpStatus.NOT_FOUND, mensaje);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
