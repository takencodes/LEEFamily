package com.lee.jpa;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

public class CommonRepositoryFactory extends JpaRepositoryFactory {

    public CommonRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param metadata
     * @return
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return CommonJpaRepositoryBean.class;
    }
}
