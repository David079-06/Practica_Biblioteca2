package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.dto.SocioDTO;
import com.aagpazos.bibliotecavideojuegospractica.service.SocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
@Tag(name = "Socios", description = "Consulta y mantenimiento de socios")
public class SocioRestController {
    private final SocioService socioService;

    public SocioRestController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    @Operation(summary = "Listar socios", description = "Permite combinar filtros de nombre, email y estado activo.")
    @ApiResponse(responseCode = "200", description = "Lista de socios, que puede estar vacía")
    public ResponseEntity<List<SocioDTO>> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean activo) {
        return ResponseEntity.ok(socioService.listar(new SocioDTO(nombre, email, activo)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un socio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Socio encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el socio")
    })
    public ResponseEntity<SocioDTO> obtener(@PathVariable int id) {
        return ResponseEntity.ok(socioService.obtener(id));
    }

    @PostMapping
    @Operation(summary = "Crear un socio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Socio creado"),
            @ApiResponse(responseCode = "400", description = "Datos incompletos o no válidos"),
            @ApiResponse(responseCode = "409", description = "El email ya está registrado")
    })
    public ResponseEntity<SocioDTO> crear(@RequestBody SocioDTO socio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.crear(socio));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un socio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Socio actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos incompletos o no válidos"),
            @ApiResponse(responseCode = "404", description = "No existe el socio"),
            @ApiResponse(responseCode = "409", description = "El email ya pertenece a otro socio")
    })
    public ResponseEntity<SocioDTO> actualizar(@PathVariable int id, @RequestBody SocioDTO socio) {
        return ResponseEntity.ok(socioService.actualizar(id, socio));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un socio")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Socio borrado"),
            @ApiResponse(responseCode = "404", description = "No existe el socio"),
            @ApiResponse(responseCode = "409", description = "El socio tiene préstamos y no se puede borrar")
    })
    public ResponseEntity<Void> borrar(@PathVariable int id) {
        socioService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}

