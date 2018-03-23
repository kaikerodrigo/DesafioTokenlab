package kdo.desafiotokenlab.modelo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kaike on 21/03/2018.
 */

public class Game implements Serializable, Comparable<Game>{
    public int id = 0;
    public String nome = "", release_date = "", trailer = "", platforms = "", img = "";
    public List<String> listNamePlataforms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getListNamePlataforms() {
        return listNamePlataforms;
    }

    public void setListNamePlataforms(List<String> listNamePlataforms) {
        this.listNamePlataforms = listNamePlataforms;
    }

    @Override
    public int compareTo(@NonNull Game game) {
        return this.getNome().compareTo(game.getNome());
    }
}
