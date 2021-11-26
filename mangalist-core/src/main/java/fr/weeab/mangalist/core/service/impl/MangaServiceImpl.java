package fr.weeab.mangalist.core.service.impl;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import fr.weeab.mangalist.core.exception.NotFoundException;
import fr.weeab.mangalist.core.repository.GenreRepository;
import fr.weeab.mangalist.core.repository.MangaRepository;
import fr.weeab.mangalist.core.repository.specification.MangaSpecification;
import fr.weeab.mangalist.core.service.MangaService;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;
import fr.weeab.mangalist.core.transform.dto.MangaDTO;
import fr.weeab.mangalist.core.transform.dto.MangaSaveDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagedListDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagerDTO;
import fr.weeab.mangalist.core.transform.mapper.GenreMapper;
import fr.weeab.mangalist.core.transform.mapper.MangaMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static fr.weeab.mangalist.core.exception.NotFoundException.NotFoundExceptionType.MANGA_NOT_FOUND;

@Service
public class MangaServiceImpl implements MangaService {

    private final MangaRepository mangaRepository;
    private final GenreRepository genreRepository;
    final private MangaMapper mangaMapper;
    final private GenreMapper genreMapper;

    public MangaServiceImpl(MangaRepository mangaRepository, GenreRepository genreRepository, MangaMapper mangaMapper, GenreMapper genreMapper) {
        this.mangaRepository = mangaRepository;
        this.genreRepository = genreRepository;
        this.mangaMapper = mangaMapper;
        this.genreMapper = genreMapper;
    }

    public PagedListDTO<MangaDTO> findAll(PagerDTO pager) {
        Page<Manga> mangas = this.mangaRepository.findAll(pager.toPageRequest());
        return new PagedListDTO<MangaDTO>(this.mangaMapper.toDTOs(mangas.getContent()), mangas.getTotalElements(), pager);
    }

    public PagedListDTO<MangaDTO> findAllSearched(MangaCriteriaDTO criteria, PagerDTO pager) {
        Page<Manga> mangas = this.mangaRepository.findAll(MangaSpecification.getMangasByTitle(criteria), pager.toPageRequest());
        return new PagedListDTO<MangaDTO>(this.mangaMapper.toDTOs(mangas.getContent()), mangas.getTotalElements(), pager);
    }

    public MangaDTO findById(Long id) {
        Manga manga = this.mangaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MANGA_NOT_FOUND));
        return this.mangaMapper.toDTO(manga);
    }

    public MangaSaveDTO save(MangaDTO dto) {
        // detach genres from entity
        // Get genres by ID in hibernate context to add it and not create it
        Set<Genre> genres = Set.copyOf(this.genreRepository.findAllById(dto.getGenres()
                .stream()
                .map(GenreSaveDTO::getId)
                .collect(Collectors.toSet())));
        Manga manga = this.mangaMapper.toEntity(dto);
        manga.setGenres(genres);
        this.mangaRepository.save(manga);

        return this.mangaMapper.toDTO(manga);
    }

    public Manga update(Long id, Manga entity) {
        Manga mangaToUpdate = this.mangaRepository.getById(id);
        if (entity.getAuthor() != null) mangaToUpdate.setAuthor(entity.getAuthor());
        if (entity.getDescription() != null) mangaToUpdate.setDescription(entity.getDescription());
        if (entity.getImage() != null) mangaToUpdate.setImage(entity.getImage());
        if (entity.getTitle() != null) mangaToUpdate.setTitle(entity.getTitle());

        return this.mangaRepository.save(mangaToUpdate);
    }

    public void deleteById(Long id) {
        if (!this.mangaRepository.existsById(id)) {
            throw new NotFoundException(MANGA_NOT_FOUND);
        }

        this.mangaRepository.deleteMangaById(id);
    }
}
