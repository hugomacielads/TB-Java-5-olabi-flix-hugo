package com.olabi.olabiflix.controller;

import com.olabi.olabiflix.model.entity.Filme;
import com.olabi.olabiflix.repository.FilmeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository repository;

    public FilmeController(FilmeRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<Filme> getFilmes(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Filme getFilmeById(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new FilmeNotFoundException(id));
    }

    @PostMapping("/criar")
    public Filme create(@RequestBody Filme filmeBody){
        return repository.save(filmeBody);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class FilmeNotFoundException extends RuntimeException {
    public FilmeNotFoundException(UUID id) {
        super("Não foi possível encontrar o filme com id: " + id);
    }
}