package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.dto.PrestamoDTO;
import com.aagpazos.bibliotecavideojuegospractica.exception.DatosInvalidosException;
import com.aagpazos.bibliotecavideojuegospractica.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/prestamos")
@Tag(name = "Préstamos", description = "Consulta, registro y devolución de préstamos")
public class PrestamoRestController {
    private static final Set<String> ESTADOS_API = Set.of("EN_PLAZO", "VENCIDO", "DEVUELTO");

    private final PrestamoService prestamoService;

    public PrestamoRestController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @Operation(summary = "Buscar préstamos", description = "Lista préstamos con filtros opcionales combinables.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de préstamos, que puede estar vacía"),
            @ApiResponse(responseCode = "400", description = "Estado o fecha con formato no válido")
    })
    public ResponseEntity<List<PrestamoDTO>> listar(
            @RequestParam(required = false) String socio,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde) {
        if (estado != null && !estado.isBlank() && !ESTADOS_API.contains(estado.trim().toUpperCase())) {
            throw new DatosInvalidosException("El estado debe ser EN_PLAZO, VENCIDO o DEVUELTO");
        }
        return ResponseEntity.ok(prestamoService.listar(new PrestamoDTO(socio, titulo, estado, fechaDesde)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un préstamo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el préstamo")
    })
    public ResponseEntity<PrestamoDTO> obtener(@PathVariable int id) {
        return ResponseEntity.ok(prestamoService.obtener(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un préstamo", description = "Comprueba las reglas RN-1 a RN-5 y crea el préstamo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Préstamo registrado"),
            @ApiResponse(responseCode = "404", description = "No existe el socio o la copia"),
            @ApiResponse(responseCode = "422", description = "Una regla de negocio impide el préstamo")
    })
    public ResponseEntity<PrestamoDTO> registrar(@RequestBody PrestamoDTO solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.registrar(solicitud));
    }

    @PutMapping("/{id}/devolucion")
    @Operation(summary = "Devolver un préstamo", description = "Calcula la multa y actualiza la copia en una transacción.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Préstamo devuelto"),
            @ApiResponse(responseCode = "404", description = "No existe el préstamo"),
            @ApiResponse(responseCode = "409", description = "El préstamo ya estaba devuelto")
    })
    public ResponseEntity<PrestamoDTO> devolver(@PathVariable int id,
                                                @RequestBody(required = false) DevolucionRequest solicitud) {
        Boolean copiaDanada = solicitud == null ? Boolean.FALSE : solicitud.copiaDanada();
        return ResponseEntity.ok(prestamoService.devolver(id, copiaDanada));
    }

    @Schema(description = "Datos opcionales de la devolución")
    public record DevolucionRequest(
            @Schema(description = "Indica si la copia se devuelve dañada", example = "false")
            Boolean copiaDanada) {}
}

