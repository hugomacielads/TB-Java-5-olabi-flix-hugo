package com.olabi.olabiflix.controller;

import com.olabi.olabiflix.model.entity.Serie;
import com.olabi.olabiflix.repository.SerieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/busca-title") // GET - Busca por título

    @GetMapping("/busca-genero") // GET - Busca por gênero

    @PostMapping("/criar") // POST - Cadastro de Serie
    public Serie create(@RequestBody Serie serieBody){
        return repository.save(serieBody);
    }

    @DeleteMapping("/{id}/delete") // DELETE - Exclusão de Filmes
}
