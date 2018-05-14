package vballada.photosapp.web.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Service
public class PhotoAppService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public Page<Photo> findByCriteria(PhotoCriteria criteria) {
		Sort pageSort = Sort.by(Direction.fromString(criteria.getDir()), criteria.getSort());
		Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getSize(), pageSort);
		Criteria mdbcriteria = new Criteria();
		if (criteria.getLocation() != null) {
			mdbcriteria = mdbcriteria.and("location").is(criteria.getLocation());
		}
		Query query = new Query(mdbcriteria);
		query.with(pageable);
		List<Photo> result = mongoTemplate.find(query, Photo.class);
		return PageableExecutionUtils.getPage(result, pageable, () -> mongoTemplate.count(query, Photo.class));

	}

}
