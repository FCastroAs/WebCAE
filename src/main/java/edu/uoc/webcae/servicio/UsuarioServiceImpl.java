package edu.uoc.webcae.servicio;

import java.util.ArrayList;
import java.util.List;

import edu.uoc.webcae.dao.UsuarioDao;
import edu.uoc.webcae.domain.Rol;
import edu.uoc.webcae.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    public Usuario findByUsername(String username){
        return usuarioDao.findByUsername(username);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioDao.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

}
