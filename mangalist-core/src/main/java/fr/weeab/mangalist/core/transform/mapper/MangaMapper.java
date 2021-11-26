package fr.weeab.mangalist.core.transform.mapper;

import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.transform.dto.MangaDTO;
import fr.weeab.mangalist.core.transform.dto.MangaSaveDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public abstract class MangaMapper {

    public abstract Manga toEntity(MangaDTO dto);

    public abstract MangaSaveDTO toDTO(Manga manga);

    public abstract List<MangaDTO> toDTOs(List<Manga> mangas);
}
