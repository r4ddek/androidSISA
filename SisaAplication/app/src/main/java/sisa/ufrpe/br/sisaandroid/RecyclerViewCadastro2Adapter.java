package sisa.ufrpe.br.sisaandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.util.logging.Logger.global;
import static sisa.ufrpe.br.sisaandroid.R.id.parent;
import static sisa.ufrpe.br.sisaandroid.R.id.recyclerView_disciplinas_cadastro2;

/**
 * Created by Leonardo on 23/10/2016.
*/

public class RecyclerViewCadastro2Adapter extends RecyclerView.Adapter<Cadastro2ViewHolder>{

    private List<Disciplina> listaDisciplinas;

    public ArrayList<Integer> listaAprovadas = new ArrayList<Integer>();
    public ArrayList<Integer> listaReprovadas = new ArrayList<Integer>();
    public ArrayList<Integer> listaAcompanhadas = new ArrayList<Integer>();
    public Disciplina disciplina;
    Context context;
    AlertDialog alerta;
    Resources resources;
    TypedArray cores;



    public RecyclerViewCadastro2Adapter(List<Disciplina> listaDisciplinas, Context context){
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    @Override
    public Cadastro2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.custom_layout_card_view_disciplinas, parent, false);
        return new Cadastro2ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Cadastro2ViewHolder holder, final int position) {
        disciplina = listaDisciplinas.get(position);
        resources = context.getResources();
        cores = resources.obtainTypedArray(R.array.areas);

        holder.vCodigo.setText(disciplina.getCodigo());
        holder.vNome.setText(disciplina.getNome());
        holder.vCorTrilha.setBackgroundResource(cores.getResourceId(disciplina.getArea(), 4));
        holder.vNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disciplina = listaDisciplinas.get(position);
               dialogTrigger(cores, disciplina.getCodigo(), disciplina.getNome(), position, disciplina.getHorarios(), disciplina.getPeriodo(), disciplina.getId());
            }
        });
    }

    private void dialogTrigger(TypedArray cores, final String cod, String titulo, int position, String horarios, int periodo, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater li = LayoutInflater.from(context);

        View view = li.inflate(R.layout.layout_dialog_cadastro,null);

        builder.setView(view);

        ImageView corTrilha = (ImageView)view.findViewById(R.id.corTrilha);
        TextView txtCod = (TextView)view.findViewById(R.id.tag_codigo);
        TextView txtTitulo = (TextView)view.findViewById(R.id.titulo);
        TextView txtDescricao = (TextView)view.findViewById(R.id.text_descricao);
        final RadioButton radioAprovado = (RadioButton)view.findViewById(R.id.radioAprovado);
        final RadioButton radioRepravado = (RadioButton)view.findViewById(R.id.radioReprovado);
        final CheckBox cAcompanhado = (CheckBox) view.findViewById(R.id.checkBox);
        final Button btnAdicionarDisciplina = (Button)view.findViewById(R.id.btnAdicionarDisciplina);

        corTrilha.setBackgroundResource(cores.getResourceId(disciplina.getArea(), 4));
        txtCod.setText("Código: " + cod);
        txtTitulo.setText(titulo);
        txtDescricao.setText("Horários: " + horarios);

        ColorStateList  colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked} , // checked
                },
                new int[]{
                        Color.parseColor("#cccccc"),
                        Color.parseColor("#cccccc"),
                }
        );

        CompoundButtonCompat.setButtonTintList(cAcompanhado,colorStateList);


        radioRepravado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAcompanhado.setClickable(true);
                ColorStateList  colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{-android.R.attr.state_checked}, // unchecked
                                new int[]{android.R.attr.state_checked} , // checked
                        },
                        new int[]{
                                Color.parseColor("#fb8c00"),
                                Color.parseColor("#fb8c00"),
                        }
                );

                CompoundButtonCompat.setButtonTintList(cAcompanhado,colorStateList);
            }
        });

        radioAprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAcompanhado.setClickable(false);
                cAcompanhado.setChecked(false);

                ColorStateList  colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{-android.R.attr.state_checked}, // unchecked
                                new int[]{android.R.attr.state_checked} , // checked
                        },
                        new int[]{
                                Color.parseColor("#cccccc"),
                                Color.parseColor("#cccccc"),
                        }
                );

                CompoundButtonCompat.setButtonTintList(cAcompanhado,colorStateList);
            }
        });

        if (listaAprovadas.contains(id)){
            radioAprovado.setChecked(true);
        }

        if (listaReprovadas.contains(id)){
            radioRepravado.setChecked(true);
        }

        if (listaAcompanhadas.contains(id)){
            cAcompanhado.setChecked(true);
        }

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        btnAdicionarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioAprovado.isChecked()){
                    listaAprovadas.add(id);
                }

                if(radioRepravado.isChecked()){
                    listaReprovadas.add(id);
                    if (cAcompanhado.isChecked()){
                        listaAcompanhadas.add(id);
                    }
                }
                alerta.dismiss();
            }
        });
        alerta = builder.create();
        alerta.show();
    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}
