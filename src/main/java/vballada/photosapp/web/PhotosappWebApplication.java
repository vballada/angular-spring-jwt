package vballada.photosapp.web;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessResourceFailureException;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

/**
 * @author BALLADA_V
 *         <p>
 *         Application Runner
 *         </p>
 */
@SpringBootApplication
public class PhotosappWebApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotosappWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PhotosappWebApplication.class, args);
	}

	/**
	 * Inserting some data into the database
	 * 
	 * @param repository
	 *            The Spring Data Repository
	 * @return An instance of {@link ApplicationRunner}
	 */

	@Bean
	public ApplicationRunner init(PhotoRepository repository) {
		try {
			repository.deleteAll();
			return args -> {
				Stream.of("photo_1.jpg", "photo_2.jpg", "photo_3.jpg", "photo_4.jpg").forEach(name -> {
					Photo photo = new Photo();
					photo.setId(Integer.valueOf(name.substring(6, 7)));
					photo.setDatetime(LocalDateTime.now());
					photo.setUsername("vballada");
					photo.setLocation("Paris");
					repository.save(photo);
				});
				repository.findAll().forEach(System.out::println);
			};
		} catch (DataAccessResourceFailureException e) {
			LOGGER.warn("Cannot get MongoDB instance", e);
			return args -> {

			};
		}
	}

}
