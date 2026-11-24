package com.aagpazos.bibliotecavideojuegospractica.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Desarrolladora de videojuegos")
public class DesarrolladoraDTO {
    @Schema(description = "Identificador de la desarrolladora", example = "4")
    private Integer id;
    @Schema(description = "Nombre de la desarrolladora", example = "FromSoftware")
    private String nombre;
    @Schema(description = "País de origen", example = "Japón")
    private String pais;

    public  DesarrolladoraDTO() {}

    public DesarrolladoraDTO(Integer id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
