package com.aagpazos.bibliotecavideojuegospractica.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de socio, incluidos edad y préstamos abiertos calculados")
public class SocioDTO {
    @Schema(description = "Identificador del socio", example = "2")
    private Integer id;
    @Schema(description = "Nombre", example = "Marcos")
    private String nombre;
    @Schema(description = "Apellidos", example = "Gil Prieto")
    private String apellidos;
    @Schema(description = "Correo electrónico único", example = "marcos.gil@mail.test")
    private String email;
    @Schema(description = "Fecha de nacimiento en formato ISO", example = "1991-07-17")
    private LocalDate fechaNacimiento;
    @Schema(description = "Fecha de alta en la biblioteca", example = "2026-04-22")
    private LocalDate fechaAlta;
    @Schema(description = "Indica si el socio está activo", example = "true")
    private Boolean activo;
    @Schema(description = "Edad calculada", example = "35")
    private Integer edad;
    @Schema(description = "Número de préstamos todavía abiertos", example = "1")
    private Integer prestamosAbiertos;

    public SocioDTO() {}

    public SocioDTO(Integer id, String nombre, String apellidos, String email, LocalDate fechaNacimiento, LocalDate fechaAlta, Boolean activo, Integer edad, Integer prestamosAbiertos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.edad = edad;
        this.prestamosAbiertos = prestamosAbiertos;
    }

    //Socio Filtro
    public SocioDTO(String nombre, String email, Boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }

    //Socio Request
    public SocioDTO(String nombre, String apellidos, String email,
                    LocalDate fechaNacimiento, Boolean activo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    //Socio

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getPrestamosAbiertos() {
        return prestamosAbiertos;
    }

    public void setPrestamosAbiertos(Integer prestamosAbiertos) {
        this.prestamosAbiertos = prestamosAbiertos;
    }
}

