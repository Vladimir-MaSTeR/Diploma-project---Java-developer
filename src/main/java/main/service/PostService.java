package main.service;


import main.api.response.postRespons.*;
import main.model.*;
import main.model.enums.ModePostResponse;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostVotesRepository postVotesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostCommentsRepository commentsRepository;
    @Autowired
    Tag2PostRepository tag2PostRepository;
    @Autowired
    TagsRepository tagsRepository;

    // finished
    public PostApiResponse listPostAllInfo(int offset, int limit, String mode) {

        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> listAllPost = postRepository.allPostList(offset, limit);
        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<PostApi> listPostApi = new ArrayList<>();
        count = postRepository.findAll().size();

        for (Post post : listAllPost) {
            for (PostVotes votes : listPostVotes) {
                if (votes.getPostId().getId() == post.getId()) {
                    if (votes.getValue() == 1) {
                        likeCount++;
                    }
                    if (votes.getValue() == -1) {
                        dislikeCount++;
                    }
                }
            }

            listPostApi.add(new PostApi(post.getId(),
                    post.getTime(),
                    new PostApiUser(post.getUserId().getId(), post.getUserId().getName()),
                    post.getTitle(),
                    post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                    likeCount,
                    dislikeCount,
                    post.getCommentsList().size(),
                    post.getViewCount()));

        }

        if (mode.equalsIgnoreCase(ModePostResponse.RECENT.toString())) {
            listPostApi.sort(Comparator.comparing(PostApi::getTime).reversed());

//            Collections.sort(listAllPost, new Comparator<Post>() {
//                @Override
//                public int compare(Post o1, Post o2) {
//                    if (o1.getTime() == null || o2.getTime() == null) {
//                        return 0;
//                    }
//
//                    return o1.getTime().compareTo(o2.getTime());
//                }
//            });
        }
        if (mode.equalsIgnoreCase(ModePostResponse.EARLY.toString())) {
            listPostApi.sort(Comparator.comparing(PostApi::getTime));
            //Collections.reverse(listPostApi);
        }
        if (mode.equalsIgnoreCase(ModePostResponse.POPULAR.toString())) {
            listPostApi.sort(Comparator.comparingInt(PostApi::getCommentCount).reversed());
        }
        if (mode.equalsIgnoreCase(ModePostResponse.BEST.toString())) {
            listPostApi.sort(Comparator.comparingInt(PostApi::getLikeCount).reversed());
        }


        return new PostApiResponse(count, listPostApi);
    }

    // finished
    public PostApiResponse searchPosts(int offset, int limit, String query) {
        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> postList = query.length() != 0 ?
                postRepository.searchPost(offset, limit, query) :
                postRepository.allPostList(offset, limit);

        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<PostApi> listPostApiMatches = new ArrayList<>();
        count = postRepository.findAll().size();

        for (Post post : postList) {
            for (PostVotes votes : listPostVotes) {
                if (votes.getPostId().getId() == post.getId()) {
                    if (votes.getValue() == 1) {
                        likeCount++;
                    }
                    if (votes.getValue() == -1) {
                        dislikeCount++;
                    }
                }
            }

            listPostApiMatches.add(new PostApi(post.getId(),
                    post.getTime(),
                    new PostApiUser(post.getUserId().getId(), post.getUserId().getName()),
                    post.getTitle(),
                    post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                    likeCount,
                    dislikeCount,
                    post.getCommentsList().size(),
                    post.getViewCount()));
        }


        return new PostApiResponse(count, listPostApiMatches);
    }

    // ready | on checking
    public PostApiIdResponse getPostId(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with this ID not found."));
        User user = userRepository.findById(post.getUserId().getId()).orElseThrow(() -> new EntityNotFoundException("User with this ID not found."));

        List<PostVotes> postVotesList = postVotesRepository.findAll();
        List<Tag2Post> tag2PostsList = tag2PostRepository.findAll();

        List<PostComments> commentsList = post.getCommentsList();
        PostApiIdResponse postApiIdResponse = null;
        Tags tags = null;
        int likeCount = 0;
        int dislikeCount = 0;

        for (PostVotes votes : postVotesList) {
            if (votes.getPostId().getId() == post.getId()) {
                if (votes.getValue() == 1) {
                    likeCount++;
                }
                if (votes.getValue() == -1) {
                    dislikeCount++;
                }
            }
        }

        for (Tag2Post tag2Post : tag2PostsList) {
            if (tag2Post.getPostId().getId() == post.getId()) {
                tags = tagsRepository.findById(tag2Post.getTagId().getId()).orElseThrow(() -> new EntityNotFoundException("Tag with this ID not found."));
            }
        }

        for (PostComments postComments : commentsList) {
            if (postComments.getPostId().getId() == post.getId()) {

                assert tags != null;
                postApiIdResponse = new PostApiIdResponse(post.getId(),
                        post.getTime(),
                        new PostApiUser(user.getId(), user.getName()),
                        post.getTitle(),
                        post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                        likeCount,
                        dislikeCount,
                        post.getViewCount(),
                        new PostApiComments(postComments.getId(),
                                postComments.getTime(),
                                postComments.getText(),
                                new PostApiUserComments(user.getId(), user.getName(), user.getPhoto())),
                        new PostApiTagName(tags.getName())
                );
            }
        }


        return postApiIdResponse;
    }

    // finished
    public PostApiResponse getPostsData(int offset, int limit, String date) {

        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ [HH][:mm][:ss][.SSS]]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        LocalDateTime dateTime = LocalDate.parse(date, formatter).atStartOfDay();
        LocalDateTime dateTime2 = dateTime.plusDays(1);


        List<Post> listAllPost = postRepository.postsData(offset, limit, dateTime, dateTime2);
        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<PostApi> listPostApi = new ArrayList<>();
        count = postRepository.findAll().size();

        for (Post post : listAllPost) {
            for (PostVotes votes : listPostVotes) {
                if (votes.getPostId().getId() == post.getId()) {
                    if (votes.getValue() == 1) {
                        likeCount++;
                    }
                    if (votes.getValue() == -1) {
                        dislikeCount++;
                    }
                }
            }

            listPostApi.add(new PostApi(post.getId(),
                    post.getTime(),
                    new PostApiUser(post.getUserId().getId(), post.getUserId().getName()),
                    post.getTitle(),
                    post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                    likeCount,
                    dislikeCount,
                    post.getCommentsList().size(),
                    post.getViewCount()));
        }


        return new PostApiResponse(count, listPostApi);
    }

    // ready | on checking
    public PostApiResponse getPostsTag(int offset, int limit, String tag) {

        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> listAllPost = postRepository.allPostList(offset, limit);
        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<Tags> listTags = tagsRepository.findAll();
        List<Tag2Post> listTag2Post = tag2PostRepository.findAll();
        List<PostApi> listPostApi = new ArrayList<>();
        count = postRepository.findAll().size();

        for (Post post : listAllPost) {
            for (PostVotes votes : listPostVotes) {
                if (votes.getPostId().getId() == post.getId()) {
                    if (votes.getValue() == 1) {
                        likeCount++;
                    }
                    if (votes.getValue() == -1) {
                        dislikeCount++;
                    }
                }
            }

            for (Tags tags : listTags) {
                if (tags.getName().equals(tag)) {
                    for (Tag2Post tag2Post : listTag2Post) {
                        if (tag2Post.getTagId().getId() == tags.getId() & tag2Post.getPostId().getId() == post.getId()) {
                            listPostApi.add(new PostApi(post.getId(),
                                    post.getTime(),
                                    new PostApiUser(post.getUserId().getId(), post.getUserId().getName()),
                                    post.getTitle(),
                                    post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                                    likeCount,
                                    dislikeCount,
                                    post.getCommentsList().size(),
                                    post.getViewCount()));
                        }
                    }
                }
            }
        }

        return new PostApiResponse(count, listPostApi);
    }

    // not ready | work
}
