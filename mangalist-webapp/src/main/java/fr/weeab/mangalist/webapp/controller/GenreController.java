package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.core.annotation.ReadTx;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import fr.weeab.mangalist.core.service.GenreService;
import fr.weeab.mangalist.webapp.transform.dto.GenreDTO;
import fr.weeab.mangalist.webapp.transform.mapper.GenreMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("api/v1/genres")
public class GenreController {

    final private GenreService genreService;

    final private GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<List<GenreDTO>> getGenres(GenreCriteriaDTO criteria) {
        if (criteria.getMangaId() == null) {
            return new ResponseEntity<>(this.genreMapper.toDTOs(this.genreService.findAllSearched(criteria)), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.genreMapper.toDTOs(this.genreService.findAllByMangasIdOrderByNameDesc(criteria)), HttpStatus.OK);
    }
}
