package org.example.board.web;

import lombok.RequiredArgsConstructor;
import org.example.board.service.posts.PostsService;
import org.example.board.web.dto.PostsListResponseDto;
import org.example.board.web.dto.PostsResponseDto;
import org.example.board.web.dto.PostsSaveRequestDto;
import org.example.board.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



// controller에서는
// postmapping, putmapping, getmapping에서와 같이
// url에 따라 api 요청을 받는다.
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // /api/v1/posts/{id}에 나오는 id값을
    // 매개변수로 전달하겠다는 뜻이다.
    // ex) /api/v1/posts/23 => id값 = 23
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {

        return postsService.update(id, requestDto);

    }

    // /api/v1/posts/{id}에 나오는 id값을
    // 매개변수로 전달하겠다는 뜻이다.
    // ex) /api/v1/posts/23 => id값 = 23
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){

        postsService.delete(id);
        return id;

    }


}
