package ru.miit.elibrary.dtos;

import ru.miit.elibrary.models.BookLanguage;

public class LanguageDTO {
    String languageName;
    Integer languageOrdinal;
    public LanguageDTO(BookLanguage bl){
        languageName = bl.getLanguageName();
        languageOrdinal = bl.getLanguageId();
    }
    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getLanguageOrdinal() {
        return languageOrdinal;
    }

    public void setLanguageOrdinal(Integer languageOrdinal) {
        this.languageOrdinal = languageOrdinal;
    }
}
