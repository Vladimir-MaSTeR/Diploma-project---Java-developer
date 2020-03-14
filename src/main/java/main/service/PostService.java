package main.service;


import main.api.response.postRespons.*;
import main.model.*;
import main.model.enums.ModePostResponse;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.swing.text.html.HTML;
import java.util.*;

@Service
public class PostService  {

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
    public PostApiResponse listPostAllInfo(int offset, int limit, String mode)  {

        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> listAllPost = postRepository.AllPostList(offset, limit);
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
    public PostApiResponse searchPosts(int offset,int limit, String query) {
        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> listAllPost = postRepository.AllPostList(offset, limit);
        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<PostApi> listPostApi = new ArrayList<>();
        List<PostApi> listPostApiMatches = new ArrayList<>();
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

            if (post.getTitle().contains(query) & query.length() != 0 || post.getText().contains(query) & query.length() != 0) {
                listPostApiMatches.add(new PostApi(post.getId(),
                        post.getTime(),
                        new PostApiUser(post.getUserId().getId(), post.getUserId().getName()),
                        post.getTitle(),
                        post.getText().length() >= 100 ? post.getText().substring(0, 100) : post.getText(),
                        likeCount,
                        dislikeCount,
                        post.getCommentsList().size(),
                        post.getViewCount()));
            } else {
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


        return listPostApiMatches.size() != 0 ? new PostApiResponse(count, listPostApiMatches) : new PostApiResponse(count, listPostApi);
    }

    // ready | work
    public PostApiIdResponse getPostId(int id) {
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
                                post.getText(),
                                likeCount,
                                dislikeCount,
                                post.getViewCount(),
                                new PostApiComments(postComments.getId(),
                                        postComments.getTime(),
                                        new PostApiUserComments(user.getId(), user.getName(), user.getPhoto()),
                                        "huhuhuhuh"),
                                new PostApiTagName(tags.getName())
                                );
            }
        }


        return postApiIdResponse;
    }

}
