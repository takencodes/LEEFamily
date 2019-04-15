package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 9:33 2019/2/14
 */
public class ApplicationBeanTest {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
//       UserPoRepositoryImpl userPoRepository =(UserPoRepositoryImpl) ac.getBean("userPoRepository");

    }
}
