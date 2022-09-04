package org.example.board.web.dto;

import lombok.Getter;
import org.example.board.domain.posts.Posts;

// Posts 엔터티 중에서 필요한 부분인
// id, title, content, author 만 저장하도록 했다.

// 그 외에 메소드도 있지만 그 부분은 필요하지 않으니 생성자에 넣지 않았다.

// 이렇게 본다면 결국 dto는
// entity를 보호하기 위해 존재하는 것 같기도 하다.

// 응답할 때는 entity의 내용 중 필요한 부분만을 차용해서 응답할 때 responseDto를 사용하고
// 요청할 때는 entity를 직접 수정해서 바꾸면 너무나도 큰 변화가 일어나니까
// 저장(save), 수정(update). 각각의 작업에 대한 별도의 dto를 만들어 쓰기 때문이다.
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

    }


}
