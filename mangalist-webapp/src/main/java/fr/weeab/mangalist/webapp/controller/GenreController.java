package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.core.annotation.ReadTx;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import fr.weeab.mangalist.core.service.GenreService;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/genres")
public class GenreController {

    final private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<List<GenreSaveDTO>> getGenres(GenreCriteriaDTO criteria) {
        if (criteria.getMangaId() == null) {
            return new ResponseEntity<>(this.genreService.findAllSearched(criteria), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.genreService.findAllByMangasIdOrderByNameDesc(criteria), HttpStatus.OK);
    }
}
