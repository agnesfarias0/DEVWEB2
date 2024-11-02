package devweb2.av1.supermercado.DTO;

import devweb2.av1.supermercado.entity.ProdutoEntity;

public record ProdutoDTO(String nomeProduto,
                         String marca,
                         String dataFabricacao,
                         String dataValidade,
                         String lote,
                         ProdutoEntity.Genero genero) {
}
