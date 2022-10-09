package org.example.board.web;



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

        if(root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(
                    root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");

        }

        else return builder.equal(root.get(criteria.getKey()), criteria.getValue());

    }

}
