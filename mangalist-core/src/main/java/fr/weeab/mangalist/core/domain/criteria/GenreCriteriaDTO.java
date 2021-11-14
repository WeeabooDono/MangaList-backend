package fr.weeab.mangalist.core.domain.criteria;

public class GenreCriteriaDTO implements CriteriaDTO {
    private static final long serialVersionUID = 2149814794194684406L;

    private Long mangaId;

    private String name;

    public GenreCriteriaDTO() {
        this.mangaId = null;
        this.name = "";
    }

    public Long getMangaId() {
        return mangaId;
    }

    public void setMangaId(Long mangaId) {
        this.mangaId = mangaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
