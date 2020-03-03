package main.service;


import main.api.response.postRespons.PostApi;
import main.api.response.postRespons.PostApiResponse;
import main.api.response.postRespons.PostApiUser;
import main.model.Post;
import main.model.PostVotes;
import main.repository.PostRepository;
import main.repository.PostVotesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;
    PostVotesRepository postVotesRepository;

    // mode - режим вывода (сортировка)
    final String MODE_RECENT = "recent";    // сортировать по дате публикации, выводить сначала новые
    final String MODE_POPULAR = "popular";  // сортировать по убыванию количества комментариев
    final String MODE_BEST = "best";        // сортировать по убыванию количества лайков
    final String MODE_EARLY = "early";      // сортировать по дате публикации, выводить сначала старые

    public PostService(PostRepository postRepository, PostVotesRepository postVotesRepository) {
        this.postRepository = postRepository;
        this.postVotesRepository = postVotesRepository;
    }


    public PostApiResponse listPostAllInfo(int offset, int limit, String mode) {

        int count = 0;                          // содержит общее количество постов, которое есть на сайте
        int likeCount = 0;
        int dislikeCount = 0;

        List<Post> listAllPost = postRepository.findAll();
        List<PostVotes> listPostVotes = postVotesRepository.findAll();
        List<PostApi> listPostApi = new ArrayList<>(limit);
        count = listAllPost.size();

        for (int i = offset; i <= limit + offset; i++) {
            if (i < count && listAllPost.get(i).isActive() && listAllPost.get(i).getModerationStatus().equals("ACCEPTED")) {
                for (PostVotes votes : listPostVotes) {
                    if (votes.getPostId().getId() == listAllPost.get(i).getId()) {
                        if (votes.getValue() == 1) {
                            likeCount++;
                        }
                        if (votes.getValue() == -1) {
                            dislikeCount++;
                        }
                    }
                }

               listPostApi.add(new PostApi(listAllPost.get(i).getId(),
                       listAllPost.get(i).getTime(),
                       new PostApiUser(listAllPost.get(i).getUserId().getId(), listAllPost.get(i).getUserId().getName()),
                       listAllPost.get(i).getTitle(),
                       listAllPost.get(i).getText().substring(0, 100),
                       likeCount,
                       dislikeCount,
                       listAllPost.get(i).getCommentsList().size(),
                       listAllPost.get(i).getViewCount()
                       ));
            }
        }

        if (mode.equals(MODE_RECENT)) {
            listPostApi.sort((p1, p2) -> p1.getTime().compareTo(p2.getTime()));

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
        if (mode.equals(MODE_EARLY)) {
            listPostApi.sort((p1, p2) -> p1.getTime().compareTo(p2.getTime()));
            Collections.reverse(listAllPost);
        }
        if (mode.equals(MODE_POPULAR)) {
            listPostApi.sort((p1, p2) -> Integer.compare(p1.getCommentCount(), p2.getCommentCount()));
        }
        if (mode.equals(MODE_BEST)) {
            listPostApi.sort((p1, p2) -> Integer.compare(p1.getLikeCount(), p2.getLikeCount()));
        }


        return new PostApiResponse(count, listPostApi);
    }

}
