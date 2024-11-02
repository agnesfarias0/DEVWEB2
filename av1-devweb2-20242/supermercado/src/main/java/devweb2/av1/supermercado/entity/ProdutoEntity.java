package devweb2.av1.supermercado.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Setter
    @Getter
    @Column(name = "marca")
    private String marca;

    @Getter
    @Setter
    @Column(name = "dataFabricacao")
    private String dataFabricacao;

    @Setter
    @Getter
    @Column(name = "dataValidade")
    private String dataValidade;

    @Setter
    @Getter
    @Column(name = "lote")
    private String lote;

    @Setter
    @Getter
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public enum Genero {
        COSMETICO,
        ALIMENTICIO,
        HIGIENE_PESSOAL,
        LIMPEZA
    }

    @Getter
    @Setter
    private boolean ativo = true;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id, String nomeProduto, String marca, String dataFabricacao, String dataValidade, String lote, Genero genero) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.lote = lote;
        this.genero = genero;
    }

    public ProdutoEntity(Long id, String nomeProduto, String marca, String dataFabricacao, String lote, Genero genero, boolean ativo) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.dataFabricacao = dataFabricacao;
        this.lote = lote;
        this.genero = genero;
        this.ativo = ativo;
    }
}
