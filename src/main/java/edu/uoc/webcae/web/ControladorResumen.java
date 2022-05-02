package edu.uoc.webcae.web;

import edu.uoc.webcae.domain.Usuario;
import edu.uoc.webcae.servicio.ClienteService;
import edu.uoc.webcae.servicio.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ControladorResumen {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/resumen")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        Usuario usuario = usuarioServiceImpl.findByUsername(user.getUsername());
        var clientes = usuario.getClientes();
        model.addAttribute("clientes", clientes);
        return "resumen";
    }

}
