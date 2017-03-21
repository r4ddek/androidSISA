package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class SugestaoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     LinearLayoutManager llm;
     RecyclerView recyclerSugestao;
     SugestaoAdapter sugestaoAdapter;
     ArrayList<SugestaoCard> sugestoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerSugestao = (RecyclerView)findViewById(R.id.recyclerView_sugestao);
        llm = new LinearLayoutManager(this);
        recyclerSugestao.setHasFixedSize(true);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSugestao.setLayoutManager(llm);
        sugestoes = new ArrayList<>();

        sugestoes.add(new SugestaoCard("Primeira Sugestão", "Testando a primeira sugestão pra ver se ta de boa"));

        sugestoes.add(new SugestaoCard("Segunda Sugestão", "Testando a segunda sugestão pra ver se ta de boa"));

        sugestoes.add(new SugestaoCard("Terceira Sugestão", "Testando a terceira sugestão pra ver se ta de boa"));

        sugestaoAdapter = new SugestaoAdapter(sugestoes, this);
        recyclerSugestao.setLayoutManager(llm);
        recyclerSugestao.setAdapter(sugestaoAdapter);
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
        getMenuInflater().inflate(R.menu.sugestao, menu);
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
            Intent intentHome = new Intent(SugestaoActivity.this, HomeActivity.class);
            startActivity(intentHome);
        } else if (id == R.id.nav_feedback) {
            Intent intentFeedback = new Intent(SugestaoActivity.this, FeedbackActivity.class);
            startActivity(intentFeedback);
        } else if (id == R.id.nav_modificacao) {
            Intent intentModificacao = new Intent(SugestaoActivity.this, Cadastro2Activity.class);
            startActivity(intentModificacao);
        } else if (id == R.id.nav_sugestion) {

        } else if (id == R.id.nav_discipline) {
            Intent intentGrade = new Intent(SugestaoActivity.this, GradeActivity.class);
            startActivity(intentGrade);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
