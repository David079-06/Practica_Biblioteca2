package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.dto.DesarrolladoraDTO;
import com.aagpazos.bibliotecavideojuegospractica.dto.ResumenDTO;
import com.aagpazos.bibliotecavideojuegospractica.service.DesarrolladoraService;
import com.aagpazos.bibliotecavideojuegospractica.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Recursos auxiliares", description = "Datos de apoyo y estadísticas de GameVault")
public class RecursosAuxiliaresRestController {
    private final DesarrolladoraService desarrolladoraService;
    private final PrestamoService prestamoService;

    public RecursosAuxiliaresRestController(DesarrolladoraService desarrolladoraService,
                                            PrestamoService prestamoService) {
        this.desarrolladoraService = desarrolladoraService;
        this.prestamoService = prestamoService;
    }

    @GetMapping("/desarrolladoras")
    @Operation(summary = "Listar desarrolladoras", description = "Devuelve todas las desarrolladoras para completar el catálogo.")
    @ApiResponse(responseCode = "200", description = "Lista de desarrolladoras, que puede estar vacía")
    public ResponseEntity<List<DesarrolladoraDTO>> listarDesarrolladoras() {
        return ResponseEntity.ok(desarrolladoraService.listarDesarrolladoras());
    }

    @GetMapping("/estadisticas")
    @Operation(summary = "Consultar estadísticas", description = "Devuelve los contadores de videojuegos, socios y préstamos.")
    @ApiResponse(responseCode = "200", description = "Contadores actuales de la biblioteca")
    public ResponseEntity<ResumenDTO> obtenerEstadisticas() {
        return ResponseEntity.ok(prestamoService.resumen());
    }
}
