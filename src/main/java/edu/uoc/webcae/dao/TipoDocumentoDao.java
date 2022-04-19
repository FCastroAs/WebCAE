package edu.uoc.webcae.dao;

import edu.uoc.webcae.domain.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoDao extends JpaRepository<TipoDocumento, Long> {
}
