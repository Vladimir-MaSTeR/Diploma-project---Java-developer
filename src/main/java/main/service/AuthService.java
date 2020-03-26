package main.service;

import com.github.cage.Cage;
import main.api.response.authRespons.CaptchaResponse;
import main.api.response.authRespons.ErrorsNewUserEmail;
import main.api.response.authRespons.NewUserResponse;
import main.model.CaptchaCodes;
import main.model.User;
import main.repository.CaptchaCodesRepository;
import main.repository.UserRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    CaptchaCodesRepository captchaRepository;
    @Autowired
    UserRepository userRepository;

    // finished
    public CaptchaResponse getCaptcha() {
        final int STRING_SIZE_CODE = 6;
        final int STRING_SIZE_SECRET_CODE = 9;
        final int SIZE_IMAGE_HEIGHT = 115;
        final int SIZE_IMAGE_WIDTH = 70;

        Cage cageCode = new Cage();
        byte[] codeBase64 = new byte[0];

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            Thumbnails.of(cageCode.drawImage(captchaGenerateString(STRING_SIZE_CODE)))
                    .size(SIZE_IMAGE_HEIGHT, SIZE_IMAGE_WIDTH)
                    .outputFormat("png")
                    .toOutputStream(byteArrayOutputStream);
            codeBase64 = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        captchaRepository.save(new CaptchaCodes(LocalDateTime.now(), new String(codeBase64, StandardCharsets.UTF_8), captchaGenerateString(STRING_SIZE_SECRET_CODE)));

        return new CaptchaResponse(captchaGenerateString(STRING_SIZE_SECRET_CODE), "data:image/png;base64, " + new String(codeBase64, StandardCharsets.UTF_8));
    }

    final String captchaGenerateString(int numMs) {

        /*
         'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z',
         */
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        char[] index = new char[numMs];

        Random r = new Random();
        int i = 0;

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }

    // not ready | work
    public NewUserResponse newUser(String eMail, String name, String password, String captcha, String captchaSecret) {

         final int MIN_LENGTH_PASSWORD = 6;
         final String NAME_OK = "\\w";

        // String defaultUserName = eMail.substring(0, 5);
 /*

     "email": "Этот e-mail уже зарегистрирован",
         "name": "Имя указано неверно",
         "password": "Пароль короче 6-ти символов",
         "captcha": "Код с картинки введён неверно"
      */

        List<User> eMailList = userRepository.searchEmail(eMail);
        if (eMailList.size() != 0) {
            return new NewUserResponse(false, new ErrorsNewUserEmail("Этот e-mail уже зарегистрирован"));
        }
        if (name.matches(NAME_OK)) {
            return new NewUserResponse(false, new ErrorsNewUserEmail("Имя указано неверно"));
        }
        if (password.length() < MIN_LENGTH_PASSWORD) {
            return new NewUserResponse(false, new ErrorsNewUserEmail("Пароль короче 6-ти символов"));
        }
        List<CaptchaCodes> captchaCodes = captchaRepository.newUserCaptcha(captcha, captchaSecret);
        if (captchaCodes.size() == 0) {
            return new NewUserResponse(false, new ErrorsNewUserEmail("Код с картинки введён неверно"));
        }

        userRepository.save(new User(false, LocalDateTime.now(), name, eMail, password));

        return new NewUserResponse(true);
    }

}
