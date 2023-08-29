package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common;

import okhttp3.*;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class PineconeService {
    private static OkHttpClient client;
    private final String url;
    private final String apiKey;

    public PineconeService(String apiKey, String url) {
        this.apiKey = apiKey;
        this.url = url;
        client = new OkHttpClient();
    }

    public String post(String path, String bodyString) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(bodyString, JSON);

        Request request = new Request.Builder()
                .url(url + path)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() >= HTTP_BAD_REQUEST) {
                throw new IOException(url);
            }

            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                return responseBody.string();
            } else {
                return null;
            }
        }
    }

}
