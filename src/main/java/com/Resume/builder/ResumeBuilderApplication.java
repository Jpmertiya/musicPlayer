package com.Resume.builder;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import jakarta.annotation.PostConstruct;

/**
 * @author Jaspal Singh
 * @since new {@link Date}
 * @version 0.1
 */
@SpringBootApplication
public class ResumeBuilderApplication {
	
	@PostConstruct
	public void apicheck() throws InterruptedException, ExecutionException {
		Future<HttpResponse<JsonNode>> jsonAsync = Unirest.get("https://newsapi.org/v2/top-headlines?country=in&apiKey=7c5e849941684871bbceccc8b1e98b23").asJsonAsync();
		System.out.println(jsonAsync);
		System.out.println(jsonAsync.get().getBody().getObject().get("articles"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ResumeBuilderApplication.class, args);

	}

}
