package com.lee.dao.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "logPo")
public class LogPo implements Serializable {
    private static final long serialVersionUID = -1744970025814656530L;

    @Id
    private Long id;
    /**
     * 访问路径
     */
    @Column(length = 255, nullable = false)
    private String uri;


    /**
     * 请求方式
     */
    @Column(length = 10, nullable = false)
    private String http_method;

    /**
     * 请求参数
     */
    @JsonIgnore
    @Column(columnDefinition = "MEDIUMTEXT")
    private String requestParams;

    /**
     * 操作人员
     */
    @Column(length = 50, nullable = false)
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttp_method() {
        return http_method;
    }

    public void setHttp_method(String http_method) {
        this.http_method = http_method;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
