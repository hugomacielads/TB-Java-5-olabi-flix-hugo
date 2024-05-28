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

        @GetMapping()
        public List<Serie> getSeries(){
            return repository.findAll();
        }

        @PostMapping("/criar")
        public Serie create(@RequestBody Serie serieBody){
            return repository.save(serieBody);
        }
}
