package testFirst;

import com.chen.test.dao.TestDao;
import com.chen.test.entity.TestPhoto;
import com.chen.utils.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml"})
@Transactional()
public class TestFunction {

    @Autowired
    private TestDao td;
    @Autowired
    private JedisUtil ju;
    @Test
    public void test22(){
        TestPhoto tp = td.testQuery("fb167c2d1aaf00330867");
        System.out.println(tp);
    }

    @Test
    public void testRedis(){
        String s=ju.get("username");
        System.out.println(s);
    }
}
