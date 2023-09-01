package com.theagilemonkeys.webcrawler.dtos;

import java.util.ArrayList;

public record GenerateEmbeddingsResponse(
        ArrayList<GenerateEmbeddingsResult> results
) {
}

