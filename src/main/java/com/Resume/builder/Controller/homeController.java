package com.Resume.builder.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Resume.builder.API.musicApi;
import com.Resume.builder.Entity.song;
/**
 * This is Controller class that map url with methods
 */
@Controller
@RequestMapping("/fragger")
public class homeController {

	/**
	 * @author Jpmertiya
	 * @param no param
	 * @return html page with name "index"
	 */
	@GetMapping("/home")
	public String index(Model model) {
		List<song> songs = fetchSong("eminem");
		
		model.addAttribute("song", songs);
		System.out.println(songs.toString());
		return "index";
	}

	/**
	 * This method return list of songs.
	 * @param name of song/writer/singer
	 * @return list of songs
	 */
	@GetMapping("/search/{author}")
	public String author(@RequestParam("author") String name, Model model) {
		List<song> songs = fetchSong(name);
		model.addAttribute("song", songs);
		System.out.println(songs.toString());
		return "index";
	}

	public List<song> fetchSong(String name) {
		List<song> songs = new ArrayList<>();
		try {
			Scanner st=new Scanner(name);
			String artist="";
			while(st.hasNext()) {
				artist+=st.next();
			}
			System.out.println(artist);
			songs = musicApi.songs(artist);
			st.close();
		} catch (IOException e) {
			songs.add(new song("not found", "invalid data"));
			e.printStackTrace();
		}
		return songs;
	}
}
