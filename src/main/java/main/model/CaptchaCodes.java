package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CaptchaCodes { //коды капч

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private Date time;         // дата и время генерации кода капчи

    @Column(nullable = false)
    private String code;       // код, отображаемый на картинкке капчи

    @Column(nullable = false)
    private String secretCode; // код, передаваемый в параметре




    public CaptchaCodes(int id, Date time, String code, String secretCode) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
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
