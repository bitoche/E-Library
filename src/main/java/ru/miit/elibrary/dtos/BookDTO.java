package ru.miit.elibrary.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import ru.miit.elibrary.models.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDTO {
    private Long bookId;
    private String bookName;
    private Date releaseDate;
    private Integer pagesNumber;
    private String description;
    private String identifier;
    private BookStatus bookStatus;
    private List<PublishingHouseDTO> publishingHouses = new ArrayList<>();
    private List<AuthorDTO> authors = new ArrayList<>();
    private List<GenreDTO> genres = new ArrayList<>();
    private LanguageDTO language;
    public BookDTO(Book book){
        bookId = book.getBookId();
        bookName = book.getBookName();
        releaseDate = getReleaseDate();
        pagesNumber = book.getPagesNumber();
        description = book.getDescription();
        identifier = book.getIdentifier();
        bookStatus = book.getBookStatus();
        for (PublishingHouse ph : book.getPublishingHouses()){
            publishingHouses.add(new PublishingHouseDTO(ph));
        }
        for (BookAuthor ba : book.getAuthors()){
            authors.add(new AuthorDTO(ba));
        }
        for (BookGenre bg : book.getGenres()){
            genres.add(new GenreDTO(bg));
        }
        language = new LanguageDTO(book.getLanguage());
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(Integer pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public List<PublishingHouseDTO> getPublishingHouses() {
        return publishingHouses;
    }

    public void setPublishingHouses(List<PublishingHouseDTO> publishingHouses) {
        this.publishingHouses = publishingHouses;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }
}
