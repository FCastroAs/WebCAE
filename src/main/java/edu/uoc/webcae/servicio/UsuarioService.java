package edu.uoc.webcae.servicio;

import edu.uoc.webcae.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listarUsuario();

    void guardar(Usuario usuario);

    void eliminar(Usuario usuario);

    Usuario encontrarUsuario(Usuario usuario);

}
