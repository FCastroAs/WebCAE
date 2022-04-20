package edu.uoc.webcae.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="tipo_documento")
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;

    @NotEmpty
    private String nombre;

    private Integer vencimiento;

    @ManyToMany(mappedBy = "tiposDocumentos")
    private Set<Obra> employees = new HashSet<>();
}
