package com.andesfresh.portal.service;

import com.andesfresh.portal.entity.DetallePedido;
import com.andesfresh.portal.entity.Pedido;
import com.andesfresh.portal.repository.PedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido create(Pedido pedido) {
        if (pedido.getDetalles() != null) {
            for (DetallePedido detalle : pedido.getDetalles()) {
                detalle.setPedido(pedido);
            }
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> readAll() {
        return pedidoRepository.findAll();
    }
}