package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GradeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Integer> arrayDisciplina;
    ArrayList<Disciplina> disciplinas;
    ArrayList<Disciplina> disciplinasPrimeiro;
    ArrayList<Disciplina> disciplinasSegundo;
    ArrayList<Disciplina> disciplinasTerceiro;
    ArrayList<Disciplina> disciplinasQuarto;
    ArrayList<Disciplina> disciplinasQuinto;
    ArrayList<Disciplina> disciplinasSexto;
    ArrayList<Disciplina> disciplinasSetimo;
    ArrayList<Disciplina> disciplinasOitavo;
    ArrayList<Disciplina> disciplinasNono;

    GradeActivityAdapter rv1;
    GradeActivityAdapter rv2;
    GradeActivityAdapter rv3;
    GradeActivityAdapter rv4;
    GradeActivityAdapter rv5;
    GradeActivityAdapter rv6;
    GradeActivityAdapter rv7;
    GradeActivityAdapter rv8;
    GradeActivityAdapter rv9;
    String gradeConn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#000000"));

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.sisa_brand_menu);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

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

        disciplinas=new ArrayList<>();
        disciplinasPrimeiro = new ArrayList<>();
        disciplinasSegundo = new ArrayList<>();
        disciplinasTerceiro = new ArrayList<>();
        disciplinasQuarto = new ArrayList<>();
        disciplinasQuinto = new ArrayList<>();
        disciplinasSexto = new ArrayList<>();
        disciplinasSetimo = new ArrayList<>();
        disciplinasOitavo = new ArrayList<>();
        disciplinasNono = new ArrayList<>();

        try {
            gradeConn = new GetAsyncTask("http://09250e43.ngrok.io/gradeHorario").execute().get();
            Log.d("testando:", gradeConn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONArray jArray = null;
        try {
            jArray = new JSONArray(gradeConn);
            for (int i = 0; i < (jArray.length()); i++) {
                JSONObject json_obj = jArray.getJSONObject(i);
                String nome = json_obj.getString("nome");
                String codigo = json_obj.getString("codigo");
                StringBuffer horarios = new StringBuffer();

                if (!json_obj.getString("segunda").equals("")) {
                    horarios.append("Segundas: " + json_obj.getString("segunda") + " ");
                }
                if (!json_obj.getString("terca").equals("")) {
                    horarios.append("Terças: " + json_obj.getString("terca") + " ");
                }
                if (!json_obj.getString("quarta").equals("")) {
                    horarios.append("Quartas: " + json_obj.getString("quarta") + " ");
                }
                if (!json_obj.getString("quinta").equals("")) {
                    horarios.append("Quintas: " + json_obj.getString("quinta") + " ");
                }
                if (!json_obj.getString("sexta").equals("")) {
                    horarios.append("Sextas: " + json_obj.getString("sexta") + " ");
                }

                int id = json_obj.getInt("id");
                String periodo = json_obj.getString("periodo");
                String area = json_obj.getString("area");


                int areaConvertida = 0;

                if (area.equals("Ensiso")){
                    areaConvertida = 1;
                }

                if (area.equals("ARQ")){
                    areaConvertida = 2;
                }

                if (area.equals("FC")){
                    areaConvertida = 3;
                }

                disciplinas.add(new Disciplina(nome, codigo, horarios.toString(), id, Integer.parseInt(periodo), areaConvertida));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getPeriodo() == 1) {
                disciplinasPrimeiro.add(disciplinas.get(i));

                RecyclerView primeiroP = (RecyclerView) findViewById(R.id.primeiroP);
                primeiroP.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                primeiroP.setLayoutManager(llm);

                rv1 = new
                        GradeActivityAdapter(disciplinasPrimeiro, this);

                primeiroP.setAdapter(rv1);
            } else if (disciplinas.get(i).getPeriodo() == 2) {
                disciplinasSegundo.add(disciplinas.get(i));

                RecyclerView segundoP = (RecyclerView) findViewById(R.id.segundoP);
                segundoP.setHasFixedSize(true);
                LinearLayoutManager llm2 = new LinearLayoutManager(this);
                llm2.setOrientation(LinearLayoutManager.VERTICAL);
                segundoP.setLayoutManager(llm2);

                rv2 = new
                        GradeActivityAdapter(disciplinasSegundo, this);

                segundoP.setAdapter(rv2);
            } else if (disciplinas.get(i).getPeriodo() == 3) {
                disciplinasTerceiro.add(disciplinas.get(i));

                RecyclerView terceiroP = (RecyclerView) findViewById(R.id.terceiroP);
                terceiroP.setHasFixedSize(true);
                LinearLayoutManager llm3 = new LinearLayoutManager(this);
                llm3.setOrientation(LinearLayoutManager.VERTICAL);
                terceiroP.setLayoutManager(llm3);

                rv3 = new
                        GradeActivityAdapter(disciplinasTerceiro, this);

                terceiroP.setAdapter(rv3);
            } else if (disciplinas.get(i).getPeriodo() == 4) {
                disciplinasQuarto.add(disciplinas.get(i));

                RecyclerView quartoP = (RecyclerView) findViewById(R.id.quartoP);
                quartoP.setHasFixedSize(true);
                LinearLayoutManager llm4 = new LinearLayoutManager(this);
                llm4.setOrientation(LinearLayoutManager.VERTICAL);
                quartoP.setLayoutManager(llm4);

                rv4 = new
                        GradeActivityAdapter(disciplinasQuarto, this);

                quartoP.setAdapter(rv4);
            } else if (disciplinas.get(i).getPeriodo() == 5) {
                disciplinasQuinto.add(disciplinas.get(i));

                RecyclerView quintoP = (RecyclerView) findViewById(R.id.quintoP);
                quintoP.setHasFixedSize(true);
                LinearLayoutManager llm5 = new LinearLayoutManager(this);
                llm5.setOrientation(LinearLayoutManager.VERTICAL);
                quintoP.setLayoutManager(llm5);

                rv5 = new
                        GradeActivityAdapter(disciplinasQuinto, this);

                quintoP.setAdapter(rv5);
            } else if (disciplinas.get(i).getPeriodo() == 6) {
                disciplinasSexto.add(disciplinas.get(i));

                RecyclerView sextoP = (RecyclerView) findViewById(R.id.sextoP);
                sextoP.setHasFixedSize(true);
                LinearLayoutManager llm6 = new LinearLayoutManager(this);
                llm6.setOrientation(LinearLayoutManager.VERTICAL);
                sextoP.setLayoutManager(llm6);

                rv6 = new
                        GradeActivityAdapter(disciplinasSexto, this);
                sextoP.setAdapter(rv6);
            } else if (disciplinas.get(i).getPeriodo() == 7) {
                disciplinasSetimo.add(disciplinas.get(i));
                RecyclerView setimoP = (RecyclerView) findViewById(R.id.setimoP);
                setimoP.setHasFixedSize(true);
                LinearLayoutManager llm7 = new LinearLayoutManager(this);
                llm7.setOrientation(LinearLayoutManager.VERTICAL);
                setimoP.setLayoutManager(llm7);

                rv7 = new
                        GradeActivityAdapter(disciplinasSetimo, this);

                setimoP.setAdapter(rv7);
            } else if (disciplinas.get(i).getPeriodo() == 8) {
                disciplinasOitavo.add(disciplinas.get(i));
                RecyclerView oitavoP = (RecyclerView) findViewById(R.id.oitavoP);
                oitavoP.setHasFixedSize(true);
                LinearLayoutManager llm8 = new LinearLayoutManager(this);
                llm8.setOrientation(LinearLayoutManager.VERTICAL);
                oitavoP.setLayoutManager(llm8);

                rv8 = new
                        GradeActivityAdapter(disciplinasOitavo, this);

                oitavoP.setAdapter(rv8);
            } else if (disciplinas.get(i).getPeriodo() == 9) {
                disciplinasNono.add(disciplinas.get(i));
                RecyclerView nonoP = (RecyclerView) findViewById(R.id.nonoP);
                nonoP.setHasFixedSize(true);
                LinearLayoutManager llm9 = new LinearLayoutManager(this);
                llm9.setOrientation(LinearLayoutManager.VERTICAL);
                nonoP.setLayoutManager(llm9);

                rv9 = new
                        GradeActivityAdapter(disciplinasNono, this);

                nonoP.setAdapter(rv9);
            }
        }
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
        getMenuInflater().inflate(R.menu.grade, menu);
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
            Intent intentHome = new Intent(GradeActivity.this, HomeActivity.class);
            startActivity(intentHome);
        } else if (id == R.id.nav_feedback) {
            Intent intentFeedback = new Intent(GradeActivity.this, FeedbackActivity.class);
            startActivity(intentFeedback);
        } else if (id == R.id.nav_sugestion) {

        } else if (id == R.id.nav_discipline) {

        }else if(id == R.id.nav_modificacao){
            Intent intentModificacao = new Intent(GradeActivity.this, Cadastro2Activity.class);
            startActivity(intentModificacao);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

