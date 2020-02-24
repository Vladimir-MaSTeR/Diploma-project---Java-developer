package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private  boolean isModerator; // является ли пользователь модератором

    @Column(nullable = false)
    private Date regTime;         // дата и время регистрации пользователя

    @Column(nullable = false)
    private String name;          // имя пользователя

    @Column(nullable = false)
    private String email;         // e-mail пользователя

    @Column(nullable = false)
    private String password;      // хэш пароля пользователя

    private String code;          // код для восстановления пароля

    private String photo;         // фотография (ссылка на файл)




    public Users(int id, boolean isModerator, Date regTime, String name, String email, String password, String code, String photo) {
        this.id = id;
        this.isModerator = isModerator;
        this.regTime = regTime;
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.photo = photo;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isModerator() {
        return isModerator;
    }
    public void setModerator(boolean moderator) {
        isModerator = moderator;
    }

    public Date getRegTime() {
        return regTime;
    }
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
