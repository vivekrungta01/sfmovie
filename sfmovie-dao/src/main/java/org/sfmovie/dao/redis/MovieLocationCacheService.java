package org.sfmovie.dao.redis;

import java.util.List;

import org.sfmovie.common.MovieLocation;
import org.sfmovie.dao.mongo.repositories.MovieLocationRepository;
import org.sfmovie.dao.redis.config.RedisCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MovieLocationCacheService {
	
	@Autowired
	private MovieLocationRepository movieLocationRepository;
	
	@Cacheable(value=RedisCacheConfig.MOVIE_LOCATION_CACHE)
	public List<MovieLocation> getMovieLocationCache() {
		return (List<MovieLocation>)movieLocationRepository.findAll();
	}
}
