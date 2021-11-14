package fr.weeab.mangalist.webapp.transform.mapper;

import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.webapp.transform.dto.MangaDTO;
import fr.weeab.mangalist.webapp.transform.dto.MangaSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public abstract class MangaMapper {

    @Mapping(target = "genres.mangas", ignore = true)
    public abstract Manga toEntity(MangaSaveDTO dto);

    public abstract MangaDTO toDTO(Manga manga);

    public abstract List<MangaDTO> toDTOs(List<Manga> mangas);
}
