package com.theagilemonkeys.webcrawler.services;

import com.theagilemonkeys.webcrawler.config.EmbeddingsSpaceConfiguration;
import com.theagilemonkeys.webcrawler.dtos.ScrapingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import com.theagilemonkeys.ellmental.embeddingsspace.EmbeddingsSpaceComponent;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.embeddingsstore.pinecone.PineconeEmbeddingsStore;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class ScrapingEmbeddingService {
    private final EmbeddingsSpaceComponent embeddingsSpaceComponent;

    ScrapingEmbeddingService(EmbeddingsSpaceConfiguration embeddingsSpaceConfiguration) {
        this.embeddingsSpaceComponent = new EmbeddingsSpaceComponent(
                new OpenAIEmbeddingsModel(embeddingsSpaceConfiguration.openaiKey()),
                new PineconeEmbeddingsStore(embeddingsSpaceConfiguration.pineconeUrl(), embeddingsSpaceConfiguration.pineconeKey())
        );
    }

    public ScrapingResult scrape(String url, String selector, Integer limit) throws IOException {
        try {
            Document body = Jsoup.connect(url).get();
            Elements selectElements = body.select(selector);
            ArrayList<String> scrapingResultsText = new ArrayList<>();

            if (limit == null)
                limit = selectElements.size();
            int i = 0;
            for (Element element : selectElements) {
                if (i == limit)
                    break;
                scrapingResultsText.add(element.text());

                i++;
            }

            return new ScrapingResult(body.title(), scrapingResultsText, url);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String generateEmbedding(String text) {
        try {
            embeddingsSpaceComponent.save(text);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED";
        }
    }
}
