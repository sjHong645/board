package org.example.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.posts.Posts;


// Entity 클래스와 거의 유사하지만 별도로 Dto 클래스를 생성함
// Request, Response로 사용하기 위해서 자주 변경될 클래스

// Entity 클래스는 수많은 클래스와 비즈니스 로직들이 연결되어 있기 때문에
// Controller에서 사용할 Dto를 꼭 Entity와 분리해서 사용해야 함

// Dto(Data Transfer Object) = 계층 간에 데이터 교환을 위한 객체
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
