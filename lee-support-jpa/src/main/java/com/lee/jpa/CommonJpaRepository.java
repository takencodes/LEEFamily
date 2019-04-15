package com.lee.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 17:14 2018/8/21
 */
public interface CommonJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void persist(T entity);
}
