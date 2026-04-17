package com.cuidadomascotas.serviciocitas.controller;

import com.cuidadomascotas.serviciocitas.dto.CitaDTO;
import com.cuidadomascotas.serviciocitas.model.Cita;
import com.cuidadomascotas.serviciocitas.service.CitaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private static final Logger log = LoggerFactory.getLogger(CitaController.class);

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodas() {
        log.info("GET /api/citas - Obteniendo todas las citas");
        return ResponseEntity.ok(citaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/citas/{} - Buscando cita por id", id);
        return citaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cita>> obtenerPorEstado(@PathVariable String estado) {
        log.info("GET /api/citas/estado/{} - Filtrando citas por estado", estado);
        return ResponseEntity.ok(citaService.obtenerPorEstado(estado));
    }

    @GetMapping("/veterinario/{veterinario}")
    public ResponseEntity<List<Cita>> obtenerPorVeterinario(@PathVariable String veterinario) {
        log.info("GET /api/citas/veterinario/{} - Filtrando citas por veterinario", veterinario);
        return ResponseEntity.ok(citaService.obtenerPorVeterinario(veterinario));
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@Valid @RequestBody CitaDTO citaDTO) {
        log.info("POST /api/citas - Creando nueva cita para mascota: {}", citaDTO.getNombreMascota());
        Cita creada = citaService.crear(citaDTO);
        log.info("Cita creada con id: {}", creada.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @Valid @RequestBody CitaDTO citaDTO) {
        log.info("PUT /api/citas/{} - Actualizando cita", id);
        return citaService.actualizar(id, citaDTO)
                .map(c -> {
                    log.info("Cita {} actualizada correctamente", id);
                    return ResponseEntity.ok(c);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/citas/{} - Eliminando cita", id);
        if (citaService.eliminar(id)) {
            log.info("Cita {} eliminada correctamente", id);
            return ResponseEntity.noContent().build();
        }
        log.warn("Cita {} no encontrada para eliminar", id);
        return ResponseEntity.notFound().build();
    }
}

