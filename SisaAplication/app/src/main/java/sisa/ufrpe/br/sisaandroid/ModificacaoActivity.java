package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModificacaoActivity extends AppCompatActivity implements ResultadoAsync{

    @BindView(R.id.btConfirmarCadastro2) Button btnSubmit;

    @BindView(R.id.btVoltarCadastro2) Button btnBack;

    @BindView(R.id.spinnerQtdTrancado) Spinner qtdTrancado;

    @BindView(R.id.txtSeekBar) TextView txtSeekBar;

    @BindView(R.id.seekBarHoras) SeekBar seekBar;

    ArrayList<Disciplina> disciplinas;
    ArrayList<Integer> listaAprovada, listaReprovada, listaAcompanhada;
    RecyclerViewCadastro2Adapter rv;
    int progress = 0;
    String listaDisciplinas;
    String result;
    JSONObject resultJSON;
    SharedPreferences user;
    String id;

    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacao);

        user = PreferenceManager.getDefaultSharedPreferences(this);

        ButterKnife.bind(this);
        result = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtSeekBar.setText("Horas: " + seekBar.getProgress() + "/" + seekBar.getMax() + "+");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtSeekBar.setText("Horas: " + progress + "/" + seekBar.getMax()+"+");
            }
        });

        try {
            disciplinas = new ArrayList<>();
            listaDisciplinas = new GetAsyncTask("http://09250e43.ngrok.io/disciplinas").execute().get();
            Log.d("Lista:", listaDisciplinas);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jArray= null;
            jArray = new JSONArray(listaDisciplinas);
            for(int i=0;i<(jArray.length());i++)
            {
                JSONObject json_obj=jArray.getJSONObject(i);
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

        RecyclerView disciplinasCadastro = (RecyclerView) findViewById(R.id.recyclerView_disciplinas_cadastro2);
        disciplinasCadastro.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        disciplinasCadastro.setLayoutManager(llm);
        rv = new RecyclerViewCadastro2Adapter(disciplinas, this);
        disciplinasCadastro.setAdapter(rv);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(ModificacaoActivity.this, HomeActivity.class);
                startActivity(intentVoltar);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaAprovada = rv.listaAprovadas;
                listaReprovada = rv.listaReprovadas;
                listaAcompanhada = rv.listaAcompanhadas;

                JSONObject jsonParam = new JSONObject();
                JSONArray pagas = new JSONArray();
                JSONArray reprovadas = new JSONArray();
                JSONArray acompanhadas = new JSONArray();

                for (int i = 0; i < listaAprovada.size(); i++) {
                    JSONObject discip = new JSONObject();
                    try {
                        discip.put("id", listaAprovada.get(i));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    pagas.put(discip);
                }

                for (int i = 0; i < listaReprovada.size(); i++) {
                    JSONObject discip = new JSONObject();
                    try {
                        discip.put("id", listaReprovada.get(i));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    reprovadas.put(discip);
                }

                for (int i = 0; i < listaAcompanhada.size(); i++) {
                    JSONObject discip = new JSONObject();
                    try {
                        discip.put("id", listaAcompanhada.get(i));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    acompanhadas.put(discip);
                }
                id = user.getString("id", "id");
                try {
                    jsonParam.put("id", Integer.parseInt(id));
                    jsonParam.put("disciplinasPagas", pagas);
                    jsonParam.put("disciplinasReprovadas", reprovadas);
                    jsonParam.put("disciplinasAcompanhadas", acompanhadas);

                    Log.d("JSON FORMADO: ", jsonParam.toString());

                    cadastrar(jsonParam);

                }catch (JSONException j) {
                    j.printStackTrace();
                }
            }
        });
    }

    @Override
    public void processFinish(String output) {
        Toast.makeText(this, "Modificações salvas!", Toast.LENGTH_LONG);
        Intent intentHome = new Intent(ModificacaoActivity.this, HomeActivity.class);
        startActivity(intentHome);
        this.finish();
    }

    public void cadastrar(JSONObject json){
        PutAsyncTask conexao = new PutAsyncTask("http://09250e43.ngrok.io/alunos", json);
        conexao.delegate = this;
        conexao.execute();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}


