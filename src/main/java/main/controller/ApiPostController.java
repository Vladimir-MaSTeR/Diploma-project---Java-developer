package main.controller;

import main.api.response.postRespons.CommonResponse;
import main.api.response.postRespons.PostApiResponse;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {  //обрабатывает все запросы /api/post/*

   @Autowired
   PostService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK) //Метод получения постов со всей сопутствующей информацией для главной страницы и подразделов
    public CommonResponse listPostAllInfo(@RequestParam int offset, @RequestParam int limit, @RequestParam String mode) {

        return service.listPostAllInfo(offset, limit, mode);
    }


}
