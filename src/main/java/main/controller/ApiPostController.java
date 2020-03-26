package main.controller;

import main.api.response.CommonResponse;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {  //обрабатывает все запросы /api/post/*

   @Autowired
   PostService service;


   // finished
    @GetMapping
    @ResponseStatus(HttpStatus.OK) //Метод получения постов со всей сопутствующей информацией для главной страницы и подразделов
    public CommonResponse listPostAllInfo(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam String mode) {

        return service.listPostAllInfo(offset, limit, mode);
    }

    // finished
   @GetMapping("/search")
   @ResponseStatus(HttpStatus.OK) // Метод возвращает посты  соответствующие поисковому запросу
    public CommonResponse searchPosts(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam String query) {
        return service.searchPosts(offset, limit, query);
   }

    // ready | no checking
   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK) // Метод выводит данные конкретного поста
   public CommonResponse getPostId(@PathVariable Integer id) {
        return service.getPostId(id);
   }

    // ready | no checking
    @GetMapping("/byDate")
    @ResponseStatus(HttpStatus.OK) // Выводит посты за конкретную дату
    public CommonResponse getPostsData(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam("date") String date) {
        return service.getPostsData(offset, limit, date);
    }

    // ready | no checking
    @GetMapping("/byTag")
    @ResponseStatus(HttpStatus.OK) // Метод выводит список постов, привязанных к тэгу
    public CommonResponse getPostsTag(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam String tag) {
        return service.getPostsTag(offset, limit, tag);
    }


//-----------------------------------------------------------------------------------------------------
    // ТРЕБУЕТСЯ АВТОРИЗАЦИЯ
    // not ready
    @GetMapping("/moderation")
    @ResponseStatus(HttpStatus.OK) // Метод выводит все посты, которые требуют модерационных действий
    public CommonResponse  getPostsModeration(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam String status) {
        /*
        status - статус модерации:
             ○ new - новые, необходима модерация
             ○ declined - отклонённые мной
             ○ accepted - утверждённые мной
         */

        return null; // service.getPostsModeration(offset, limit, status);
    }

    // ТРЕБУЕТСЯ АВТОРИЗАЦИЯ
    // not ready
    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK) //Метод выводит только те посты, которые создал я
    public CommonResponse getPostsMy(@RequestParam int offset, @RequestParam(defaultValue = "5") int limit, @RequestParam String status) {
        /*
        status - статус модерации:
            ○ inactive - скрытые, ещё не опубликованы (is_active = 0)
            ○ pending - активные, ожидают утверждения модератором (is_active = 1,
            moderation_status = NEW)
            ○ declined - отклонённые по итогам модерации (is_active = 1,
            moderation_status = DECLINED)
            ○ published - опубликованные по итогам модерации (is_active = 1,
            moderation_status = ACCEPTED)

         */
        return null; // service.getPostsMy(offset, limit, status);
    }

    // ТРЕБУЕТСЯ АВТОРИЗАЦИЯ
    // not ready
    @PostMapping
    @ResponseStatus(HttpStatus.OK) // Метод отправляет данные поста, которые пользователь ввёл в форму публикации.
    public CommonResponse addPost(@RequestParam LocalDateTime date, @RequestParam byte active, @RequestParam String title, @RequestParam String text, @RequestParam String tags) {
        return null; // service.addPost(date, active, title, text, tags);
    }

    // ТРЕБУЕТСЯ АВТОРИЗАЦИЯ
    // not ready
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // Метод изменяет данные поста
    public CommonResponse updatePost(@RequestParam LocalDateTime date, @RequestParam byte active, @RequestParam String title, @RequestParam String text, @RequestParam String tags ) {
       return null; // service.updatePost(date, active, title, text, tags);
    }





}
