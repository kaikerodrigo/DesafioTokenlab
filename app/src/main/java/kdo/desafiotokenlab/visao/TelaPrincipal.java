package kdo.desafiotokenlab.visao;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import kdo.desafiotokenlab.JSON.JSONGameRead;
import kdo.desafiotokenlab.JSON.JSONUserRead;
import kdo.desafiotokenlab.R;
import kdo.desafiotokenlab.adapter.AdapterGame;
import kdo.desafiotokenlab.modelo.Game;
import kdo.desafiotokenlab.modelo.Usuario;

public class TelaPrincipal extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView txtNameUser, txtLastNameUser, txtBrithdayUser,
    txtEmail, txtCountry, txtCity, txtAddress, txtToken;
    private ImageView imageViewAvatarUser;
    private LinearLayout linearLayout1, linearLayout2;
    private RelativeLayout relativeLayout;
    private List<Game> listDeGames = new ArrayList<>();
    private Game game = new Game();
    private JSONGameRead jsonGameRead = new JSONGameRead();
    private ListView listViewGames, listViewDestaqueGame;
    private List<Game> listGameDestaqueSorteado = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_destaque:
//                    mTextMessage.setText(R.string.title_destaque);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);

//                    preencherDestaque();
                    return true;
                case R.id.navigation_lista_de_jogos:
//                    mTextMessage.setText(R.string.title_lista_de_jogos);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                    preencherListaDeGames();
                    return true;
                case R.id.navigation_perfil:
//                    mTextMessage.setText(R.string.title_perfil);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    preencherPerfil();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (verificaConexao()) {
//        mTextMessage = (TextView) findViewById(R.id.message);
            txtNameUser = (TextView) findViewById(R.id.txtRespostaNameUser);
            txtLastNameUser = (TextView) findViewById(R.id.txtRespostaLastNameUser);
            txtEmail = (TextView) findViewById(R.id.txtRespostaEmailUser);
            txtAddress = (TextView) findViewById(R.id.txtRespostaAddressUser);
            txtCity = (TextView) findViewById(R.id.txtRespostaCityUser);
            txtCountry = (TextView) findViewById(R.id.txtRespostaCountryUser);
            txtToken = (TextView) findViewById(R.id.txtRespostaTokenUser);
            txtBrithdayUser = (TextView) findViewById(R.id.txtRespostaBrithdayUser);
            imageViewAvatarUser = (ImageView) findViewById(R.id.imageViewAvatar);
            linearLayout1 = (LinearLayout) findViewById(R.id.linearLayoutDestaque);
            linearLayout2 = (LinearLayout) findViewById(R.id.linearLayoutListaDeGame);
            relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutPerfil);
            listViewDestaqueGame = (ListView) findViewById(R.id.listViewDestaqueGame);
            listViewGames = (ListView) findViewById(R.id.listViewGames);
            listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent retorno = new Intent(TelaPrincipal.this, TelaVideo.class);
                    retorno.putExtra("trailerVideo", listDeGames.get(position).getTrailer());
                    startActivity(retorno);
                }
            });
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            preencherDestaque();
        }else{
            Toast.makeText(getApplicationContext(), R.string.message_erro_internet, Toast.LENGTH_SHORT).show();
            this.finish();
        }
        listViewDestaqueGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent retorno = new Intent(TelaPrincipal.this, TelaVideo.class);
                retorno.putExtra("trailerVideo", listGameDestaqueSorteado.get(position).getTrailer());
                startActivity(retorno);
            }
        });
    }

    public void preencherDestaque () {
        listDeGames.clear();
        listDeGames = jsonGameRead.gameObject();
        Random random = new Random();
        listGameDestaqueSorteado.add(listDeGames.get(random.nextInt(listDeGames.size())));
        if (listGameDestaqueSorteado != null) {
            AdapterGame adapter = new AdapterGame(this, R.layout.game_row_list, listGameDestaqueSorteado);
            listViewDestaqueGame.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), R.string.message_erro_list_game_vazia, Toast.LENGTH_SHORT).show();
        }
    }

    public void preencherListaDeGames() {
        listDeGames.clear();
        listDeGames = jsonGameRead.gameObject();
        Collections.sort(listDeGames);
        if (listDeGames != null) {
            AdapterGame adapter = new AdapterGame(this, R.layout.game_row_list, listDeGames);
            listViewGames.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), R.string.message_erro_list_game_vazia, Toast.LENGTH_SHORT).show();
        }
    }

    public void preencherPerfil(){
        try {
            Usuario usuario = new Usuario();
            JSONUserRead jsonUserRead = new JSONUserRead();
            usuario = jsonUserRead.usuarioObject();
            txtNameUser.setText(usuario.getName());
            txtLastNameUser.setText(usuario.getLastName());
            txtEmail.setText(usuario.getEmail());
            txtToken.setText(usuario.getToken());
            txtCity.setText(usuario.getCity());
            txtCountry.setText(usuario.getCountry());
            txtAddress.setText(usuario.getAddress());
            ImageView img = this.findViewById(R.id.imageViewAvatar);
            Picasso.get().load(usuario.getAvatar()).into(img);
//            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String dataFormatada = "";
            dataFormatada = String.valueOf(df.format(
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm", Locale.US).parse(usuario.getBirthday())));
            txtBrithdayUser.setText(dataFormatada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
