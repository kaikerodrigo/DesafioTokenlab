package kdo.desafiotokenlab.JSON;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kaike on 21/03/2018.
 */

public class HttpConnections {
    public static String get(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resposta = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resposta = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta;
    }
}
