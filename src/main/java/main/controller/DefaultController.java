package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefaultController {          //для обычных запросов не через API (главная страница - /, в частности)


    // Pattern PRG (Post/Redirect/Get))
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.GET},
                    value =  "/**/{path:[^\\.]*}")
    public String index() {
        return "forward:/";
    }


//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }

}
