package edu.uoc.webcae.dao;

import edu.uoc.webcae.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraDao extends JpaRepository<Obra, Long> {
}
