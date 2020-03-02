package main.controller;

import main.api.response.generalRespons.InitApiResponse;
import main.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {      //для прочих запросов к API.

    @Autowired
    private GeneralService service;



    @GetMapping("/init")
    @ResponseStatus(HttpStatus.OK)
    public InitApiResponse generalBlogData() { //Общие данные блога для размещения в хедере и футере сайта | ГОТОВ

        return service.generalBlogData();
    }

}
