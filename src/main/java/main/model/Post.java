package main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import main.model.enums.ModerationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isActive;       // скрыта или активна публикация: 0 или 1

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('NEW', 'ACCEPTED', 'DECLINED')")
    private ModerationStatus moderationStatus;  // статус модерации, по умолчанию значение NEW

    @Column(nullable = false)
    private int moderatorId;        // ID пользователя-модератора, принявшего решение, или NULL

    //@Column(nullable = false)
    @ManyToOne()
    @JoinColumn(nullable = false)
    private User userId;             // автор поста

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime time;              // дата и время публикации поста

    @Column(nullable = false)
    private String title;           // заголовок поста

    @Column(nullable = false)
    private String text;            // текст поста

    @Column(nullable = false)
    private int viewCount;          // количество просмотров поста

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<PostComments> commentsList;


    public Post() {
    }

    public Post(boolean isActive, ModerationStatus moderationStatus, int moderatorId, User userId, LocalDateTime time, String title, String text, int viewCount) {
        this.isActive = isActive;
        this.moderationStatus = moderationStatus;
        this.moderatorId = moderatorId;
        this.userId = userId;
        this.time = time;
        this.title = title;
        this.text = text;
        this.viewCount = viewCount;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public Enum getModerationStatus() {
        return moderationStatus;
    }
    public void setModerationStatus(ModerationStatus moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public int getModeratorId() {
        return moderatorId;
    }
    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<PostComments> getCommentsList() {
        return commentsList;
    }
    public void setCommentsList(List<PostComments> commentsList) {
        this.commentsList = commentsList;
    }
}
