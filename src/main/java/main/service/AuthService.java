package main.service;

import com.github.cage.Cage;
import main.api.request.EmailRecoveryRequestApi;
import main.api.request.LoginRequestApi;
import main.api.request.PasswordChangeRequestApi;
import main.api.request.RegisterRequestApi;
import main.api.response.authRespons.*;
import main.model.CaptchaCodes;
import main.model.User;
import main.repository.CaptchaCodesRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    private HashMap<String, Integer> authorizedUsersList = new HashMap<>();
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    CaptchaCodesRepository captchaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    // finished
    public CaptchaResponse getCaptcha() {
        final int STRING_SIZE_CODE = 6;
        final int STRING_SIZE_SECRET_CODE = 9;
        final int SIZE_IMAGE_HEIGHT = 115;
        final int SIZE_IMAGE_WIDTH = 70;
        String stringCaptcha = generateString(STRING_SIZE_CODE);
        Cage cageCode = new Cage();
        byte[] codeBase64 = new byte[0];

        //Delete captcha
        List<CaptchaCodes> captchaList = captchaRepository.findAll();
        long timeDeathCaptcha = Long.parseLong(timeDeathCaptcha());

        for (CaptchaCodes codes : captchaList) {
            if (codes.getTime().plusHours(timeDeathCaptcha).getHour() <= LocalDateTime.now().getHour() ||
                    codes.getTime().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()) {
                captchaRepository.delete(codes);
            }
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            Thumbnails.of(cageCode.drawImage(stringCaptcha))
                    .size(SIZE_IMAGE_HEIGHT, SIZE_IMAGE_WIDTH)
                    .outputFormat("png")
                    .toOutputStream(byteArrayOutputStream);
            codeBase64 = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        captchaRepository.save(new CaptchaCodes(LocalDateTime.now(), stringCaptcha, generateString(STRING_SIZE_SECRET_CODE)));

        return new CaptchaResponse(generateString(STRING_SIZE_SECRET_CODE), "data:image/png;base64, " + new String(codeBase64, StandardCharsets.UTF_8));
    }

    final String generateString(int numMs) {

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

    final String timeDeathCaptcha() {
        final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
        FileInputStream fileInputStream;
        //инициализируем специальный объект Properties
        Properties prop = new Properties();
        String timeDeathCaptcha = null;
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            timeDeathCaptcha = prop.getProperty("timeDeathCaptcha");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return timeDeathCaptcha;
    }

    // finished | possible rework of switch Case JDK14
    public ResponseTrueFalseAndObject newUser(RegisterRequestApi registerRequestApi) {

        final int MIN_LENGTH_PASSWORD = 6;
        final String NAME_OK = "\\w";

        String eMail = registerRequestApi.geteMail();
        String name = registerRequestApi.geteMail().substring(0, registerRequestApi.geteMail().indexOf("@"));
        String password = registerRequestApi.getPassword();
        String captcha = registerRequestApi.getCaptcha();
        String captchaSecret = registerRequestApi.getCaptchaSecret();


        List<User> eMailList = userRepository.searchEmail(eMail);
        if (eMailList.size() != 0) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Этот e-mail уже зарегистрирован"));
        }
        if (name.matches(NAME_OK)) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Имя указано неверно"));
        }
        if (password.length() < MIN_LENGTH_PASSWORD) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Пароль короче 6-ти символов"));
        }
        List<CaptchaCodes> captchaCodes = captchaRepository.newUserCaptcha(captcha);
        if (captchaCodes.size() == 0) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Код с картинки введён неверно"));
        }

        userRepository.save(new User(false, LocalDateTime.now(), name, eMail, password));

        return new ResponseTrueFalseAndObject(true);
    }

    // finished
    public ResponseTrueFalseAndObject login(LoginRequestApi loginRequestApi) {

        final String sessionStr = RequestContextHolder.currentRequestAttributes().getSessionId();

        String email = loginRequestApi.getEmail();
        String password = loginRequestApi.getPassword();

        User user = userRepository.searchEmail(email).stream().findFirst().orElse(null);
        //User user = userList.stream().findFirst().orElse(null);

        UserLoginResponse userLoginResponse = null;
        if (user != null && user.isModerator()) {
            userLoginResponse = new UserLoginResponse(user.getId(),
                    user.getName(),
                    user.getPhoto(),
                    user.getEmail(),
                    true,
                    postRepository.postsNewStatus().size(),
                    true);
        } else {
            assert user != null;
            userLoginResponse = new UserLoginResponse(user.getId(),
                    user.getName(),
                    user.getPhoto(),
                    user.getEmail(),
                    false,
                    postRepository.postsNewStatus().size(),
                    false);
        }

        if (!user.getPassword().equals(password)) {
            return new ResponseTrueFalseAndObject(false);
        } else {
            authorizedUsersList.put(sessionStr, user.getId());
        }
        return new ResponseTrueFalseAndObject(true, userLoginResponse);

    }

    // finished
    public ResponseTrueFalseAndObject check() {
        final String sessionStr = RequestContextHolder.currentRequestAttributes().getSessionId();
        UserLoginResponse userLoginResponse = null;

        if (!authorizedUsersList.containsKey(sessionStr)) {
            return new ResponseTrueFalseAndObject(false);
        } else {
            int valueIdUser = authorizedUsersList.get(sessionStr);
            User user = userRepository.getOne(valueIdUser);

            if (user.isModerator()) {
                userLoginResponse = new UserLoginResponse(user.getId(),
                        user.getName(),
                        user.getPhoto(),
                        user.getEmail(),
                        true,
                        postRepository.postsNewStatus().size(),
                        true);
            } else {
                userLoginResponse = new UserLoginResponse(user.getId(),
                        user.getName(),
                        user.getPhoto(),
                        user.getEmail(),
                        false,
                        postRepository.postsNewStatus().size(),
                        false);
            }

        }

        return new ResponseTrueFalseAndObject(true, userLoginResponse);
    }

    // finished
    public ResponseTrueFalseAndObject logout() {
        final String sessionStr = RequestContextHolder.currentRequestAttributes().getSessionId();

        authorizedUsersList.remove(sessionStr);

        return new ResponseTrueFalseAndObject(true);
    }

    // finished
    public ResponseTrueFalseAndObject passwordRecovery(EmailRecoveryRequestApi emailRecovery) {
        final int LENGTH_GENERATE = 30;
        final String HASH_CODE = generateString(LENGTH_GENERATE);
        final String FROM_EMAIL = "devpubsupp@gmail.com";
        final String TITLE_EMAIL = "ВОСТАНОВЛЕНИЕ ПАРОЛЯ";
        final String TEXT_EMAIL = "http://localhost:8080/login/change-password/" + HASH_CODE;
        String email = emailRecovery.getEmail();
        List<User> userList = userRepository.searchEmail(email);

        if (!userList.isEmpty()) {
            User user = userList.stream().findFirst().orElse(null);
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(FROM_EMAIL);
            mailMessage.setTo(email);
            mailMessage.setSubject(TITLE_EMAIL);
            mailMessage.setText(TEXT_EMAIL);

            emailSender.send(mailMessage);

            user.setCode(HASH_CODE);
            userRepository.save(user);
        } else {
            return new ResponseTrueFalseAndObject(false);
        }

        return new ResponseTrueFalseAndObject(true);
    }

    // finished | possible rework of switch Case JDK14
    public ResponseTrueFalseAndObject passwordChange(PasswordChangeRequestApi passwordChangeRequestApi) {
        String code = passwordChangeRequestApi.getCode();
        String password = passwordChangeRequestApi.getPassword();
        String captcha = passwordChangeRequestApi.getCaptcha();
        String captchaSecret = passwordChangeRequestApi.getCaptchaSecret();
        final int MIN_LENGTH_PASSWORD = 6;

        User user = userRepository.searchCode(code).stream().findFirst().orElse(null);
        CaptchaCodes captchaCodes = captchaRepository.newUserCaptcha(captcha).stream().findFirst().orElse(null);

        if (password.length() < MIN_LENGTH_PASSWORD) {
            return new ResponseTrueFalseAndObject(false, new ErrorPassword("Пароль короче 6-ти символов"));
        }
        assert captchaCodes != null;
        if (!captchaCodes.getCode().equals(captcha)) {
            return new ResponseTrueFalseAndObject(false, new ErrorCaptcha("Код с картинки введён неверно"));
        }
        assert user != null;
        if (!user.getCode().equals(code)) {
            return new ResponseTrueFalseAndObject(false, new ErrorCode("Ссылка для восстановления пароля устарела." +
                    "<a href=\"/auth/restore\">Запросить ссылку снова</a>"));
        }
        user.setPassword(password);
        userRepository.save(user);

        return new ResponseTrueFalseAndObject(true);
    }
}
