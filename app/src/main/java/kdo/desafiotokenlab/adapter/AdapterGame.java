package kdo.desafiotokenlab.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kdo.desafiotokenlab.R;
import kdo.desafiotokenlab.modelo.Game;

/**
 * Created by kaike on 21/03/2018.
 */

public class AdapterGame extends ArrayAdapter<Game> {
    private Context context;
    private int resources;
    private List<Game> itensGames;

    public AdapterGame(@NonNull Context context, @LayoutRes int resource, @NonNull List<Game> itemGame) {
        super(context, resource, itemGame);
        this.context = context;
        this.resources = resource;
        this.itensGames = itemGame;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resources, null);
        }
        TextView valorColunaNomeGame = convertView.findViewById(R.id.txtNameGame);
        TextView valorColunaDataGame = convertView.findViewById(R.id.txtDataGame);
        TextView valorColunaPlataforma = convertView.findViewById(R.id.txtPlataformsGame);
        Game game = new Game();
        game = this.itensGames.get(position);
        ImageView img = convertView.findViewById(R.id.imgGame);
        Picasso.get().load(game.getImg()).into(img);
//        Bitmap bitImg = BitmapFactory.decodeFile(game.getImg());
//        img.setImageBitmap(bitImg);
        valorColunaNomeGame.setText(context.getString(R.string.name) + ": " + game.getNome() + "");
        valorColunaDataGame.setText(context.getString(R.string.date_release) + ": " + game.getRelease_date() + "");
        valorColunaPlataforma.setText(context.getString(R.string.platforms) + ": " + game.getPlatforms() + "");
        return convertView;
    }
}
