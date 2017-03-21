package sisa.ufrpe.br.sisaandroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Paulo on 14/02/2017.
 */

public class SugestaoViewHolder extends RecyclerView.ViewHolder {

    protected TextView vTitulo;
    protected TextView vDescricao;


    public SugestaoViewHolder(View v){
        super(v);
        vTitulo = (TextView) v.findViewById(R.id.titulo);
        vDescricao = (TextView) v.findViewById(R.id.descricao);

    }
}