package com.lee.dao.repository;

import com.lee.dao.mysql.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 20:20 2019/1/21
 */
@Repository
public interface UserRepository extends JpaRepository<UserPO, Long>, UserDao, JpaSpecificationExecutor<UserPO> {

    /**
     * 根据username查找用户
     * @param username
     * @return
     */
    UserPO findByUsername(String username);

    /**
     * 插入用户
     */
    @Transactional
    @Modifying
    @Query(value = "insert into user  values(?1,?2,'lizhizhong')", nativeQuery = true)
    void insert(Long id, String password);

}
