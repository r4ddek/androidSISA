package sisa.ufrpe.br.sisaandroid;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leonardo on 22/11/2016.
 */

public class GradeActivityAdapter extends RecyclerView.Adapter<GradeViewHolder> {
    List<Disciplina> disciplinaPeriodo;
    Context context;
    Disciplina disciplina;
    AlertDialog alerta;
    Resources resources;
    TypedArray cores;

    public GradeActivityAdapter (List<Disciplina> disciplinaPeriodo, Context context){
        this.disciplinaPeriodo = disciplinaPeriodo;
        this.context = context;
    }

    @Override
    public GradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.custom_layout_grade_min, parent, false);
        return new GradeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GradeViewHolder holder, final int position) {
        disciplina = disciplinaPeriodo.get(position);

        resources = context.getResources();
        cores = resources.obtainTypedArray(R.array.areas);

        holder.vTitulo.setText(disciplina.getNome());
        holder.vCardMateria.setBackgroundResource(cores.getResourceId(disciplina.getArea(), 4));

        holder.vTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disciplina = disciplinaPeriodo.get(position);
                Log.d( "vsf", "foda-se55");
                dialogTrigger(cores, disciplina.getCodigo(), disciplina.getNome(), disciplina.getHorarios(), disciplina.getPeriodo());
            }
        });

    }

    @Override
    public int getItemCount() {
        return disciplinaPeriodo.size();
    }

    private void dialogTrigger(TypedArray cores, final String cod, String titulo, String dias, int periodo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater li = LayoutInflater.from(context);

        View view = li.inflate(R.layout.custom_layout_grade_exp, null);

        builder.setView(view);

        ImageView corTrilha = (ImageView) view.findViewById(R.id.corTrilha);
        TextView txtCod = (TextView) view.findViewById(R.id.tag_codigo);
        TextView txtTitulo = (TextView) view.findViewById(R.id.titulo);
        TextView txtDias = (TextView) view.findViewById(R.id.text_dias_disciplina);
        TextView txtDescricao = (TextView) view.findViewById(R.id.text_nomeProfessor_disciplina);

        corTrilha.setBackgroundResource(cores.getResourceId(disciplina.getArea(), 4));
        txtCod.setText(cod);
        if (cod.equals("")){
            String codNovo = "Sem Código";
            txtCod.setText(codNovo);
        }

        txtTitulo.setText(titulo);
        txtDias.setText(dias);
        if (dias.equals("")){
            String diaNovo = "Sem horários para exibir";
            txtCod.setText(diaNovo);
        }
        txtDescricao.setText(String.valueOf(periodo));

        alerta = builder.create();
        alerta.show();
    }
}
