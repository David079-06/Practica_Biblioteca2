package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.exception.ConflictoException;
import com.aagpazos.bibliotecavideojuegospractica.exception.DatosInvalidosException;
import com.aagpazos.bibliotecavideojuegospractica.exception.RecursoNoEncontradoException;
import com.aagpazos.bibliotecavideojuegospractica.exception.ReglaNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.aagpazos.bibliotecavideojuegospractica.controladores.rest")
public class ApiExceptionHandler {
    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<Map<String, Object>> datosInvalidos(DatosInvalidosException exception,
                                                              HttpServletRequest request) {
        return crearRespuesta(HttpStatus.BAD_REQUEST, exception.getReason(), request);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> noEncontrado(RecursoNoEncontradoException exception,
                                                            HttpServletRequest request) {
        return crearRespuesta(HttpStatus.NOT_FOUND, exception.getReason(), request);
    }

    @ExceptionHandler(ConflictoException.class)
    public ResponseEntity<Map<String, Object>> conflicto(ConflictoException exception,
                                                         HttpServletRequest request) {
        return crearRespuesta(HttpStatus.CONFLICT, exception.getReason(), request);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<Map<String, Object>> reglaNegocio(ReglaNegocioException exception,
                                                            HttpServletRequest request) {
        String mensaje = exception.getRegla() + ": " + exception.getReason();
        return crearRespuesta(HttpStatus.UNPROCESSABLE_ENTITY, mensaje, request);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity<Map<String, Object>> peticionMalFormada(Exception exception,
                                                                  HttpServletRequest request) {
        String mensaje;
        if (exception instanceof MethodArgumentTypeMismatchException mismatch) {
            mensaje = "El parámetro '" + mismatch.getName() + "' tiene un formato no válido";
        } else if (exception instanceof MissingServletRequestParameterException missing) {
            mensaje = "Falta el parámetro obligatorio '" + missing.getParameterName() + "'";
        } else {
            mensaje = "El cuerpo JSON no tiene un formato válido";
        }
        return crearRespuesta(HttpStatus.BAD_REQUEST, mensaje, request);
    }

    private ResponseEntity<Map<String, Object>> crearRespuesta(HttpStatus estado, String mensaje,
                                                                HttpServletRequest request) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status", estado.value());
        cuerpo.put("error", estado.getReasonPhrase());
        cuerpo.put("message", mensaje);
        cuerpo.put("path", request.getRequestURI());
        return ResponseEntity.status(estado).body(cuerpo);
    }
}

