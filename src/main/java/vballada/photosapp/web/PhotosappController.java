package vballada.photosapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vballada.photosapp.web.domain.Photo;
import vballada.photosapp.web.domain.PhotoAppService;
import vballada.photosapp.web.domain.PhotoCriteria;

/**
 * @author BALLADA_V
 *         <p>
 *         Rest API for operations on {@link Photo}
 *         </p>
 */
@RestController
public class PhotosappController {

	@Autowired
	private PhotoAppService service;

	/**
	 * @param pageable
	 * @return The photos collection
	 */
	@PostMapping("/api/photos")
	@ResponseBody
	public Page<Photo> photos(@RequestBody PhotoCriteria criteria) {
		return service.findByCriteria(criteria);
	}
}
