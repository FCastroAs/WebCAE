package edu.uoc.webcae.dao;

import edu.uoc.webcae.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
