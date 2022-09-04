package org.example.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


// Dto = Data Transfer Object = 데이터 전달 객체
// api 요청을 받는 controller를 보면 request 데이터를 받아야 할 부분이 있다.
// 그 부분을 담당하는 게 dto다.

// 이는 Entity가 해주면 안된다. Entity는 모든 비즈니스 영역을 처리해야 하는 중요한 부분이기 때문이다.
// 그래서 save, update와 같이 각각의 작업에 대한
// 별도의 requestDto를 만든 거다.
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto (String title, String content) {
        this.title = title;
        this.content = content;
    }

}
