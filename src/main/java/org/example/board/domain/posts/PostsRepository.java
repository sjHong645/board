package org.example.board.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 DB를 접근하게 해줄 인터페이스 = DB Layer 접근자 -> JPA에서는 Repository라고 함
// 아래와 같이 인터페이스 생성 후
// JpaRepository<Entity 클래스, PK 자료형>을 상속하면 됨 => 자동적으로 CRUD 메소드가 생성됨

// 해당 인터페이스는 Entity와 같은 위치에 있어야 함
public interface PostsRepository extends JpaRepository<Posts, Long> {}
