package com.Resume.builder.Entity;

import org.springframework.stereotype.Component;
/**
 * This call is used to map songs
 */
@Component
public class song {
	private String name;
	private String url;
	private String imageUrl;
	private String artist;
	public song() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public song(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public song(String name, String url, String imageUrl, String artist) {
		super();
		this.name = name;
		this.url = url;
		this.imageUrl = imageUrl;
		this.artist = artist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
	
	
}
