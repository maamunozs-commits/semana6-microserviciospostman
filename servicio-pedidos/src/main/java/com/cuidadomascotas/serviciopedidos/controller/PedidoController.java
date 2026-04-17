package com.cuidadomascotas.serviciopedidos.controller;

import com.cuidadomascotas.serviciopedidos.dto.PedidoDTO;
import com.cuidadomascotas.serviciopedidos.model.Pedido;
import com.cuidadomascotas.serviciopedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        log.info("GET /api/pedidos - Obteniendo todos los pedidos");
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/pedidos/{} - Buscando pedido por id", id);
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPorEstado(@PathVariable String estado) {
        log.info("GET /api/pedidos/estado/{} - Filtrando pedidos por estado", estado);
        return ResponseEntity.ok(pedidoService.obtenerPorEstado(estado));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Pedido>> obtenerPorCategoria(@PathVariable String categoria) {
        log.info("GET /api/pedidos/categoria/{} - Filtrando pedidos por categoria", categoria);
        return ResponseEntity.ok(pedidoService.obtenerPorCategoria(categoria));
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@Valid @RequestBody PedidoDTO pedidoDTO) {
        log.info("POST /api/pedidos - Creando nuevo pedido para cliente: {}", pedidoDTO.getNombreCliente());
        Pedido creado = pedidoService.crear(pedidoDTO);
        log.info("Pedido creado con id: {}", creado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        log.info("PUT /api/pedidos/{} - Actualizando pedido", id);
        return pedidoService.actualizar(id, pedidoDTO)
                .map(p -> {
                    log.info("Pedido {} actualizado correctamente", id);
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/pedidos/{} - Eliminando pedido", id);
        if (pedidoService.eliminar(id)) {
            log.info("Pedido {} eliminado correctamente", id);
            return ResponseEntity.noContent().build();
        }
        log.warn("Pedido {} no encontrado para eliminar", id);
        return ResponseEntity.notFound().build();
    }
}

