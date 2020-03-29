package main.controller;

import main.api.request.EmailRecoveryRequestApi;
import main.api.request.LoginRequestApi;
import main.api.request.PasswordChangeRequestApi;
import main.api.request.RegisterRequestApi;
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
    public CommonResponse newUser (@RequestBody RegisterRequestApi registerRequestApi) {
        return service.newUser(registerRequestApi);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK) // Метод проверяет введенные данные и производит авторизацию пользователя
    public CommonResponse login(@RequestBody LoginRequestApi loginRequestApi) {
        return service.login(loginRequestApi);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK) // Метод возвращает информацию о текущем авторизованном пользователе, если он авторизован.
    public CommonResponse check() {
        return service.check();
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK) // Метод разлогинивает пользователя: удаляет идентификатор его сессии из списка авторизованных.
    public CommonResponse logout() {
        return service.logout();
    }

    @PostMapping("/restore")
    @ResponseStatus(HttpStatus.OK) // Отправка письма для востановления пороля
    public CommonResponse passwordRecovery(@RequestBody EmailRecoveryRequestApi emailRecovery) {
        return service.passwordRecovery(emailRecovery);
    }

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.OK) // Отправка письма для востановления пороля
    public CommonResponse passwordChange(@RequestBody PasswordChangeRequestApi passwordChangeRequestApi) {
        return service.passwordChange(passwordChangeRequestApi);
    }




}
