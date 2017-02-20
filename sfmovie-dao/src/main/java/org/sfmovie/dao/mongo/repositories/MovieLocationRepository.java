package org.sfmovie.dao.mongo.repositories;

import org.sfmovie.common.MovieLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLocationRepository extends CrudRepository<MovieLocation, Long>{
	
}
