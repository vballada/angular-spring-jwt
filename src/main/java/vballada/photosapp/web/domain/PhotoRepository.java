package vballada.photosapp.web.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author BALLADA_V
 *         <p>
 *         Repository For Entity {@link Photo}
 *         </p>
 */
public interface PhotoRepository extends MongoRepository<Photo, Long> {

}
