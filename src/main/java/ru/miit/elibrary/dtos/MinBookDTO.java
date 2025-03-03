package ru.miit.elibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MinBookDTO {
    private Long bookId;
    private String bookName;
    private Date releaseDate;
    private Integer pagesNumber;
    private String description;
    private String identifier;
    private Set<String> authorNames;
    private Set<String> genreNames;
    private Set<String> publishingHouseNames;
    private String languageName;
    private String bookStatusName;
}
