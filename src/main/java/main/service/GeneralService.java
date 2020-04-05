package main.service;

import main.api.request.ModifiedProfileRequestApi;
import main.api.response.CommonResponse;
import main.api.response.generalRespons.*;
import main.api.response.errorRespons.ErrorText;
import main.api.response.errorRespons.ErrorsEmail;
import main.api.response.ResponseTrueFalseAndObject;
import main.model.*;
import main.model.enums.ModerationStatus;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@Service
public class GeneralService {

    @Autowired
    CaptchaCodesRepository captchaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostVotesRepository postVotesRepository;
    @Autowired
    GlobalSettingsRepository settingsRepository;
    @Autowired
    TagsRepository tagsRepository;
    @Autowired
    Tag2PostRepository tag2PostRepository;
    @Autowired
    PostCommentsRepository postCommentsRepository;
    @Autowired
    AuthService authService;


    // finished
    public GeneralInitApiResponse generalBlogData() {

        GeneralInitApiResponse apiResponse = new GeneralInitApiResponse(
                "DevPub",
                "Рассказы разработчиков",
                "+7 950 404 0747",
                "tsyuman87binary@gmail.com",
                "Цюман Владимир",
                "2020");

        return apiResponse;
    }

    // not verified |
    public String loadingImage(MultipartFile file) {
        String folderImageUpload1 = "main/resources/upload";
        String folderImageUpload2 = "/" + generateString(2);
        String folderImageUpload3 = "/" + generateString(2);
        String folderImageUpload4 = "/" + generateString(2);

        File fileDir = new File(folderImageUpload1);
        if (fileDir.exists()) {
            new File(folderImageUpload1 + folderImageUpload2 +
                    folderImageUpload3 + folderImageUpload4).mkdirs();
        }
        File output = null;
        try {
            BufferedImage bImage = ImageIO.read((File) file);
            output = new File(generateString(5) + ".jpg");
            ImageIO.write(bImage, "jpg", output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return folderImageUpload1 + folderImageUpload2 + folderImageUpload3 + folderImageUpload4 + output;
    }

    // ready | есть проблемка с аватаркой толи с фронта толи с моей стороны, пока не ясно.
    public ResponseTrueFalseAndObject modifiedProfile(ModifiedProfileRequestApi modifiedProfileRequestApi) {
        MultipartFile photo = modifiedProfileRequestApi.getPhoto();
        byte removePhoto = modifiedProfileRequestApi.getRemovePhoto();
        String name = modifiedProfileRequestApi.getName();
        String email = modifiedProfileRequestApi.getEmail();
        String password = modifiedProfileRequestApi.getPassword();
        final String NAME_OK = "\\w";
        final int MIN_LENGTH_PASSWORD = 6;

        User user = userRepository.getOne(idUserSession());

        if (password.length() >= MIN_LENGTH_PASSWORD) {
            user.setPassword(password);
        } else {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Пароль короче 6-ти символов"));
        }
        if (name != null && name.matches(NAME_OK)) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Имя указано неверно"));
        } else {
            user.setName(name);
        }
        if (user.getEmail().equals(email)) {
            return new ResponseTrueFalseAndObject(false, new ErrorsEmail("Этот e-mail уже зарегистрирован"));
        } else if (email != null) {
            user.setEmail(email);
        }

        if (photo != null && removePhoto != 1) {
            user.setPhoto(photo.getOriginalFilename());
        } else if (removePhoto == 1) {
            user.setPhoto(null);
        }

        userRepository.save(user);

        return new ResponseTrueFalseAndObject(true);
    }

    // finished | пока работает некоректно из за ошибок фронта, ждем обновлений.
    public GeneralStatisticsResponse myStatistics() {
        int postCount = 0;
        int likeCount = 0;
        int dislikeCount = 0;
        int viewsCount = 0;
        String firstPublication = null;

        User user = userRepository.getOne(idUserSession());
        List<Post> allPostUser = postRepository.postsUser(user.getId());
        postCount = allPostUser.size();
        List<PostVotes> listPostVotes = new ArrayList<>();

        allPostUser.sort(Comparator.comparing(Post::getTime));
        firstPublication = allPostUser.get(0).getTime().toString();

        for (Post post : allPostUser) {
            viewsCount += post.getViewCount();
            listPostVotes.add(postVotesRepository.postVotesIdPost(post.getId()).stream().findFirst().orElse(null));
        }
        for (PostVotes votes : listPostVotes) {
            if (votes != null && votes.getValue() == 1) {
                likeCount++;
            }
            if (votes != null && votes.getValue() == -1) {
                dislikeCount++;
            }
        }

        return new GeneralStatisticsResponse(postCount, likeCount, dislikeCount, viewsCount, firstPublication);
    }

    // finished | пока работает некоректно из за ошибок фронта, ждем обновлений.
    public Object allStatistics() {
        final String GLOBAL_SETTINGS_VALUE = "NO";
        final String GLOBAL_SETTINGS_CODE = "STATISTICS_IS_PUBLIC";

        Integer idAuthorized = idUserSession();
        String settingsValue = settingsRepository.codeList(GLOBAL_SETTINGS_CODE).stream().findFirst().get().getValue();

        if (idAuthorized == null && settingsValue.equals(GLOBAL_SETTINGS_VALUE)) {
            return status(UNAUTHORIZED); // возврат ошибки 401
        }

        int postCount = 0;
        int likeCount = 0;
        int dislikeCount = 0;
        int viewsCount = 0;
        String firstPublication = null;

        List<Post> allPostList = postRepository.findAll();
        allPostList.sort(Comparator.comparing(Post::getTime));
        List<PostVotes> allPostVotesList = postVotesRepository.findAll();

        postCount = allPostList.size();
        for (PostVotes votes : allPostVotesList) {
            if (votes != null && votes.getValue() == 1) {
                likeCount++;
            }
            if (votes != null && votes.getValue() == -1) {
                dislikeCount++;
            }
        }
        for (Post post : allPostList) {
            viewsCount += post.getViewCount();
        }

        firstPublication = allPostList.get(0).getTime().toString();

        return new GeneralStatisticsResponse(postCount, likeCount, dislikeCount, viewsCount, firstPublication);
    }

    //  not verified | все работает но есть мнение что со стороны фронта не коректно, возможны правки
    public GeneralSettingsResponse getSettings() {
        final String MULTIUSER_MODE = "MULTIUSER_MODE";
        final String POST_PREMODERATION = "POST_PREMODERATION";
        final String STATISTICS_IS_PUBLIC = "STATISTICS_IS_PUBLIC";

        final String YES = "YES";

        String multiuserModeValue = null;
        String postPremoderationValue = null;
        String statisticsIsPublicValue = null;

        List<GlobalSettings> settingsList = settingsRepository.findAll();

        for (GlobalSettings settings : settingsList) {
            if (settings.getCode().equals(MULTIUSER_MODE)) {
                multiuserModeValue = settings.getValue();
            }
            if (settings.getCode().equals(POST_PREMODERATION)) {
                postPremoderationValue = settings.getValue();
            }
            if (settings.getCode().equals(STATISTICS_IS_PUBLIC)) {
                statisticsIsPublicValue = settings.getValue();
            }
        }

        return new GeneralSettingsResponse(multiuserModeValue != null && multiuserModeValue.equals(YES),
                postPremoderationValue != null && postPremoderationValue.equals(YES),
                statisticsIsPublicValue != null && statisticsIsPublicValue.equals(YES));
    }

    //  not verified | все работает но есть мнение что со стороны фронта не коректно, возможны правки
    public ResponseEntity saveSettings(GeneralSettingsResponse generalSettingsResponse) {
        final String MULTIUSER_MODE = "MULTIUSER_MODE";
        final String POST_PREMODERATION = "POST_PREMODERATION";
        final String STATISTICS_IS_PUBLIC = "STATISTICS_IS_PUBLIC";

        String multiuserModeValue = generalSettingsResponse.isMultiuser_MODE() ? "YES" : "NO";
        String postPremoderationValue = generalSettingsResponse.isPost_PREMODERATION() ? "YES" : "NO";
        String statisticsIsPublicValue = generalSettingsResponse.isStatistics_IS_PUBLIC() ? "YES" : "NO";

        List<GlobalSettings> settingsList = null;

        if (userRepository.getOne(Objects.requireNonNull(idUserSession())).isModerator()) {
            settingsList = settingsRepository.findAll();

            for (GlobalSettings settings : settingsList) {
                if (settings.getCode().equals(MULTIUSER_MODE)) {
                    settings.setValue(multiuserModeValue);
                    settingsRepository.save(settings);
                } else if (settings.getCode().equals(POST_PREMODERATION)) {
                    settings.setValue(postPremoderationValue);
                    settingsRepository.save(settings);
                } else if (settings.getCode().equals(STATISTICS_IS_PUBLIC)) {
                    settings.setValue(statisticsIsPublicValue);
                    settingsRepository.save(settings);
                }
            }
        }

        return new ResponseEntity(OK);
    }

    // finished | Проверенно постманом
    public GeneralTagResponse getTag(String query) {
        int countAllPosts = 0; // Общее кол. постов на сайте.
        int countTags = 0;      // Кол. постов с данным тегом

        double weight = 0;      // вес поста (Колю постов с тегом ДЕЛЕННОЕ на общее кол. постов)
        double maxWeight = 0;  // МаксВес это максимальное количество использования самого популярного тэга
        double ratedWeight = 0; // Нормированный вес вычесляется по формуле | ВЕС * (1  / макс вес)

        List<Tags> tagsList = query.length() == 0 ? tagsRepository.findAll() : tagsRepository.searchTag(query);
        List<GeneralTag> generalTagList = new ArrayList<>();

        for (Tags tag : tagsRepository.findAll()) {
            if ((double) tag2PostRepository.searchIdTag(tag.getId()).size() > maxWeight) {
                maxWeight = tag2PostRepository.searchIdTag(tag.getId()).size();
            }
        }

        for (Tags tags : tagsList) {

            countAllPosts = postRepository.findAll().size();
            countTags = tag2PostRepository.searchIdTag(tags.getId()).size();
            weight = (double) countTags / countAllPosts;
            ratedWeight = weight * (1 / maxWeight);

            generalTagList.add(new GeneralTag(tags.getName(), ratedWeight));
        }

        return new GeneralTagResponse(generalTagList);
    }

    // finished | Проверенно постманом
    public Object addCommentToPost(int parentId, int postId, String text) {
        final String TEXT_ERROR = "Текст комментария не задан или слишком короткий";

        Post post = postRepository.findById(postId).orElse(null);
        PostComments postComments = postCommentsRepository.searchParentId(parentId).stream().findFirst().orElse(null);

        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        if (postComments == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        if (text.length() == 0) {
            new ResponseTrueFalseAndObject(false, new ErrorText(TEXT_ERROR));
        }
        PostComments ps = new PostComments(parentId, post, post.getUserId(), LocalDateTime.now(), text);
        postCommentsRepository.save(ps);

        return new GeneralIdResponse(ps.getId());
    }

    // finished | Проверенно постманом
    public ResponseEntity moderationPost(int postId, String decision) {

        Post post = postRepository.getOne(postId);
        post.setModerationStatus(ModerationStatus.valueOf(decision));
        post.setModeratorId(idUserSession());

        return new ResponseEntity(OK);
    }

    // not ready | work
    public CommonResponse getCalendar(String year) {
        final String YEAR_REGEX = "\\d{4}";
        LocalDateTime timeYears = null;
        Integer getYear = null;
        List<String> yearList = new ArrayList<>();
        HashMap<LocalDate, Integer> postsListHM = new HashMap<>();

        if (year.matches(YEAR_REGEX)) {
            timeYears = LocalDateTime.of(Integer.parseInt(year), Month.JANUARY, 1, 0, 0);
        } else {
            timeYears = LocalDateTime.of(LocalDateTime.now().getYear(), Month.JANUARY, 1, 0, 0);
        }

        List<Post> dataList = postRepository.searchToDate(timeYears, LocalDateTime.now());
        for (Post post : dataList) {
            getYear = post.getTime().getYear();
            yearList.add(getYear.toString());

            postsListHM.put(post.getTime().toLocalDate(),
                    postRepository.searchToDate(post.getTime(), post.getTime().plusDays(1)).size());
        }

        return new GeneralCalendarResponse(yearList, postsListHM);
    }


    final String generateString(int numMs) {

        /*
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'
         */
        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z'};
        char[] index = new char[numMs];

        Random r = new Random();
        int i = 0;

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }

    final Integer idUserSession() {
        final String sessionStr = RequestContextHolder.currentRequestAttributes().getSessionId();
        if (authService.getAuthorizedUsersList().get(sessionStr) == null) {
            return null;
        }
        return authService.getAuthorizedUsersList().get(sessionStr);
    }

}
