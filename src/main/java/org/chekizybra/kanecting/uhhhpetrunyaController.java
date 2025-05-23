package org.chekizybra.kanecting;
import okhttp3.*;

        import java.io.IOException;

public class uhhhpetrunyaController {
    private static final String SUPABASE_URL = "https://dsonncuemnjmovdfnriw.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRzb25uY3VlbW5qbW92ZGZucml3Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NzgyODI2MCwiZXhwIjoyMDYzNDA0MjYwfQ.fBJqLWCjRlp6uv1M_Nyb0pGxEUNC_FVceo5sxyv7JdE";

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        //insertMeme("абаюдна","tictok",2025);
        fetchAllMemes();
    }
    public static String insertMeme(String mem, String origin, int year) throws IOException {
        String json = "{"
                + "\"meme\": \"" + mem + "\","
                + "\"origin\": \"" + origin + "\","
                + "\"year\": " + year
                + "}";

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/memes?select=*")
                .post(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=representation")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String fetchAllMemes() throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/memes?select=*")
                .get()
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String deleteMemeById(int id) throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/memes?id=eq." + id)
                .delete()
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Prefer", "return=representation")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}