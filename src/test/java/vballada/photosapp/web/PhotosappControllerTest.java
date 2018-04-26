package vballada.photosapp.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import vballada.photosapp.web.domain.Criteria;
import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

@RunWith(SpringRunner.class)
@Import(TestSecurityConfig.class)
@WebMvcTest(controllers = PhotosappController.class, secure = false)
public class PhotosappControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhotoRepository service;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void testPhotos() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		Criteria criteria = new Criteria();
		criteria.setDir("asc");
		criteria.setSort("id");
		criteria.setSize(5);
		String requestJson = ow.writeValueAsString(criteria);
		when(service.findAll()).thenReturn(buildResult());
		mockMvc.perform(post("/api/photos").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk());

	}

	private List<Photo> buildResult() {
		Photo photo = new Photo();
		photo.setId(1);
		photo.setFilename("test.jpg");
		photo.setLocation("Paris");
		photo.setUsername("vballada");
		photo.setDatetime(LocalDateTime.now());
		List<Photo> photos = new ArrayList<>();
		photos.add(photo);
		return photos;
	}

}
