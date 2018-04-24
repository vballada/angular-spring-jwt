package vballada.photosapp.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PhotosappController.class, secure = false)
public class PhotosappControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhotoRepository service;

	@Test
	public void testPhotos() throws Exception {
		when(service.findAll()).thenReturn(buildResult());
		mockMvc.perform(
				get("/api/photos").param("pageNumber", "0").param("size", "5").param("sort", "id").param("dir", "asc"))
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
