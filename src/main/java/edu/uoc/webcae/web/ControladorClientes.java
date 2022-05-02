package edu.uoc.webcae.web;

import edu.uoc.webcae.domain.Cliente;
import edu.uoc.webcae.domain.Obra;
import edu.uoc.webcae.domain.Usuario;
import edu.uoc.webcae.servicio.ClienteService;
import edu.uoc.webcae.servicio.ObraService;
import edu.uoc.webcae.servicio.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ControladorClientes {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ObraService obraService;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/clientes")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        Usuario usuario = usuarioServiceImpl.findByUsername(user.getUsername());
        var clientes = usuario.getClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/clientes/agregar")
    public String agregar(Cliente cliente){
        return "ficha_cliente";
    }

    @PostMapping("/clientes/guardar")
    public String guardar(@Valid Cliente cliente, Errors errores, @AuthenticationPrincipal User user){
        if(errores.hasErrors()){
            return "ficha_cliente";
        }

        Cliente clienteGuardar;

        if (cliente.getIdCliente() != null){
            clienteGuardar = clienteService.encontrarCliente(cliente);
        } else {
            clienteGuardar = cliente;
            clienteGuardar.setUsuario(usuarioServiceImpl.findByUsername(user.getUsername()));
        }
        clienteGuardar.setNombre(cliente.getNombre());
        clienteGuardar.setCif(cliente.getCif());
        clienteGuardar.setResponsable(cliente.getResponsable());
        clienteGuardar.setEmail(cliente.getEmail());

        clienteService.guardar(clienteGuardar);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{idCliente}")
    public String editar(Cliente cliente, Model model){
        cliente = clienteService.encontrarCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "ficha_cliente";
    }

    @GetMapping("/clientes/eliminar")
    public String eliminar(Cliente cliente){
        clienteService.eliminar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/nueva_obra/{idCliente}")
    public String nuevaObra(Cliente cliente, Model model){
        cliente = clienteService.encontrarCliente(cliente);
        Obra obra = new Obra();
        obra.setCliente(cliente);
        model.addAttribute("cliente", cliente);
        model.addAttribute("obra", obra);
        return "ficha_obra";
    }

    @PostMapping("/clientes/guardar_obra")
    public String guardar(@Valid Obra obra, Model model, Errors errores, @AuthenticationPrincipal User user){
        if(errores.hasErrors()){
            return "ficha_obra";
        }

        obraService.guardar(obra);
        return "redirect:/clientes";
    }

}
