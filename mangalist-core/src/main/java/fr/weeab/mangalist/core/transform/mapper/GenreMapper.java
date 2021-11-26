package fr.weeab.mangalist.core.transform.mapper;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public abstract class GenreMapper {

    public abstract List<GenreSaveDTO> toDTOs(List<Genre> genres);
}
