package main.controller;

import main.api.response.CommonResponse;
import main.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {    //обрабатывает все запросы /api/auth/*

    @Autowired
    AuthService service;

    @GetMapping("/captcha")
    @ResponseStatus(HttpStatus.OK) // Метод генерирует коды капчи
    public CommonResponse getCaptcha() {
        return service.getCaptcha();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK) // Метод создаёт пользователя в базе данных
    public CommonResponse newUser (@RequestParam(name = "e_mail") String eMail,
                                   @RequestParam String name,
                                   @RequestParam String password,
                                   @RequestParam String captcha,
                                   @RequestParam(name = "captcha_secret") String captchaSecret) {
        return service.newUser(eMail, name, password, captcha, captchaSecret);
    }




}
