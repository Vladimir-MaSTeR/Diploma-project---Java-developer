package main.controller;

import main.api.request.ModifiedProfileRequestApi;
import main.api.response.CommonResponse;
import main.api.response.generalRespons.GeneralSettingsResponse;
import main.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {      //для прочих запросов к API.

    @Autowired
    private GeneralService service;


    @GetMapping("/init")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse generalBlogData() { //Общие данные блога для размещения в хедере и футере сайта | ГОТОВ

        return service.generalBlogData();
    }


    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK) // Метод загружает на сервер изображение в папку “upload” и три случайные подпапки.
    public String loadingImage(@RequestParam MultipartFile file) {
        return service.loadingImage(file);
    }

    @PostMapping("/profile/my")
    @ResponseStatus(HttpStatus.OK) // Редактирование моего профиля
    public CommonResponse modifiedProfile(@ModelAttribute ModifiedProfileRequestApi modifiedProfileRequestApi) {
        return service.modifiedProfile(modifiedProfileRequestApi);
    }

    @GetMapping("/statistics/my")
    @ResponseStatus(HttpStatus.OK) // Метод возвращает статистику постов текущего авторизованного пользователя
    public CommonResponse myStatistics() {
        return service.myStatistics();
    }

    @GetMapping("/statistics/all")
    @ResponseStatus(HttpStatus.OK) // Метод выдаёт статистику по всем постам блога
    public CommonResponse allStatistics() {
        return (CommonResponse) service.allStatistics();
    }

    @GetMapping("/settings")
    @ResponseStatus(HttpStatus.OK) // Метод возвращает глобальные настройки блога из таблицы global_settings
    public Object getSettings() {
        return service.getSettings();
    }

    @PutMapping("/settings") // Метод записывает глобальные настройки блога в таблицу global_settings
    public ResponseEntity saveSettings(@RequestBody GeneralSettingsResponse generalSettingsResponse) {
        return service.saveSettings(generalSettingsResponse);
    }

    @GetMapping("/tag/")
    @ResponseStatus(HttpStatus.OK) // Получение списка тэгов
    public CommonResponse getTag(@RequestParam String query) {
        return service.getTag(query);
    }

    @PostMapping("/comment/")
    @ResponseStatus(HttpStatus.OK) // Метод добавляет комментарий к посту.
    public Object addCommentToPost(@RequestParam(required = false) int parentId, @RequestParam int postId, @RequestParam String text) {
        return service.addCommentToPost(parentId, postId, text);
    }

    @PostMapping("/moderation")
    @ResponseStatus(HttpStatus.OK) // Модерация поста
    public ResponseEntity moderationPost(@RequestParam int postId, @RequestParam String decision) {
        return service.moderationPost(postId, decision);
    }

    @GetMapping("/calendar")
    @ResponseStatus(HttpStatus.OK) //Метод выводит количества публикаций на каждую дату переданного в параметре year
    public CommonResponse getCalendar(@RequestParam String year) {
        return service.getCalendar(year);
    }
}
