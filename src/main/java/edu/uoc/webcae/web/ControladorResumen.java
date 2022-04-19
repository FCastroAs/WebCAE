package edu.uoc.webcae.web;

import edu.uoc.webcae.domain.Cliente;
import edu.uoc.webcae.domain.Persona;
import edu.uoc.webcae.domain.Usuario;
import edu.uoc.webcae.servicio.ClienteService;
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
public class ControladorResumen {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        Usuario usuario = usuarioServiceImpl.findByUsername(user.getUsername());
        var clientes = usuario.getClientes();
        model.addAttribute("clientes", clientes);
        return "resumen";
    }

    @GetMapping("/agregar")
    public String agregar(Cliente cliente){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Cliente cliente, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        clienteService.guardar(cliente);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Cliente cliente, Model model){
        cliente = clienteService.encontrarCliente(cliente);
        model.addAttribute("persona", cliente);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Cliente cliente){
        clienteService.eliminar(cliente);
        return "redirect:/";
    }
}
