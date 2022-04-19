package edu.uoc.webcae.servicio;

import edu.uoc.webcae.domain.Cliente;
import edu.uoc.webcae.domain.Usuario;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarClientes();

    List<Cliente> listarClientesByUsuario(Usuario usuario);

    void guardar(Cliente cliente);

    void eliminar(Cliente cliente);

    Cliente encontrarCliente(Cliente cliente);

}
