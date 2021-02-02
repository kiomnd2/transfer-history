package com.kakaopay.history.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Querydsl을 bean으로 등록
 */
@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager manager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(manager);
    }

}
