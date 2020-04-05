package main.api.request;

import main.api.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public class ModifiedProfileRequestApi implements CommonResponse {

    private MultipartFile photo;
    private byte removePhoto; // указывает на то, что фотографию нужно удалить (если значение равно 1)
    private String name;
    private String email;
    private String password;



    public ModifiedProfileRequestApi() {
    }

    public ModifiedProfileRequestApi(MultipartFile photo, byte removePhoto, String name, String email, String password) {
        this.photo = photo;
        this.removePhoto = removePhoto;
        this.name = name;
        this.email = email;
        this.password = password;
    }




    public MultipartFile getPhoto() {
        return photo;
    }
    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public byte getRemovePhoto() {
        return removePhoto;
    }
    public void setRemovePhoto(byte removePhoto) {
        this.removePhoto = removePhoto;
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
}
