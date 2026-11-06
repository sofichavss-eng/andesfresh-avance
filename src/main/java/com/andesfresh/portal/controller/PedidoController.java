package com.andesfresh.portal.controller;

import com.andesfresh.portal.entity.DetallePedido;
import com.andesfresh.portal.entity.Pedido;
import com.andesfresh.portal.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.readAll());
        return "pedidos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Pedido pedido = new Pedido();
        pedido.addDetalle(new DetallePedido());
        model.addAttribute("pedido", pedido);
        return "pedidos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoService.create(pedido);
        return "redirect:/pedidos";
    }
}