package com.lee.dao.repository.Log;

import com.lee.dao.mysql.LogPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogPo, Long>, JpaSpecificationExecutor<LogPo>, LogDao {

}
