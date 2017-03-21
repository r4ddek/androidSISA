package sisa.ufrpe.br.sisaandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Paulo on 14/02/2017.
 */

public class SugestaoAdapter  extends RecyclerView.Adapter<SugestaoViewHolder> {
    private List<SugestaoCard> listaSugestao;

    public SugestaoCard sugestao;
    Context context;
    AlertDialog alerta;
    String teste;
    ArrayList<Integer> arrayId;
    public SugestaoAdapter(List<SugestaoCard> listaSugestao, Context context){
        this.listaSugestao = listaSugestao;
        this.context = context;

    }

    @Override
    public SugestaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        arrayId = new ArrayList<>();
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_card_sugestao, parent, false);



        return new SugestaoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SugestaoViewHolder holder, final int position) {

        try {
            teste = new GetAsyncTask("http://09250e43.ngrok.io/sugestao/19").execute().get();
            Log.d("testando:", teste);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        JSONArray jArray= null;
        try {
            jArray = new JSONArray(teste);
            for(int i=0;i<(jArray.length());i++)
            {
                JSONObject json_obj=jArray.getJSONObject(i);
                int idSugestao=json_obj.getInt("id");
                /*String value2=json_obj.getString("username");
                Log.d("usuario", value2);*/
                arrayId.add(idSugestao);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sugestao = listaSugestao.get(position);



        holder.vTitulo.setText(sugestao.getTitulo());
        holder.vDescricao.setText(sugestao.getDescricao());




        holder.vDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugestao = listaSugestao.get(position);
                Log.d( "vsf", "foda-se2");
                Intent intentDetalhe = new Intent(context,DetalheSugestaoActivity.class);

                if(position==0){
                    Log.d("posicao:", "testando posicao 0");
                    intentDetalhe.putExtra("id",0);

                }else if(position==1){
                    Log.d("posicao:", "testando posicao 1");
                    intentDetalhe.putExtra("id",1);

                }else if(position==2){
                    Log.d("posicao:", "testando posicao 2");
                    intentDetalhe.putExtra("id",2);

                }
                context.startActivity(intentDetalhe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSugestao.size();
    }
}
