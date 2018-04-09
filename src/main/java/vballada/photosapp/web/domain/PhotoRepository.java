package vballada.photosapp.web.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author BALLADA_V
 *         <p>
 *         Repository pour l'accès à l'entité {@link Photo}
 *         </p>
 */
public interface PhotoRepository extends MongoRepository<Photo, Long> {

}
