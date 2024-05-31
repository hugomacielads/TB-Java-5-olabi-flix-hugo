package com.olabi.olabiflix.controller;

import com.olabi.olabiflix.model.entity.Filme;
import com.olabi.olabiflix.repository.FilmeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository repository;

    public FilmeController(FilmeRepository repository) {
        this.repository = repository;
    }

    @GetMapping() // GET - Lista de filmes
    public List<Filme> getFilmes(){
        return repository.findAll();
    }

    @GetMapping("/{id}") // GET - Busca por Id
    public Optional<Filme> getById(@PathVariable UUID id){
        return repository.findById(id);
    }



    @PostMapping("/criar") // POST - Cadastro de Filme
    public Filme create(@RequestBody Filme filmeBody){
        return repository.save(filmeBody);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /* Utilizamos o void pois não retornaremos nada, apenas apagaremos */
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }

}

/*
 * Path params : GetById
 *  /filmes/suspense/{id}
 * Mais utilizados em rotas de identificação
 *
 * Query params : Filtros
 *  youtube.com/watch?v=IdDoVideo&ab_channel=MeuCanal
 *
 * ? identifica query params
 * & concatena os operadores
 * % é com espaço
 *
 * Body :
 * */

    /*
    Utilizamos o tipo Optional pois a rota pode ter ou não o atributo.
    Não queremos o erro NullPointer

    *Criado com IA*
    @GetMapping("/{id}")
    public Filme getFilmeById(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new FilmeNotFoundException(id));
    }

    public Filme getFilmesById(@PathVariable*value = "id") UUID id) {
        return repository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class FilmeNotFoundException extends RuntimeException {
    public FilmeNotFoundException(UUID id) {
        super("Não foi possível encontrar o filme com id: " + id);
    }
} */