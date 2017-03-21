package sisa.ufrpe.br.sisaandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Leonardo on 22/11/2016.
 */

public class GradeViewHolder extends RecyclerView.ViewHolder {

    protected TextView vTitulo, vNome, vDescricao, vCodigo, vHorario, vProfessor;
    protected ImageView vCorTrilha;
    protected CardView vCardMateria;

    public GradeViewHolder(final View itemView) {
        super(itemView);

        vCardMateria = (CardView) itemView.findViewById(R.id.card_materia);
        vTitulo = (TextView) itemView.findViewById(R.id.titulo_disciplina);
        vNome = (TextView) itemView.findViewById(R.id.titulo);
        vDescricao = (TextView) itemView.findViewById(R.id.text_descricao);
        vCodigo = (TextView) itemView.findViewById(R.id.tag_codigo);
        vCorTrilha = (ImageView) itemView.findViewById(R.id.corTrilha);
        vHorario = (TextView) itemView.findViewById(R.id.text_dias_disciplina);
        vProfessor = (TextView) itemView.findViewById(R.id.text_nomeProfessor_disciplina);
    }
}
