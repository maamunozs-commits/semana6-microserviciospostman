package com.cuidadomascotas.serviciopedidos.service;

import com.cuidadomascotas.serviciopedidos.dto.PedidoDTO;
import com.cuidadomascotas.serviciopedidos.model.Pedido;
import com.cuidadomascotas.serviciopedidos.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        log.debug("Se encontraron {} pedidos", pedidos.size());
        return pedidos;
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        log.debug("Buscando pedido con id: {}", id);
        return pedidoRepository.findById(id);
    }

    public List<Pedido> obtenerPorEstado(String estado) {
        log.debug("Buscando pedidos con estado: {}", estado);
        return pedidoRepository.findByEstadoIgnoreCase(estado);
    }

    public List<Pedido> obtenerPorCategoria(String categoria) {
        log.debug("Buscando pedidos con categoria: {}", categoria);
        return pedidoRepository.findByCategoriaProductoIgnoreCase(categoria);
    }

    public Pedido crear(PedidoDTO dto) {
        log.info("Creando pedido para cliente: {}", dto.getNombreCliente());
        Pedido pedido = new Pedido();
        pedido.setNombreProducto(dto.getNombreProducto());
        pedido.setCategoriaProducto(dto.getCategoriaProducto());
        pedido.setCantidad(dto.getCantidad());
        pedido.setPrecioUnitario(dto.getPrecioUnitario());
        pedido.setPrecioTotal(dto.getPrecioUnitario() * dto.getCantidad());
        pedido.setNombreCliente(dto.getNombreCliente());
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setEstado("Pendiente");
        Pedido guardado = pedidoRepository.save(pedido);
        log.info("Pedido guardado con id: {}", guardado.getId());
        return guardado;
    }

    public Optional<Pedido> actualizar(Long id, PedidoDTO dto) {
        log.info("Actualizando pedido con id: {}", id);
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setNombreProducto(dto.getNombreProducto());
            pedido.setCategoriaProducto(dto.getCategoriaProducto());
            pedido.setCantidad(dto.getCantidad());
            pedido.setPrecioUnitario(dto.getPrecioUnitario());
            pedido.setPrecioTotal(dto.getPrecioUnitario() * dto.getCantidad());
            pedido.setNombreCliente(dto.getNombreCliente());
            pedido.setFechaPedido(dto.getFechaPedido());
            if (dto.getEstado() != null) {
                pedido.setEstado(dto.getEstado());
            }
            return pedidoRepository.save(pedido);
        });
    }

    public boolean eliminar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            log.info("Pedido con id {} eliminado", id);
            return true;
        }
        log.warn("Intento de eliminar pedido inexistente con id: {}", id);
        return false;
    }
}

