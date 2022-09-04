package org.example.board.domain.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정이 없다면 h2 데이터베이스를 자동으로 실행함
// 여기서 save와 findAll 기능을 테스트하겠다.
public class PostsRepositoryTest {

    // 스프링이 관리하는 빈(Bean)을 주입 받음
    @Autowired
    PostsRepository postsRepository;

    @After // 테스트 이후에 실행될 메소드
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_load() {

        // given
        String title = "테스트 게시글";
        String content = "게시글 본문";
        String author = "김가루";

        // 주어진 글 저장
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터 조회

        // then
        Posts posts = postsList.get(0); // 0번째에 있는 값
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);

    }

    @Test
    public void BaseTimeEntity_saved() {

        // given
        LocalDateTime now = LocalDateTime.of(2022, 8, 29, 10, 10, 0);

        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createdDate = " + posts.getCreatedDate()
        + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }


}
