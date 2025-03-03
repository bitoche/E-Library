package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "entry_code", schema = "public")
public class EntryCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code_id")
    private Long codeId;
    @Column(name="code")
    private String code;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Column(name="expire_dttm", nullable = false)
    private LocalDateTime expireDateTime;
    public EntryCode(@NotNull User user){
        int sixDigitNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        this.code = String.valueOf(sixDigitNumber);
        this.user = user;
        this.expireDateTime = LocalDateTime.now().plusMinutes(20);
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(LocalDateTime expireDateTime) {
        this.expireDateTime = expireDateTime;
    }
}
