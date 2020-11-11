package com.example.timesheet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimesheetApplicationTests {

    @Test
    void contextLoads() {
    }

}


/*The following test proves that our application is correctly set up.
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AggregationApplication.class)
@WebAppConfiguration
public class AggregationApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void findById() {
        Product product = new Product("LN1", "London", 5.0f);
        productRepository.save(product);

        Product foundProduct = productRepository.findOne("LN1");

        assertNotNull(foundProduct);
    }
}*/
