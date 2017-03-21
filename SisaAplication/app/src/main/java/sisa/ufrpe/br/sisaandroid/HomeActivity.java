package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static sisa.ufrpe.br.sisaandroid.R.attr.contentInsetLeft;
import static sisa.ufrpe.br.sisaandroid.R.attr.contentInsetStartWithNavigation;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ResultadoAsync {

    Button btnMatriculaHome,
            btnGradeHome,
            btnAnteriorHome,
            btnHistoricoHome,
            btnRecomendacaoHome;
    TextView nomeSide, emailSide;
    String id;
    SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sisa_brand_menu);

        String nomeAlunoConn = "";

        user = PreferenceManager.getDefaultSharedPreferences(this);
        id = user.getString("id", "id");

        try {
            nomeAlunoConn = new GetAsyncTask("http://09250e43.ngrok.io/alunos/"+id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        JSONObject jObject;
        String nomeAluno = "";
        String emailAluno = "";
        int  idAluno;

        try {
            jObject = new JSONObject(nomeAlunoConn);
            idAluno = jObject.getInt("id");
            nomeAluno = jObject.getString("nome");
            emailAluno = jObject.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView nome = (TextView) findViewById(R.id.alunoNome);
        nome.setText(" " + nomeAluno.split(" ")[0]);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMensagem = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(intentMensagem);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setBackgroundColor(getResources().getColor(R.color.black));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        btnMatriculaHome = (Button)findViewById(R.id.btnMatriculaHome);
        btnGradeHome = (Button)findViewById(R.id.btnGradeHome);
        btnAnteriorHome = (Button)findViewById(R.id.btnAnteriorHome);
        btnHistoricoHome = (Button)findViewById(R.id.btnHistoricoHome);
        btnRecomendacaoHome = (Button)findViewById(R.id.btnRecomendacaoHome);

        nomeSide = (TextView) header.findViewById(R.id.nomeMenu);
        emailSide = (TextView) header.findViewById(R.id.emailMenu);

        nomeSide.setText(nomeAluno);
        emailSide.setText(emailAluno);

        btnMatriculaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGrade = new Intent(HomeActivity.this,GradeActivity.class);
                startActivity(intentGrade);
            }
        });

        btnGradeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGrade = new Intent(HomeActivity.this,GradeActivity.class);
                startActivity(intentGrade);
            }
        });

        btnAnteriorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSugestao = new Intent(HomeActivity.this,VerSugestoesActivity.class);
                startActivity(intentSugestao);
            }
        });

        btnHistoricoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGrade = new Intent(HomeActivity.this, ModificacaoActivity.class);
                startActivity(intentGrade);
            }
        });

        btnRecomendacaoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json = new JSONObject();
                cadastrar(json);
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_feedback) {
            Intent intentFeedback = new Intent(HomeActivity.this, FeedbackActivity.class);
            startActivity(intentFeedback);

        } else if (id == R.id.nav_sugestion) {
            Intent intentSugestao = new Intent(HomeActivity.this, SugestaoActivity.class);
            startActivity(intentSugestao);
        } else if (id == R.id.nav_discipline) {
            Intent intentGrade = new Intent(HomeActivity.this, GradeActivity.class);
            startActivity(intentGrade);
        }else if(id == R.id.nav_modificacao){
            Intent intentModificacao = new Intent(HomeActivity.this, Cadastro2Activity.class);
            startActivity(intentModificacao);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cadastrar(JSONObject json){
        Toast.makeText(getApplicationContext(), "Suas sugestões estão sendo geradas. Assim que estiverem prontas você será redirecionado.", Toast.LENGTH_LONG);
        PostAsyncTask conexao = new PostAsyncTask("http://09250e43.ngrok.io/sugestao/"+id, json);
        conexao.delegate = this;
        conexao.execute();
    }

    @Override
    public void processFinish(String output) {
        Intent intentHome = new Intent(HomeActivity.this, VerSugestoesActivity.class);
        startActivity(intentHome);
        this.finish();
    }
}
