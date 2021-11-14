package fr.weeab.mangalist.webapp.transform.mapper;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.webapp.transform.dto.GenreDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public abstract class GenreMapper {

    public abstract List<GenreDTO> toDTOs(List<Genre> genres);
}
