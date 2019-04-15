package com.lee.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lee.dao.mysql.LogPo;
import com.lee.service.interfaces.LogService;
import com.util.AuthorityUtil;
import org.codelogger.utils.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private LogConfig logConfig;

    @Autowired
    private LogService logService;

    public LoginInterceptor() {
    }

    /**
     * 使用URI 匹配需要不记录log的正则表达式
     */
    public boolean withinLog(String uri) {
        String excludedUri = logConfig.getExcludedUri();
        return !excludedUri.contains(uri);
    }

    /**
     * 日志记录实现
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Boolean enabled = logConfig.isManualEnable();
        if (enabled && withinLog(request.getRequestURI())) {
            String uri = request.getRequestURI();
            String user = AuthorityUtil.getLoginUsername();
            String http_method = request.getMethod();
            JSONObject params = new JSONObject();
            Enumeration<String> parameterNames = request.getParameterNames();
            for (Enumeration e = parameterNames; e.hasMoreElements(); ) {
                String thisName = e.nextElement().toString();
                String thisValue = ArrayUtils.join(request.getParameterValues(thisName), ",");
                params.put(thisName, thisValue);
            }
            String requestParams = params.toJSONString();
            LogPo po = new LogPo();
            po.setUri(uri);
            po.setUser(user);
            po.setHttp_method(http_method);
            po.setRequestParams(requestParams);
            if (!Objects.equals(po.getUri(), "/heartbeat.do")) {
                logger.debug("uri:{}\nmethod:{}\nuser:{}\ntimestamp:{}\nparams:{}", po.getUri(), po.getHttp_method(), po.getUser(), po.getRequestParams());
                logger.debug("preHadle{} loginfo", uri);
            }
            logService.saveLog(po);
        }
        return true;
    }
}
