import com.auction.AuctionApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuctionApp.class)
public class SpringBootTomcatConnectionPoolIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
        Assert.assertEquals("org.apache.tomcat.jdbc.pool.DataSource",dataSource.getClass().getName());
    }
}
