package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.core.annotation.NewInnerTx;
import fr.weeab.mangalist.core.annotation.ReadTx;
import fr.weeab.mangalist.core.annotation.Tx;
import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.service.MangaService;
import fr.weeab.mangalist.webapp.transform.dto.MangaDTO;
import fr.weeab.mangalist.webapp.transform.dto.MangaSaveDTO;
import fr.weeab.mangalist.webapp.transform.dto.pagination.MangaPagedListDTO;
import fr.weeab.mangalist.webapp.transform.dto.pagination.PagedListDTO;
import fr.weeab.mangalist.webapp.transform.dto.pagination.PagerDTO;
import fr.weeab.mangalist.webapp.transform.mapper.MangaMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/mangas")
public class MangaController {

    final private MangaService mangaService;

    final private MangaMapper mangaMapper;

    public MangaController(MangaService mangaService, MangaMapper mangaMapper) {
        this.mangaService = mangaService;
        this.mangaMapper = mangaMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<PagedListDTO<MangaDTO>> getMangas(PagerDTO pager) {
        Page<Manga> mangas = this.mangaService.findAll(pager.toPageRequest());
        PagedListDTO<MangaDTO> mangaPagedList = new PagedListDTO<MangaDTO>(this.mangaMapper.toDTOs(mangas.getContent()), mangas.getTotalElements(), pager);
        return new ResponseEntity<>(mangaPagedList, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<PagedListDTO<MangaDTO>> search(MangaPagedListDTO pagedSearchDTO) {
        Page<Manga> mangas = this.mangaService.findAllSearched(pagedSearchDTO.getCriteria(), pagedSearchDTO.getPager().toPageRequest());
        PagedListDTO<MangaDTO> mangaPagedList = new PagedListDTO<MangaDTO>(this.mangaMapper.toDTOs(mangas.getContent()), mangas.getTotalElements(), pagedSearchDTO.getPager());
        return new ResponseEntity<>(mangaPagedList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ReadTx
    public ResponseEntity<MangaDTO> getManga(@PathVariable Long id) {
        return new ResponseEntity<>(this.mangaMapper.toDTO(this.mangaService.findById(id)), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @NewInnerTx
    public ResponseEntity<?> createManga(@RequestBody MangaSaveDTO dto) {
        Manga manga = this.mangaService.save(this.mangaMapper.toEntity(dto));
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
