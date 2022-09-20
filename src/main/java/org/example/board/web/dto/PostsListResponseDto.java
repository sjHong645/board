package org.example.board.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import org.example.board.domain.posts.Posts;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDate modifiedDate;
    private String content;

    public PostsListResponseDto(Posts entity){

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = (entity.getModifiedDate()).toLocalDate();

        // 내용이 10자를 넘어가면 일부만 출력
        if(entity.getContent().length() >= 10) {
            this.content = entity.getContent().substring(0, 9) + "...";
        }

        // 그렇지 않다면 전부 출력
        else this.content = entity.getContent();



    }

}
