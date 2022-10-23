package org.example.board.web;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.board.domain.posts.Posts;
import org.example.board.web.dto.PostsSearchCriteriaDto;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification implements Specification<Posts> {

    private PostsSearchCriteriaDto criteria;

    public SearchSpecification(PostsSearchCriteriaDto criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        String[] keys = criteria.getKey();

        List<Predicate> predicateList = new LinkedList<>();

        for(String key : keys) {
            if(root.get(key).getJavaType() == String.class) {
                predicateList.add(builder.like(root.get(key), "%" + criteria.getValue() + "%"));
            }

            else predicateList.add(builder.equal(root.get(key), criteria.getValue()));
        }

        return builder.or(predicateList.toArray(new Predicate[]{}));

    }

}
