package org.example.board.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.posts.Posts;
import org.example.board.domain.posts.PostsRepository;
import org.example.board.web.dto.PostsListResponseDto;
import org.example.board.web.dto.PostsResponseDto;
import org.example.board.web.dto.PostsSaveRequestDto;
import org.example.board.web.dto.PostsUpdateRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// transaction = 더 이상 쪼갤 수 없는 작업 단위
// Service는
// 말 그대로 하나의 트랜잭션, 도메인 기능 (통칭 작업) 들의 순서를 보장하는 곳
// 순서라는 말은 잘 모르겠지만
// 하나의 기능이 정확히 어떤 기능을 할 지 명확히 해주는 곳이라고 이해했다.
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 트랜잭션의 범위를 설정함
    // 여기서는 save 메소드를 실행했을 때 postsRepository.save를 실행하도록 함
    @Transactional
    public Long save (PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllAsc(String column) {

        // column 기준으로 오름차순
        return postsRepository.findAll(Sort.by(column).ascending()).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(String column) {

        // column 기준으로 내림차순
        return postsRepository.findAll(Sort.by(column).descending()).stream()
                              .map(PostsListResponseDto::new)
                              .collect(Collectors.toList());

    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){

        // id에 해당하는 엔터티를 조회한 후
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 해당 엔터티 삭제
        postsRepository.delete(posts);

    }
}
