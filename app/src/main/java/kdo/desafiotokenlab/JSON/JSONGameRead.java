package kdo.desafiotokenlab.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kdo.desafiotokenlab.modelo.Game;

/**
 * Created by kaike on 21/03/2018.
 */

public class JSONGameRead {

    public List<Game> gameObject() {
        List<Game> listGame = new ArrayList<>();
        try {
            String URL = HttpConnections.get("https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/game");
            JSONObject jsonObject = new JSONObject(URL);
            JSONArray arrayGames = jsonObject.getJSONArray("games");
            for(int i = 0; i < arrayGames.length(); i++){
                JSONObject gameObject = arrayGames.getJSONObject(i);
                Game game = new Game();
                game.setId(gameObject.getInt("id"));
                game.setNome(gameObject.getString("name"));
                game.setImg(gameObject.getString("image"));
                game.setRelease_date(gameObject.getString("release_date"));
                game.setTrailer(gameObject.getString("trailer"));
                game.setPlatforms(String.valueOf(gameObject.getJSONArray("platforms")));
                listGame.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listGame;
    }

}
