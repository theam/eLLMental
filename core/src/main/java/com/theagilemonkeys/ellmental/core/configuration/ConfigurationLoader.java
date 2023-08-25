package com.theagilemonkeys.ellmental.core.configuration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConfigurationLoader {

    public static String getDefaultConfigurationPath() {
        var cwd = System.getProperty("user.dir");
        return cwd + "/configuration.json";
    }

    public static Optional<Configuration> loadConfiguration() {
        var configurationPath = getDefaultConfigurationPath();
        var gson = new Gson();

        // read the string inside the path
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(configurationPath));
            var configurationContent = reader.lines().collect(Collectors.joining("\n"));
            reader.close();

            // parse the string into a RawConfiguration object
            var rawConfiguration = gson.fromJson(configurationContent, RawConfiguration.class);
            var features = rawConfiguration.features().stream().map(feature -> {
                if (feature instanceof String) {
                    return new Feature((String) feature, new HashMap<>());
                } else if (feature instanceof Feature) {
                    return (Feature) feature;
                } else {
                    throw new RuntimeException("Invalid feature type");
                }
            });

            var conf = new Configuration(features.collect(Collectors.toList()));
            return Optional.of(conf);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
