package edu.uoc.webcae.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotEmpty
    private String nombre;

    private String cif;

    private String email;

    private String responsable;

    @OneToMany
    @JoinColumn(name="id_cliente")
    private List<Obra> obras;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
