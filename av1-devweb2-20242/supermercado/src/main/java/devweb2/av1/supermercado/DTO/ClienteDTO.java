package devweb2.av1.supermercado.DTO;

import devweb2.av1.supermercado.entity.ClienteEntity;

public record ClienteDTO(
        String nome,
        String cpf,
        String dataNascimento,
        ClienteEntity.Genero genero) {
}
