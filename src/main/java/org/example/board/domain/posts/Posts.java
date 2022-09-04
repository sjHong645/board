package org.example.board.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.BaseTimeEntity;

// Entity에서는 setter를 만들면 안됨
// 왜냐면, 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 구분할 수 없기 때문
@Getter // lombok 어노테이션 - 빌더 패턴 클래스 생성
@NoArgsConstructor // lombok 어노테이션 - 기본 생성자 자동 추가
@Entity // JPA 어노테이션 - Posts 클래스는 실제 DB 클래스와 매칭할 클래스 = Entity
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 Primary Key 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙 - IDENTITY를 통해 auto_increment 하도록 했음
    private Long id;

    // Column 어노테이션이 없어도 컬럼이 된다.
    // 아래와 같이 자료형이나 길이를 설정하고 싶을 때 사용하면 된다.
    @Column(length = 500, nullable = false)
    private String title; // 글 제목

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 글 내용

    private String author; // 글 저자

    // Builder 클래스를 통해 다음과 같은 작업 가능
    // Posts.builder()
    //      .title(title)
    //      .content(content)
    //      .author(author)
    //      .build();
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
