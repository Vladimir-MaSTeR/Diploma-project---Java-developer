package main.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CaptchaCodes { //коды капч

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime time;         // дата и время генерации кода капчи

    @Column(nullable = false)
    private String code;       // код, отображаемый на картинкке капчи

    @Column(nullable = false)
    private String secretCode; // код, передаваемый в параметре


    public CaptchaCodes() {
    }

    public CaptchaCodes(LocalDateTime time, String code, String secretCode) {
      //  this.id = id;
        this.time = time;
        this.code = code;
        this.secretCode = secretCode;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getSecretCode() {
        return secretCode;
    }
    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

}
