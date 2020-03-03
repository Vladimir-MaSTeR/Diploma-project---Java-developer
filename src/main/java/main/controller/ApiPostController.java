package main.controller;

import main.api.response.postRespons.PostApiResponse;
import main.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {  //обрабатывает все запросы /api/post/*

   PostService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK) //Метод получения постов со всей сопутствующей информацией для главной страницы и подразделов
    public PostApiResponse listPostAllInfo(@RequestParam int offset, int limit, String mode) {

        return service.listPostAllInfo(offset, limit, mode);
    }


}
