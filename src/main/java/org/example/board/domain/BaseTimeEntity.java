package org.example.board.domain;



import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


// 생성시간과 수정 시간을 자동화하기 위한 클래스
// 모든 Entity의 상위 클래스가 되어 Entity 들의 createdDate, modifiedDate를 자동으로 관리한다
// 때문에 모든 Entity 들은 해당 클래스를 상속해야 함 => ~~ extends BaseTimeEntity
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
