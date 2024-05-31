package com.olabi.olabiflix.controller;

import com.olabi.olabiflix.model.entity.Filme;
import com.olabi.olabiflix.repository.FilmeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Query Params
    @GetMapping("/busca-title") // GET - Busca por título
    public ResponseEntity<Filme> findByTitle(@RequestParam(name = "title", defaultValue = "") String title) {
        Optional<Filme> filmeEncontrado = repository.findByTitle(title);
        if (filmeEncontrado.isPresent()) {
            Filme filme = filmeEncontrado.get();
            return ResponseEntity.ok(filme);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/busca-genero") // GET - Busca por gênero
    public ResponseEntity<List<Filme>> findByGenre(@RequestParam(name = "genre", defaultValue = "") String genre) {
        List<Filme> filmes = repository.findByGenreContainsIgnoreCase(genre);
        return ResponseEntity.ok(filmes);
    }

    @PostMapping("/criar") // POST - Cadastro de Filme
    public Filme create(@RequestBody Filme filmeBody){
        return repository.save(filmeBody);
    }

    @DeleteMapping("/{id}/delete") // DELETE - Exclusão de Filmes
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /* Utilizamos o void pois não retornaremos nada, apenas apagaremos */
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }

}

/*
Path params : Rotas de identificação
 - Exemplo: netflix.com/filmes/suspense/{id}

Query params : Filtros
 - Exemplo: youtube.com/watch?v=IdDoVideo&ab_channel=MeuCanal

    ? identifica query params
    & concatena os operadores
    % é o espaço

Utilizamos o tipo Optional pois a rota pode ter ou não o atributo.
Não queremos o erro NullPointer, e sim o retorno Nulo.

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

*/