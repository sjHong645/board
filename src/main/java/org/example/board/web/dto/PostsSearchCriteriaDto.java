package org.example.board.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsSearchCriteriaDto {

    private String key;
    private String value;

    public PostsSearchCriteriaDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
