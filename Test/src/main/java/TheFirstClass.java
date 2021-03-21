import com.bzk.pojo.User;

import java.io.InputStream;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/8 17:22
 * @description：测试git好用不
 * @modified By：
 */
public class TheFirstClass  {
    public static void main(String[] args) {
        User user = new User();
        ClassLoader classLoader = user.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("userMapper.xml");
        System.out.println(resourceAsStream);
        System.out.println("Hello world!");
    }
}
