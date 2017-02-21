package org.sfmovie.common;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "movie_location")
public class MovieLocation implements Serializable {
	
	private static final long serialVersionUID = 6894953557838306575L;
	
	@Id
	private ObjectId id;
	@Field("actor_1")
	private String actor1;
	@Field("actor_2")
	private String actor2;
	private String director;
	@Field("release_year")
	private String releaseYear;
	private String locations;
	private String title;
	private String lat;
	private String lng;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getActor1() {
		return actor1;
	}
	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}
	public String getActor2() {
		return actor2;
	}
	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "MovieLocation [id=" + id + ", actor1=" + actor1 + ", actor2=" + actor2 + ", director=" + director
				+ ", releaseYear=" + releaseYear + ", locations=" + locations + ", title=" + title + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
	
}
