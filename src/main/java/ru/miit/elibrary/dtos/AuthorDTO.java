package ru.miit.elibrary.dtos;

import ru.miit.elibrary.models.Book;
import ru.miit.elibrary.models.BookAuthor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDTO {
    Long authorId;
    String identifier;
    String fullName;
    Date dob;
    List<Long> bookIDs = new ArrayList<>();
    public AuthorDTO(BookAuthor ba){
        authorId = ba.getAuthorId();
        identifier = ba.getIdentifier();
        fullName = ba.getFirstName() + " ";
        if (ba.getThirdName() != null){
            fullName += ba.getThirdName() + " ";
        }
        fullName += ba.getSecondName();
        dob = ba.getBirthDate();
        for (Book book : ba.getBooks()){
            bookIDs.add(book.getBookId());
        }
    }
}
