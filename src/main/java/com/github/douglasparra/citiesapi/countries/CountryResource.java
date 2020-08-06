package com.github.douglasparra.citiesapi.countries;

import com.github.douglasparra.citiesapi.countries.Country;
import com.github.douglasparra.citiesapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    // Pode usar o @Autowired se não tiver o construtor
    private CountryRepository repository;

    // Injeção de dependência do repositório
    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    // Indica que é um GET
    @GetMapping
    public Page<Country> countries(Pageable page){
        // http://localhost:8080/countries?page=0&size=10&sort=name,asc
        // parâmetro page = 0
        // size de 10
        // sort por name, asc
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<Country> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        }

        return ResponseEntity.notFound().build();
    }

}
