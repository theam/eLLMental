package com.theagilemonkeys.webcrawler.controllers;

import java.util.ArrayList;

public record LearnRequest(
        ArrayList<SiteSelector> siteSelectors

) {
}

record SiteSelector(
        String selector,
        String url,
        Integer limit
) {
}
