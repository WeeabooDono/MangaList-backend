package fr.weeab.mangalist.core.domain.criteria;

public class MangaCriteriaDTO implements CriteriaDTO {

    private static final long serialVersionUID = 3779912265757960070L;

    private String title;

    public MangaCriteriaDTO() {
    }

    public MangaCriteriaDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
