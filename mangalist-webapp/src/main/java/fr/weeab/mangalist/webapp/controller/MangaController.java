package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.core.annotation.NewInnerTx;
import fr.weeab.mangalist.core.annotation.ReadTx;
import fr.weeab.mangalist.core.annotation.Tx;
import fr.weeab.mangalist.core.service.MangaService;
import fr.weeab.mangalist.core.transform.dto.MangaDTO;
import fr.weeab.mangalist.core.transform.dto.MangaSaveDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.MangaPagedListDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagedListDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/mangas")
public class MangaController {

    final private MangaService mangaService;
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<PagedListDTO<MangaDTO>> getMangas(PagerDTO pager) {
        return new ResponseEntity<>(this.mangaService.findAll(pager), HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<PagedListDTO<MangaDTO>> search(MangaPagedListDTO pagedSearchDTO) {
        return new ResponseEntity<>(this.mangaService.findAllSearched(pagedSearchDTO.getCriteria(), pagedSearchDTO.getPager()), HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<MangaDTO> getManga(@PathVariable Long id) {
        return new ResponseEntity<>(this.mangaService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @NewInnerTx
    public ResponseEntity<?> createManga(@RequestBody MangaDTO dto) {
        MangaSaveDTO manga = this.mangaService.save(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + manga.getId().toString()).build().toUri()).build();
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tx
    public ResponseEntity<?> deleteManga(@PathVariable Long id) {
        this.mangaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
