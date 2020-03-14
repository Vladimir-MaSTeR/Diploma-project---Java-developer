package main.controller;

import main.api.response.generalRespons.InitApiResponse;
import main.api.response.postRespons.CommonResponse;
import main.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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

    // ТРЕБУЕТСЯ АВТОРИЗАЦИЯ
    // not ready
    @PostMapping("/image") // Метод загружает на сервер изображение в папку “upload” и три случайные подпапки.
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse loadingImage(File fileImage) {
        return null; // service.loadingImage(fileImage);
    }



}
