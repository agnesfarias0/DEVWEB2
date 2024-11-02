package devweb2.av1.supermercado.controller;

import devweb2.av1.supermercado.DTO.ProdutoDTO;
import devweb2.av1.supermercado.entity.ProdutoEntity;
import devweb2.av1.supermercado.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        var produtos = produtoRepository.findAll().stream().filter(ProdutoEntity::isAtivo);
        return ResponseEntity.ok(produtos.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(@PathVariable Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            if(produto.get().isAtivo()) {
                return ResponseEntity.ok(produto.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> postProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produtoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {

        var produtoEntity = produtoRepository.findById(id);

        if(produtoEntity.isPresent()) {
            if(produtoEntity.get().isAtivo()) {
                var produto = produtoEntity.get();

                if(produtoDTO.nomeProduto() != null){
                    produto.setNomeProduto(produtoDTO.nomeProduto());
                }

                if(produtoDTO.marca() != null){
                    produto.setMarca(produtoDTO.marca());
                }

                if(produtoDTO.dataFabricacao() != null){
                    produto.setDataFabricacao(produtoDTO.dataFabricacao());
                }

                if(produtoDTO.dataValidade() != null){
                    produto.setDataValidade(produtoDTO.dataValidade());
                }

                if(produtoDTO.lote() != null){
                    produto.setLote(produtoDTO.lote());
                }

                if(produtoDTO.genero() != null){
                    produto.setGenero(produtoDTO.genero());
                }
                produtoRepository.save(produto);
            }
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {

        var produtoExiste = produtoRepository.existsById(id);

        if(produtoExiste){
            produtoRepository.deleteById(id);
        }

    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable Long id) {
        var produtoExiste = produtoRepository.findById(id);

        if(produtoExiste.isPresent()){
            var produto = produtoExiste.get();
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }


}
