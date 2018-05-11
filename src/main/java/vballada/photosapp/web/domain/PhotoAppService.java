package vballada.photosapp.web.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PhotoAppService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Photo> findByCriteria(PhotoCriteria criteria){
		Sort pageSort = Sort.by(Direction.fromString(criteria.getDir()), criteria.getSort());
		Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getSize(), pageSort);
		Criteria mdbcriteria = new Criteria();
		mdbcriteria = mdbcriteria.and("location").is(criteria.getLocation());
		Query query = new Query(mdbcriteria);
		query.with(pageable);
		return mongoTemplate.find(query, Photo.class);
		
	}

}
