package devweb2.av1.supermercado.controller;

import devweb2.av1.supermercado.DTO.ClienteDTO;
import devweb2.av1.supermercado.entity.ClienteEntity;
import devweb2.av1.supermercado.repository.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAll() {
        var clientes = clienteRepository.findAll().stream().filter(ClienteEntity::isAtivo);
        return ResponseEntity.ok(clientes.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            if(cliente.get().isAtivo()) {
                return ResponseEntity.ok(cliente.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> postCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, clienteEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {

        var clienteEntity = clienteRepository.findById(id);

        if(clienteEntity.isPresent()) {
            if(clienteEntity.get().isAtivo()) {
                var cliente = clienteEntity.get();

                if(clienteDTO.nome() != null){
                    cliente.setNome(clienteDTO.nome());
                }

                if(clienteDTO.cpf() != null){
                    cliente.setCpf(clienteDTO.cpf());
                }

                if(clienteDTO.dataNascimento() != null){
                    cliente.setDataNascimento(clienteDTO.dataNascimento());
                }

                if(clienteDTO.genero() != null){
                    cliente.setGenero(clienteDTO.genero());
                }

                clienteRepository.save(cliente);
            }
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {

        var clienteExiste = clienteRepository.existsById(id);

        if(clienteExiste){
            clienteRepository.deleteById(id);
        }

    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable Long id) {
        var clienteExiste = clienteRepository.findById(id);

        if(clienteExiste.isPresent()){
            var cliente = clienteExiste.get();
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
        }
    }
}
