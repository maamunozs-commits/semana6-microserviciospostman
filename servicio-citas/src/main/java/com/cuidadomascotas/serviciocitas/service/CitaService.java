package com.cuidadomascotas.serviciocitas.service;

import com.cuidadomascotas.serviciocitas.dto.CitaDTO;
import com.cuidadomascotas.serviciocitas.model.Cita;
import com.cuidadomascotas.serviciocitas.repository.CitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private static final Logger log = LoggerFactory.getLogger(CitaService.class);

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> obtenerTodas() {
        List<Cita> citas = citaRepository.findAll();
        log.debug("Se encontraron {} citas", citas.size());
        return citas;
    }

    public Optional<Cita> obtenerPorId(Long id) {
        log.debug("Buscando cita con id: {}", id);
        return citaRepository.findById(id);
    }

    public List<Cita> obtenerPorEstado(String estado) {
        log.debug("Buscando citas con estado: {}", estado);
        return citaRepository.findByEstadoIgnoreCase(estado);
    }

    public List<Cita> obtenerPorVeterinario(String veterinario) {
        log.debug("Buscando citas del veterinario: {}", veterinario);
        return citaRepository.findByVeterinarioContainingIgnoreCase(veterinario);
    }

    public Cita crear(CitaDTO dto) {
        log.info("Creando cita para mascota: {}", dto.getNombreMascota());
        Cita cita = new Cita();
        cita.setNombreMascota(dto.getNombreMascota());
        cita.setTipoMascota(dto.getTipoMascota());
        cita.setNombreDueno(dto.getNombreDueno());
        cita.setTelefonoDueno(dto.getTelefonoDueno());
        cita.setServicio(dto.getServicio());
        cita.setVeterinario(dto.getVeterinario());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado("Programada");
        Cita guardada = citaRepository.save(cita);
        log.info("Cita guardada con id: {}", guardada.getId());
        return guardada;
    }

    public Optional<Cita> actualizar(Long id, CitaDTO dto) {
        log.info("Actualizando cita con id: {}", id);
        return citaRepository.findById(id).map(cita -> {
            cita.setNombreMascota(dto.getNombreMascota());
            cita.setTipoMascota(dto.getTipoMascota());
            cita.setNombreDueno(dto.getNombreDueno());
            cita.setTelefonoDueno(dto.getTelefonoDueno());
            cita.setServicio(dto.getServicio());
            cita.setVeterinario(dto.getVeterinario());
            cita.setFechaHora(dto.getFechaHora());
            if (dto.getEstado() != null) {
                cita.setEstado(dto.getEstado());
            }
            return citaRepository.save(cita);
        });
    }

    public boolean eliminar(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            log.info("Cita con id {} eliminada", id);
            return true;
        }
        log.warn("Intento de eliminar cita inexistente con id: {}", id);
        return false;
    }
}

