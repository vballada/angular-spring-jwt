package vballada.photosapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vballada.photosapp.web.domain.Criteria;
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
	@PostMapping("/api/photos")
	@ResponseBody
	public Page<Photo> photos(@RequestBody Criteria criteria) {
		Sort pageSort = Sort.by(Direction.fromString(criteria.getDir()), criteria.getSort());
		return repository.findAll(PageRequest.of(criteria.getPageNumber(), criteria.getSize(), pageSort));
	}
}
