package com.sparta.week05miniprojectbackend.service;

import com.sparta.week05miniprojectbackend.dto.requestDto.CommentRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.entity.Comment;
import com.sparta.week05miniprojectbackend.entity.Post;
import com.sparta.week05miniprojectbackend.entity.User;
import com.sparta.week05miniprojectbackend.exception.CommentNotFoundException;
import com.sparta.week05miniprojectbackend.exception.UserNotFoundException;
import com.sparta.week05miniprojectbackend.repository.CommentRepository;
import com.sparta.week05miniprojectbackend.repository.PostRepository;
import com.sparta.week05miniprojectbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // userId를 이용해서 User 있는지 확인 후 User 객체 만들기
    private User getUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);
        return user;
    }

    // commentId를 이용해서 commentid 있는지 확인 후  commentid 객체 만들기
    private Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        return comment;
    }



    @Transactional
    public ResponseDto<String> createComment(Long postId, CommentRequestDto commentRequestDto, String userId) {
        // 받아온 userId로 user 객체 생성
        User user = getUser(userId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다."));

        Comment comment = new Comment(user,post,commentRequestDto.getContent());

        // DB에 저장
        commentRepository.save(comment);


        return ResponseDto.success(
                "댓글 작성 완료"
        );
    }

    //댓글 삭제
    @Transactional
    public ResponseDto<String> deleteComment(Long commentId, String userId) {
        // 받아온 userId로 user 객체 생성
        User user = getUser(userId);
        // 받아온 commentId로 comment 객체 생성
        Comment comment = getComment(commentId);
        //로그인한 아이디와 comment 작성한아이디가 동일한지 확인
        if (!user.getId().equals(comment.getUser().getId())) {
            return ResponseDto.fail("404", "작성자만 게시글 삭제가 가능 합니다");
        }
        //DB에서 삭제
        commentRepository.deleteById(commentId);

        return ResponseDto.success(
                "댓글 삭제 완료"
        );
    }
}
