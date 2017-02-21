package org.sfmovie.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sfmovie.common.MovieLocation;
import org.sfmovie.dao.mongo.repositories.MovieLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sfmovie/*")
public class SFMovieController {
	
	@Autowired
	private MovieLocationRepository movieLocationRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String createOrUpdateReward(HttpServletRequest request, HttpServletResponse response) {
    	List<MovieLocation> movieLocations = (List<MovieLocation>) movieLocationRepository.findAll();
    	
    	System.out.println(movieLocations);
    	return "index";
    }
}
