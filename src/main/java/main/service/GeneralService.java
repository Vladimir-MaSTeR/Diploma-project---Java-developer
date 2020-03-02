package main.service;

import main.api.response.generalRespons.InitApiResponse;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    //Общие данные блога для размещения в хедере и футере сайта | ГОТОВ
    public InitApiResponse generalBlogData() {

        InitApiResponse apiResponse = new InitApiResponse(
                "devPub",
                "Рассказы разработчиков",
                "+7 950 404 0747",
                "tsyuman87binary@gmail.com",
                "Цюман Владимир",
                "2020");

        return apiResponse;
    }

}
