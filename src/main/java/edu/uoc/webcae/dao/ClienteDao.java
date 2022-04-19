package edu.uoc.webcae.dao;

import edu.uoc.webcae.domain.Cliente;
import edu.uoc.webcae.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuario(Usuario usuario);
}
