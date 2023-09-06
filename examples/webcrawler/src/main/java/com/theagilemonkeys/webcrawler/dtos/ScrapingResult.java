package com.theagilemonkeys.webcrawler.dtos;

import java.util.ArrayList;

public record ScrapingResult(
        String title,
        ArrayList<String> content,
        String siteUrl
) {
}
