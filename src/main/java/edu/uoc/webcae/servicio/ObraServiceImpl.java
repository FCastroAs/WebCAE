package edu.uoc.webcae.servicio;

import edu.uoc.webcae.dao.ObraDao;
import edu.uoc.webcae.domain.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraServiceImpl implements ObraService {

    @Autowired
    private ObraDao obraDao;

    @Override
    public void guardar(Obra obra) {
        obraDao.save(obra);
    }
}
