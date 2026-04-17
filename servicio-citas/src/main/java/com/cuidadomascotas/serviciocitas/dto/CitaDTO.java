package com.cuidadomascotas.serviciocitas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CitaDTO {

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Size(max = 100, message = "El nombre de la mascota no puede superar 100 caracteres")
    private String nombreMascota;

    @NotBlank(message = "El tipo de mascota es obligatorio")
    @Size(max = 50, message = "El tipo de mascota no puede superar 50 caracteres")
    private String tipoMascota;

    @NotBlank(message = "El nombre del dueño es obligatorio")
    @Size(max = 100, message = "El nombre del dueño no puede superar 100 caracteres")
    private String nombreDueno;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos")
    private String telefonoDueno;

    @NotBlank(message = "El servicio es obligatorio")
    @Size(max = 100, message = "El servicio no puede superar 100 caracteres")
    private String servicio;

    @NotBlank(message = "El veterinario es obligatorio")
    @Size(max = 100, message = "El veterinario no puede superar 100 caracteres")
    private String veterinario;

    @NotBlank(message = "La fecha y hora son obligatorias")
    private String fechaHora;

    private String estado;

    public CitaDTO() {
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(String telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

