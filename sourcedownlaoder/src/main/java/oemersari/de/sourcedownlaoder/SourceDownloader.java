package oemersari.de.sourcedownlaoder;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SourceDownloader extends AsyncTask {

    private Responce responce;
    private String sLink;

    public SourceDownloader(String _link, Responce _responce) {
        this.responce = _responce;
        this.sLink = _link;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL(sLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            String source = stringBuilder.toString();
            reader.close();
            responce.onFinished(source);
        } catch (UnsupportedEncodingException e) {
            responce.onError("Character Encoding is not supported");
            Log.i("SourceDownloader", "Character Encoding is not supported");
        } catch (MalformedURLException e) {
            responce.onError("Invalid URL");
            Log.i("SourceDownloader", "Invalid URL");
        } catch (IOException e) {
            responce.onError("URL does not exist");
            Log.i("SourceDownloader", "URL does not exist");
        }
        return null;
    }

    public interface Responce {
        void onFinished(String _source);

        void onError(String _ErrorMessage);
    }
}
