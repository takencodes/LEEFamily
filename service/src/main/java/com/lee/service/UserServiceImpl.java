package com.lee.service;
import com.alibaba.druid.util.Base64;
import com.lee.dao.mysql.UserPO;
import com.lee.dao.repository.UserRepository;
import com.lee.service.interfaces.UserService;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 18:15 2018/8/21
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IdService idService;

    @Autowired
    private UserRepository userRepository;

    public void login(String username, String password) {
        if (logger.isDebugEnabled()){
            logger.debug("Received request params {},{}", username, password);
        }
        String pw = Base64.byteArrayToAltBase64(password.getBytes());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new RuntimeException("账号或密码错误！");
        }
        UserPO userPO = userRepository.findByUsername(username);
        if (userPO == null){
            throw new RuntimeException("该用户不存在！");
        }
        if(!pw.equals(userPO.getPassword())){
            throw new RuntimeException("账号或密码错误！");
        }

    }

    public void init(String pw) {
        userRepository.insert(idService.nextId(), Base64.byteArrayToAltBase64(pw.getBytes()));
    }
}
