package sisa.ufrpe.br.sisaandroid;

import android.animation.ValueAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Leonardo on 23/10/2016.
 */

public class Cadastro2ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

    protected TextView vNome, vDescricao, vCodigo;
    protected CheckBox vPassou, vReprovou;
    protected Spinner vQtReprovações;
    protected CardView vCardView;
    protected ImageView vCorTrilha;

    public Cadastro2ViewHolder(final View itemView) {
        super(itemView);

        vCardView = (CardView) itemView.findViewById(R.id.card_disciplinas);

        vNome = (TextView) itemView.findViewById(R.id.titulo);
        vDescricao = (TextView) itemView.findViewById(R.id.text_descricao);
        vCodigo = (TextView) itemView.findViewById(R.id.tag_codigo);
        vPassou = (CheckBox) itemView.findViewById(R.id.checkBoxAprovado);
        vReprovou = (CheckBox) itemView.findViewById(R.id.checkBoxReprovado);
        vQtReprovações = (Spinner) itemView.findViewById(R.id.numReprovacoes);
        vCorTrilha = (ImageView) itemView.findViewById(R.id.corTrilha);
    }

//    @Override
//    public void onClick(final View v) {
//
//        if (!isExpanded) {
//            vRelativeLayout.setVisibility(View.VISIBLE);
//            vRelativeLayout.setEnabled(true);
//            isExpanded = true;
//        } else {
//            isExpanded = false;
//
//            }
//        }
    }

