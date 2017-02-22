package org.sfmovie.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sfmovie.common.MovieLocation;
import org.sfmovie.service.SFMovieLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sfmovie/*")
public class SFMovieController {
	
	@Autowired
	private SFMovieLocationService sFMovieLocationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String getMap(HttpServletRequest request, HttpServletResponse response) {
    	return "index";
    }
	
	@RequestMapping(value = "/movie/getAll", method = RequestMethod.GET)
    public @ResponseBody List<MovieLocation> getMovie(HttpServletRequest request, HttpServletResponse response) {
    	List<MovieLocation> movieLocations = sFMovieLocationService.getAllSFMovieLocation();
    	return movieLocations;
    }
	
}
