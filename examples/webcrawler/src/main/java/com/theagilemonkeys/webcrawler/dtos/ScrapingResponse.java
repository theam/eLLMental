package com.theagilemonkeys.webcrawler.dtos;

import java.util.ArrayList;

public record ScrapingResponse(
        ArrayList<ScrapingResult> results
) {
}

