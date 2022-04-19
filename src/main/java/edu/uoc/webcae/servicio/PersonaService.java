package edu.uoc.webcae.servicio;

import java.util.List;

import edu.uoc.webcae.domain.Persona;

public interface PersonaService {
    
    List<Persona> listarPersonas();
    
    void guardar(Persona persona);
    
    void eliminar(Persona persona);
    
    Persona encontrarPersona(Persona persona);
}
