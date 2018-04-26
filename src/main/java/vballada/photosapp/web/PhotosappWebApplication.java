package vballada.photosapp.web;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

/**
 * @author BALLADA_V
 *         <p>
 *         Application Runner
 *         </p>
 */
@SpringBootApplication
@EnableWebSecurity
public class PhotosappWebApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotosappWebApplication.class);
	
	private static final String [] CITIES = {"Paris", "Vannes", "Amiens", "Caen", "Rouen"};
	
	private static final String [] USERS = {"vballada", "a_user", "b_user", "c_user", "d_user"};
	
	private Random generator = new Random();

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
				Stream.of("photo_1.jpg", "photo_2.jpg", "photo_3.jpg", "photo_4.jpg", "photo_5.jpg", "photo_6.jpg",
						"photo_7.jpg", "photo_8.jpg", "photo_9.jpg", "photo_10.jpg", "photo_11.jpg").forEach(name -> {
							Photo photo = new Photo();
							photo.setId(Integer.valueOf(name.substring(6, name.length() - 4)));
							photo.setDatetime(LocalDateTime.now());
							photo.setUsername(USERS[generator.nextInt(USERS.length)]);
							photo.setLocation(CITIES[generator.nextInt(CITIES.length)]);
							photo.setFilename(name);
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
