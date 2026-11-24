package com.aagpazos.bibliotecavideojuegospractica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Videojuego y disponibilidad de sus copias")
public class VideojuegoDTO {
    @Schema(description = "Identificador", example = "3")
    private Integer id;
    @Schema(description = "Título", example = "Mario Kart 8 Deluxe")
    private String titulo;
    @Schema(description = "Año de lanzamiento", example = "2017")
    private Integer anioLanzamiento;
    @Schema(description = "Clasificación PEGI", example = "3")
    private Integer pegi;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "ID de la desarrolladora que se envía al crear un videojuego", example = "4")
    private Integer idDesarrolladora;
    @Schema(description = "Nombre de la desarrolladora", example = "Nintendo")
    private String desarrolladora;
    @Schema(description = "Número total de copias", example = "2")
    private Integer copiasTotales;
    @Schema(description = "Copias disponibles para préstamo", example = "1")
    private Integer copiasDisponibles;
    @JsonIgnore
    private boolean disponible;
    @JsonIgnore
    private Integer totalPrestado;

    //Constructor vacio, necesario para que funcione OK
    public VideojuegoDTO() {}

    //Constructor "normal"
    public VideojuegoDTO(Integer id, String titulo, Integer anioLanzamiento, Integer pegi, Integer idDesarrolladora, String desarrolladora, Integer copiasTotales, Integer copiasDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.pegi = pegi;
        this.idDesarrolladora = idDesarrolladora;
        this.desarrolladora = desarrolladora;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasDisponibles;
    }

    //Constructor filtros
    public VideojuegoDTO(String titulo, Integer idDesarrolladora, Integer anioDesde,
                         Integer pegiMax, boolean soloDisponibles) {
        this.titulo = titulo;
        this.idDesarrolladora = idDesarrolladora;
        this.anioLanzamiento = anioDesde;
        this.pegi = pegiMax;
        this.disponible = soloDisponibles;
    }

    //Constructor request
    public VideojuegoDTO(String titulo, Integer anioLanzamiento, Integer pegi, Integer idDesarrolladora) {
        this.titulo = titulo;
        this.idDesarrolladora = idDesarrolladora;
        this.pegi = pegi;
        this.anioLanzamiento = anioLanzamiento;
    }

    //Constructor más prestados
    public VideojuegoDTO(Integer id, String titulo, Integer totalPrestado) {
        this.id = id;
        this.titulo = titulo;
        this.totalPrestado = totalPrestado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Integer anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public Integer getPegi() {
        return pegi;
    }

    public void setPegi(Integer pegi) {
        this.pegi = pegi;
    }

    public Integer getIdDesarrolladora() {
        return idDesarrolladora;
    }

    public void setIdDesarrolladora(Integer idDesarrolladora) {
        this.idDesarrolladora = idDesarrolladora;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public Integer getCopiasTotales() {
        return copiasTotales;
    }

    public void setCopiasTotales(Integer copiasTotales) {
        this.copiasTotales = copiasTotales;
    }

    public Integer getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(Integer copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getTotalPrestado() {
        return totalPrestado;
    }

    public void setTotalPrestado(Integer totalPrestado) {
        this.totalPrestado = totalPrestado;
    }
}
