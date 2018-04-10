package vballada.photosapp.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataMongoTest
@ActiveProfiles("embedded")
public class PhotosappWebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
