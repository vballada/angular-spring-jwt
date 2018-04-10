package vballada.photosapp.web;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

@SpringBootApplication
public class PhotosappWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotosappWebApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PhotoRepository repository) {
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
	}
}
