package com.sparta.week05miniprojectbackend.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commentId", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    }
}
