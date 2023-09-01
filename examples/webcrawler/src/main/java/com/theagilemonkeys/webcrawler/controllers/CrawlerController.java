package com.theagilemonkeys.webcrawler.controllers;

import com.theagilemonkeys.webcrawler.dtos.GenerateEmbeddingsResponse;
import com.theagilemonkeys.webcrawler.dtos.GenerateEmbeddingsResult;
import com.theagilemonkeys.webcrawler.dtos.ScrapingResponse;
import com.theagilemonkeys.webcrawler.dtos.ScrapingResult;
import com.theagilemonkeys.webcrawler.services.ScrapingEmbeddingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/learn")
public class CrawlerController {
    private final ScrapingEmbeddingService learn;

    CrawlerController(@Autowired ScrapingEmbeddingService learn) {
        this.learn = learn;
    }

    @PostMapping("/scrape")
    public ScrapingResponse scrape(@RequestBody LearnRequest request) {
        ArrayList<ScrapingResult> results = new ArrayList<>();
        for (SiteSelector siteSelector : request.siteSelectors()) {
            try {
                results.add(learn.scrape(siteSelector.url(), siteSelector.selector(), siteSelector.limit()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return new ScrapingResponse(results);
    }

    @PostMapping("/generateembedding")
    public GenerateEmbeddingsResponse generateEmbedding(@RequestBody LearnRequest request) {
        ArrayList<ScrapingResult> results = new ArrayList<>();

        request.siteSelectors().forEach(siteSelector ->
        {
            try {
                results.add(learn.scrape(siteSelector.url(), siteSelector.selector(), siteSelector.limit()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        ArrayList<GenerateEmbeddingsResult> generateEmbeddingsResults = new ArrayList<>();
        results.forEach(scrapingResult -> {
            scrapingResult.content().forEach(content -> {
                String status = learn.generateEmbedding(content);
                if (!content.isEmpty()) {
                    generateEmbeddingsResults.add(new GenerateEmbeddingsResult(scrapingResult.title(), status, scrapingResult.siteUrl()));
                } else {
                    System.out.println("Skipping empty content");
                }
            });
        });

        return new GenerateEmbeddingsResponse(generateEmbeddingsResults);
    }
}
