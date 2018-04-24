package vballada.photosapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoRepository;

/**
 * @author BALLADA_V
 *         <p>
 *         Rest API for operations on {@link Photo}
 *         </p>
 */
@RestController
public class PhotosappController {

	/**
	 * Repository access for {@link Photo}
	 */
	@Autowired
	private PhotoRepository repository;

	/**
	 * @param pageable
	 * @return The photos collection
	 */
	@RequestMapping("/api/photos")
	@ResponseBody
	public Page<Photo> photos(Integer pageNumber, Integer size) {
		return repository.findAll(PageRequest.of(pageNumber, size));
	}
}
