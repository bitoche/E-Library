package ru.miit.elibrary.dtos;

import ru.miit.elibrary.models.BookGenre;

public class GenreDTO {
    String genreName;
    Integer genreOrdinal;
    public GenreDTO(BookGenre bg){
        genreName = bg.getGenreName();
        genreOrdinal = bg.getGenreId();
    }
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Integer getGenreOrdinal() {
        return genreOrdinal;
    }

    public void setGenreOrdinal(Integer genreOrdinal) {
        this.genreOrdinal = genreOrdinal;
    }
}
