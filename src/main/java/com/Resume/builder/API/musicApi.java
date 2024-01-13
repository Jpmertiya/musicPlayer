package com.Resume.builder.API;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.Resume.builder.Entity.song;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
/**
 * This class call Music Api 
 */
public class musicApi {

	public static List<song> songs(String author) throws IOException {
		final String RAPIDAPI_KEY = "4da15ffc60mshf91d916c2f4a833p1e5263jsn43d353de7a72";

		List<song> songName = new ArrayList<>();
		try {
			String apiUrl = "https://deezerdevs-deezer.p.rapidapi.com/search?q=" + author;

			// Make the API request
			HttpResponse<JsonNode> response = Unirest.get(apiUrl).header("X-RapidAPI-Key", RAPIDAPI_KEY).asJson();

			// Check the response status
			int status = response.getStatus();

			if (status == 200) {
				// If the request is successful, handle the API response
				JsonNode responseBody = response.getBody();
				try {
					for (int i = 0; i <= 25; i++) {
						String song_name = responseBody.getObject().getJSONArray("data").getJSONObject(i)
								.getString("title");
						String song_link = responseBody.getObject().getJSONArray("data").getJSONObject(i)
								.getString("preview");
						String artistPhoto = responseBody.getObject().getJSONArray("data").getJSONObject(i)
								.getJSONObject("artist").getString("picture_medium");
						String artistName = responseBody.getObject().getJSONArray("data").getJSONObject(i)
								.getJSONObject("artist").getString("name");
						// downloadAndSaveMP3(song_name,"fragger"+i+".jpg");
						com.Resume.builder.Entity.song s = new com.Resume.builder.Entity.song(song_name, song_link,
								artistPhoto, artistName);
						songName.add(s);
					}
				} catch (JSONException e) {
					// TODO: handle exception
				}
			} else {
				System.err.println("API Request Failed with Status Code: " + status);
			}

		} catch (UnirestException e) {

		}
		return songName;
	}

	// this is to externally download song eawsy or what
	private static void downloadAndSaveMP3(String mp3Url, String fileName) throws IOException {
		URL url = new URL(mp3Url);
		try (InputStream in = url.openStream()) {
			Path outputPath = Path.of(fileName);
			Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("MP3 file saved as " + fileName);
		}
	}
}
