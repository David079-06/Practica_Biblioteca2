package com.aagpazos.bibliotecavideojuegospractica.controladores.rest;

import com.aagpazos.bibliotecavideojuegospractica.dto.VideojuegoDTO;
import com.aagpazos.bibliotecavideojuegospractica.service.VideojuegoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@Tag(name = "Videojuegos", description = "Consulta y alta de videojuegos")
public class VideojuegoRestController {
    private final VideojuegoService videojuegoService;

    public VideojuegoRestController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    @Operation(summary = "Listar videojuegos", description = "Permite combinar filtros de título, desarrolladora, año, PEGI y disponibilidad.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de videojuegos, que puede estar vacía"),
            @ApiResponse(responseCode = "400", description = "Algún filtro tiene un formato no válido")
    })
    public ResponseEntity<List<VideojuegoDTO>> listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer idDesarrolladora,
            @RequestParam(required = false) Integer anioDesde,
            @RequestParam(required = false) Integer pegiMax,
            @RequestParam(required = false) Boolean soloDisponibles) {
        VideojuegoDTO filtro = new VideojuegoDTO(titulo, idDesarrolladora, anioDesde, pegiMax,
                Boolean.TRUE.equals(soloDisponibles));
        return ResponseEntity.ok(videojuegoService.listar(filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un videojuego")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el videojuego")
    })
    public ResponseEntity<VideojuegoDTO> obtener(@PathVariable int id) {
        return ResponseEntity.ok(videojuegoService.obtener(id));
    }

    @PostMapping
    @Operation(summary = "Crear un videojuego")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Videojuego creado"),
            @ApiResponse(responseCode = "400", description = "Datos incompletos o no válidos"),
            @ApiResponse(responseCode = "404", description = "No existe la desarrolladora indicada")
    })
    public ResponseEntity<VideojuegoDTO> crear(@RequestBody VideojuegoDTO videojuego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuegoService.crear(videojuego));
    }
}

