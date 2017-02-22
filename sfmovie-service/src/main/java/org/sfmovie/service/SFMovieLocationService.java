package org.sfmovie.service;

import java.util.List;

import org.sfmovie.common.MovieLocation;
import org.sfmovie.dao.redis.MovieLocationCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SFMovieLocationService 
{
	@Autowired
	private MovieLocationCacheService movieLocationCacheService;
    public List<MovieLocation> getAllSFMovieLocation() {
    	return movieLocationCacheService.getMovieLocationCache();
    }
}
