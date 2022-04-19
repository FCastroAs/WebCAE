package edu.uoc.webcae.servicio;

import edu.uoc.webcae.dao.ClienteDao;
import edu.uoc.webcae.domain.Cliente;
import edu.uoc.webcae.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientesByUsuario(Usuario usuario) {
        return clienteDao.findByUsuario(usuario);
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
}
