package edu.uoc.webcae.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="obra")
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObra;

    @NotEmpty
    private String nombre;

    private String descripcion;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "obra_documento",
            joinColumns = { @JoinColumn(name = "id_obra") },
            inverseJoinColumns = { @JoinColumn(name = "id_tipo_documento") }
    )
    private Set<TipoDocumento> tiposDocumentos = new HashSet<>();

}
