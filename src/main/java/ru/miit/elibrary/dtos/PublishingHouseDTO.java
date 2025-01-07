package ru.miit.elibrary.dtos;

import ru.miit.elibrary.models.Book;
import ru.miit.elibrary.models.PublishingHouse;

import java.util.ArrayList;
import java.util.List;

public class PublishingHouseDTO {
    String name;
    Long ordinal;
    List<Long> bookIDs = new ArrayList<>();
    public PublishingHouseDTO(PublishingHouse ph){
        name = ph.getPublishingHouseName();
        ordinal = ph.getPublishingHouseId();
        for (Book book : ph.getBooks()){
            bookIDs.add(book.getBookId());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }

    public List<Long> getBookIDs() {
        return bookIDs;
    }

    public void setBookIDs(List<Long> bookIDs) {
        this.bookIDs = bookIDs;
    }
}
