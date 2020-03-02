package main.api.response.generalRespons;



/*
Общие данные блога - GET /api/init/
Метод возвращает общую информацию о блоге: название блога и подзаголовок
для размещения в хэдере сайта, а также номер телефона, e-mail и информацию об
авторских правах для размещения в футере.

 */
public class InitApiResponse {

     private String title;
     private String subtitle;
     private String phone;
     private String email;
     private String copyright;
     private String copyrightFrom;

    public InitApiResponse() {
    }

    public InitApiResponse(String title, String subtitle, String phone, String email, String copyright, String copyrightFrom) {
        this.title = title;
        this.subtitle = subtitle;
        this.phone = phone;
        this.email = email;
        this.copyright = copyright;
        this.copyrightFrom = copyrightFrom;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightFrom() {
        return copyrightFrom;
    }
    public void setCopyrightFrom(String copyrightFrom) {
        this.copyrightFrom = copyrightFrom;
    }
}
