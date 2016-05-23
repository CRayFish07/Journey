import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by nibnait on 5/21/16.
 */
public class TestSeesion {

    static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.mapper");



    public static void testSession(){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        SqlSession sqlSession = sqlSessionFactory.openSession();
    }


    public static void main(String[] args){
        testSession();

    }

}
