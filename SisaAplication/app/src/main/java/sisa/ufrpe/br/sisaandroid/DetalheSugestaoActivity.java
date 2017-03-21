package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetalheSugestaoActivity extends AppCompatActivity {
    String teste;
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
    ArrayList<Disciplina> disciplinasDez;
    ArrayList<Disciplina> disciplinasOnze;
    ArrayList<Disciplina> disciplinasDoze;
    GradeActivityAdapter rv1;
    GradeActivityAdapter rv2;
    GradeActivityAdapter rv3;
    GradeActivityAdapter rv4;
    GradeActivityAdapter rv5;
    GradeActivityAdapter rv6;
    GradeActivityAdapter rv7;
    GradeActivityAdapter rv8;
    GradeActivityAdapter rv9;
    GradeActivityAdapter rv10;
    GradeActivityAdapter rv11;
    GradeActivityAdapter rv12;
    int i;
    JSONArray array_temp;
    JSONObject sugestao;
    JSONObject periodoSugestao;

    String gradeConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_sugestao);

        int aux;
        arrayDisciplina = new ArrayList<>();
        disciplinas = new ArrayList<>();
        disciplinasPrimeiro = new ArrayList<>();
        disciplinasSegundo = new ArrayList<>();
        disciplinasTerceiro = new ArrayList<>();
        disciplinasQuarto = new ArrayList<>();
        disciplinasQuinto = new ArrayList<>();
        disciplinasSexto = new ArrayList<>();
        disciplinasSetimo = new ArrayList<>();
        disciplinasOitavo = new ArrayList<>();
        disciplinasNono = new ArrayList<>();
        disciplinasDez = new ArrayList<>();
        disciplinasOnze = new ArrayList<>();
        disciplinasDoze = new ArrayList<>();

        Intent intentSugestao = getIntent();
        aux = (int) intentSugestao.getSerializableExtra("id");
        arrayDisciplina = (ArrayList<Integer>) intentSugestao.getSerializableExtra("array");

        try {
            gradeConn = new GetAsyncTask("http://10.0.2.2:8080/sugestao/8").execute().get();
            Log.d("testando:", gradeConn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "id:" + aux, Toast.LENGTH_SHORT).show();

        try {
            array_temp = new JSONArray(gradeConn);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            sugestao = array_temp.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            periodoSugestao = sugestao.getJSONObject("periodos");
        } catch (JSONException e) {
            e.printStackTrace();
        }



//        JSONArray jArray = null;
//        JSONArray jsonPeriodo = null;
//        JSONArray jsonDisciplina = null;
//        int periodoSugestao = 0;
//        String nomeDisciplina = "";
//        try {
//            try {
//                jArray = new JSONArray(gradeConn);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//            JSONObject json_obj = null;
//            try {
//                json_obj = jArray.getJSONObject(aux);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                int value1 = json_obj.getInt("id");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//            try {
//                jsonPeriodo = new JSONArray("periodos");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            for (int y = 0; y < (jsonPeriodo.length()); y++) {
//                JSONObject json_obj_periodo = null;
//                try {
//                    json_obj_periodo = jsonPeriodo.getJSONObject(y);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    periodoSugestao = json_obj_periodo.getInt("periodo");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    jsonDisciplina = new JSONArray("disciplinasDoPeriodoDaSugestao");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                for (int x = 0; x < (jsonDisciplina.length()); x++) {
//                    JSONObject json_obj_disciplina = null;
//                    try {
//                        json_obj_disciplina = jsonDisciplina.getJSONObject(x);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        nomeDisciplina = json_obj_disciplina.getString("nome");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }


//                String value2=json_obj.getString("username");
//               // Log.d("usuario", value2);
//                disciplinas.add(new Disciplina(nomeDisciplina,"listaDisciplinas","testando",1,periodoSugestao));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

      /*  disciplinas.add(new

                Disciplina("Desenvolvimento de Aplicações Móveis","A  disciplina tem por objetivo apresentar" +
                " os principais conceitos relativos ao desenvolvimento de software voltado para " +
                "dispositivos móveis, desde os requisitos e desafios desse tipo de software, " +
                "passando pela sua arquitetura e mecanismos de comunicação até uma discussão sobre " +
                "plataformas de desenvolvimento.\"","LC1 - 141197",2,1)

        );
        disciplinas.add(new

                Disciplina("Projeto de Desenvolvimento de Software","listaDisciplinas","BCC1 - 150023",1,2)

        );
        disciplinas.add(new

                Disciplina("Algoritmos e Estrutura de Dados","listaDisciplinas","BCC1 - 155520",3,3)

        );
        disciplinas.add(new

                Disciplina("Arquitetura de Computadores","listaDisciplinas","BCC1 - 148970",2,4)

        );
        disciplinas.add(new

                Disciplina("Interfaces Homem-Máquina","listaDisciplinas","LC1 - 156698",1,5)

        );*/

            for (i = 0; i < disciplinas.size(); i++) {
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
                } else if (disciplinas.get(i).getPeriodo() == 10) {
                    disciplinasDez.add(disciplinas.get(i));

                    RecyclerView dezP = (RecyclerView) findViewById(R.id.decimoP);
                    dezP.setHasFixedSize(true);
                    LinearLayoutManager llm10 = new LinearLayoutManager(this);
                    llm10.setOrientation(LinearLayoutManager.VERTICAL);
                    dezP.setLayoutManager(llm10);

                    rv10 = new
                            GradeActivityAdapter(disciplinasDez, this);

                    dezP.setAdapter(rv10);
                } else if (disciplinas.get(i).getPeriodo() == 11) {
                    disciplinasOnze.add(disciplinas.get(i));

                    RecyclerView onzeP = (RecyclerView) findViewById(R.id.onzeP);
                    onzeP.setHasFixedSize(true);
                    LinearLayoutManager llm11 = new LinearLayoutManager(this);
                    llm11.setOrientation(LinearLayoutManager.VERTICAL);
                    onzeP.setLayoutManager(llm11);

                    rv11 = new
                            GradeActivityAdapter(disciplinasOnze, this);

                    onzeP.setAdapter(rv11);
                } else if (disciplinas.get(i).getPeriodo() == 12) {
                    disciplinasDoze.add(disciplinas.get(i));

                    RecyclerView dozeP = (RecyclerView) findViewById(R.id.dozeP);
                    dozeP.setHasFixedSize(true);
                    LinearLayoutManager llm12 = new LinearLayoutManager(this);
                    llm12.setOrientation(LinearLayoutManager.VERTICAL);
                    dozeP.setLayoutManager(llm12);

                    rv12 = new
                            GradeActivityAdapter(disciplinasDoze, this);

                    dozeP.setAdapter(rv12);
                }
            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
