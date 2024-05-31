package com.olabi.olabiflix.controller;

import com.olabi.olabiflix.model.entity.Serie;
import com.olabi.olabiflix.repository.SerieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieRepository repository;

    public SerieController(SerieRepository repository) {
        this.repository = repository;
    }

    @GetMapping() // GET - Lista de series
    public List<Serie> getSeries(){
        return repository.findAll();
    }

    @GetMapping("/{id}") // GET - Busca por Id
    public Optional<Serie> getById(@PathVariable UUID id){
        return repository.findById(id);
    }

    @GetMapping("/busca-title") // GET - Busca por título
    public ResponseEntity<Serie> findByTitle(@RequestParam(name = "title", defaultValue = "") String title) {
        Optional<Serie> serieEncontrada = repository.findByTitle(title);
        if (serieEncontrada.isPresent()) {
            Serie serie = serieEncontrada.get();
            return ResponseEntity.ok(serie);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Atributo Genero na Entidade Serie é uma lista de strings, talvez não funciona corretamente
    @GetMapping("/busca-genero") // GET - Busca por gênero
    public ResponseEntity<List<Serie>> findByGenre(@RequestParam(name = "genre", defaultValue = "") String genre) {
        List<Serie> series = repository.findByGenreContainsIgnoreCase(genre);
        return ResponseEntity.ok(series);
    }

    @PostMapping("/criar") // POST - Cadastro de Serie
    public Serie create(@RequestBody Serie serieBody){
        return repository.save(serieBody);
    }

    @DeleteMapping("/{id}/delete") // DELETE - Exclusão de Filmes
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }
}

