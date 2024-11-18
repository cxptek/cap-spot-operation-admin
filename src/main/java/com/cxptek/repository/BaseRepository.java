package com.cxptek.repository;

import com.cxptek.config.EntityManagerConfig;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@NoRepositoryBean
public interface BaseRepository<E, I> extends
        CrudRepository<E, I>,
        JpaRepository<E, I>,
        JpaSpecificationExecutor<E>,
        QuerydslPredicateExecutor<E> {

    default List<E> fetch(Pageable pageable) {
        return stream(findAll(pageable).spliterator(), true).collect(toList());
    }

    default List<E> fetch(Predicate condition, Sort sort) {
        return stream(findAll(condition, sort).spliterator(), true).collect(toList());
    }

    default List<E> fetch(Predicate condition) {
        return stream(findAll(condition).spliterator(), true).collect(toList());
    }

    default List<E> fetch(Predicate condition, Pageable pageable) {
        return stream(findAll(condition, pageable).spliterator(), true).collect(toList());
    }

    static JPAQueryFactory getQuery() {
        return new JPAQueryFactory(EntityManagerConfig.getEntityManager());
    }
}
