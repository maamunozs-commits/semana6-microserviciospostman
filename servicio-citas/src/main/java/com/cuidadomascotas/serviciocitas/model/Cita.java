package com.cuidadomascotas.serviciocitas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CITAS")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citas_seq")
    @SequenceGenerator(name = "citas_seq", sequenceName = "CITAS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE_MASCOTA", nullable = false, length = 100)
    private String nombreMascota;

    @Column(name = "TIPO_MASCOTA", nullable = false, length = 50)
    private String tipoMascota;

    @Column(name = "NOMBRE_DUENO", nullable = false, length = 100)
    private String nombreDueno;

    @Column(name = "TELEFONO_DUENO", length = 20)
    private String telefonoDueno;

    @Column(name = "SERVICIO", nullable = false, length = 100)
    private String servicio;

    @Column(name = "VETERINARIO", nullable = false, length = 100)
    private String veterinario;

    @Column(name = "FECHA_HORA", nullable = false, length = 30)
    private String fechaHora;

    @Column(name = "ESTADO", nullable = false, length = 30)
    private String estado;

    public Cita() {
    }

    public Cita(Long id, String nombreMascota, String tipoMascota, String nombreDueno,
                String telefonoDueno, String servicio, String veterinario,
                String fechaHora, String estado) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.tipoMascota = tipoMascota;
        this.nombreDueno = nombreDueno;
        this.telefonoDueno = telefonoDueno;
        this.servicio = servicio;
        this.veterinario = veterinario;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

