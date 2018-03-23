package kdo.desafiotokenlab.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import kdo.desafiotokenlab.modelo.Usuario;

/**
 * Created by kaike on 23/03/2018.
 */

public class JSONUserRead {

    public Usuario usuarioObject() {
        Usuario usuario = new Usuario();
        try {
            String URL = HttpConnections.get("https://dl.dropboxusercontent.com/s/fiqendqz4l1xk61/userinfo");
            JSONObject jsonObject = new JSONObject(URL);
//            usuario.setId(jsonObject.getInt("id"));
            usuario.setName(jsonObject.getString("name"));
            usuario.setLastName(jsonObject.getString("lastname"));
            usuario.setAvatar(jsonObject.getString("avatar"));
            usuario.setEmail(jsonObject.getString("email"));
            usuario.setBirthday(jsonObject.getString("birthday"));
            usuario.setAddress(jsonObject.getString("address"));
            usuario.setCity(jsonObject.getString("city"));
            usuario.setCountry(jsonObject.getString("country"));
            usuario.setToken(jsonObject.getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
