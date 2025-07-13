package org.irmc.industrialrevival.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Utility class for making HTTP requests to external APIs.
 * <p>
 * This class provides methods for making synchronous and asynchronous HTTP requests,
 * with support for JSON parsing and error handling. It's primarily used for
 * accessing external APIs like GitHub API.
 * </p>
 *
 * @author balugaq
 * @since 1.0
 */
public class HttpUtil {
    
    private static final String USER_AGENT = "IndustrialRevival/1.0";
    private static final Gson GSON = new Gson();
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    
    /**
     * Makes a synchronous HTTP GET request to the specified URL.
     *
     * @param urlString the URL to make the request to
     * @return the response body as a string, or null if the request failed
     * @throws IOException if an I/O error occurs
     */
    @Nullable
    public static String makeGetRequest(@NotNull String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
    
    /**
     * Makes an asynchronous HTTP GET request to the specified URL.
     *
     * @param urlString the URL to make the request to
     * @return a CompletableFuture that will contain the response body as a string
     */
    @NotNull
    public static CompletableFuture<String> makeAsyncGetRequest(@NotNull String urlString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return makeGetRequest(urlString);
            } catch (IOException e) {
                throw new RuntimeException("Failed to make HTTP request", e);
            }
        }, EXECUTOR);
    }
    
    /**
     * Parses a JSON string into a JsonObject.
     *
     * @param json the JSON string to parse
     * @return the parsed JsonObject, or null if parsing failed
     */
    @Nullable
    public static JsonObject parseJson(@NotNull String json) {
        try {
            JsonElement element = JsonParser.parseString(json);
            return element.isJsonObject() ? element.getAsJsonObject() : null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Parses a JSON string into a JsonArray.
     *
     * @param json the JSON string to parse
     * @return the parsed JsonArray, or null if parsing failed
     */
    @Nullable
    public static JsonArray parseJsonArray(@NotNull String json) {
        try {
            JsonElement element = JsonParser.parseString(json);
            return element.isJsonArray() ? element.getAsJsonArray() : null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Gets an integer value from a JsonObject with a fallback to default value.
     *
     * @param json the JsonObject to get the value from
     * @param key the key to look for
     * @param defaultValue the default value to return if the key doesn't exist or is not a number
     * @return the integer value, or the default value if not found
     */
    public static int getIntOrDefault(@NotNull JsonObject json, @NotNull String key, int defaultValue) {
        try {
            JsonElement element = json.get(key);
            if (element != null && element.isJsonPrimitive()) {
                return element.getAsInt();
            }
        } catch (Exception e) {
            // Ignore parsing errors and return default value
        }
        return defaultValue;
    }
    
    /**
     * Shuts down the executor service used for async requests.
     * <p>
     * This method should be called when the plugin is being disabled
     * to properly clean up resources.
     * </p>
     */
    public static void shutdown() {
        EXECUTOR.shutdown();
    }
} 