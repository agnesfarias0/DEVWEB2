package devweb2.av1.supermercado.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nome")
    private String nome;

    @Setter
    @Getter
    @Column(name = "cpf")
    private String cpf;

    @Setter
    @Getter
    @Column(name = "dataNascimento")
    private String dataNascimento;

    @Setter
    @Getter
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public enum Genero {
        MASCULINO,
        FEMININO
    }

    @Getter
    @Setter
    private boolean ativo = true;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String nome, String cpf, String dataNascimento, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

}
