package org.example.board.web;

import java.util.List;

import org.example.board.domain.posts.Posts;
import org.example.board.domain.posts.PostsRepository;
import org.example.board.web.dto.PostsSaveRequestDto;
import org.example.board.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// springBootTest는 WebMvcTest와 달리 JPA 기능이 작동함
// 그래서 jpa를 테스트할 때 springBootTest와 TestRestTemplate을 이용하면 됨
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_saved() throws Exception {

        // given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when

        // 요청한 url에 requestDto에 저장한 내용을 Long으로 반환해서 responseEntity에 저장
        // save 형식은 "post" 방식으로 api를 요청받아서 postFor~~ 메서드를 사용하나보다.
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then

        // 제대로 반환 되었는지 확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Posts> all = postsRepository.findAll();

        // 제대로 내용이 저장되었는지 확인
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_updated() throws Exception {

        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updatedId = savedPosts.getId();

        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        // 자료형은 PostsUpdateRequestDto로 설정
        // 매개변수가 1개니까 body만 채웠다.
        // 즉, HttpEntity 자료형인 requestEntity의 인스턴스 변수 body는 requestDto이다.

        // body 부분을 requestDto의 값들로 채움
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when

        // exchage() 메소드를 통해 api 호출
        // 응답받은 responseEntity
        // 요청한 url에 PUT 방식으로 requestEntity를 요청해서 Long 자료형으로 반환받음 - 내 추축임. 틀릴 수 있음.

        // 원래 updateId는 기존에 저장된 savedPosts 내용을 저장했다.
        // 그런데 새로운 내용을 저장한 requestDto의 내용을 받은 requestEntity을 호출하도록 했다.

        // 아래의 결과를 보면 분명 기존에 있던 savedPosts 내용이 아닌
        // 새롭게 수정한 requestDto의 내용인 걸 확인할 수 있다.
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);


    }
}
