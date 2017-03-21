package sisa.ufrpe.br.sisaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<SugestaoCard> notificacoes;
    SugestaoAdapter cardAdapter;
    public static final String QUALPEGAR = "itens";
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<SugestaoCard, SugestaoViewHolder> mFirebaseAdapter;
    private LinearLayoutManager llm;
    private RecyclerView listNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listNotificacao = (RecyclerView) findViewById(R.id.cardList);
        llm = new LinearLayoutManager(this);
        listNotificacao.setHasFixedSize(true);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        notificacoes = new ArrayList<>();

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<SugestaoCard, SugestaoViewHolder>(
                SugestaoCard.class,
                R.layout.layout_card_sugestao,
                SugestaoViewHolder.class,
                mFirebaseDatabaseReference.child(QUALPEGAR)) {
            @Override
            protected void populateViewHolder(SugestaoViewHolder viewHolder, final SugestaoCard model, int position) {
                viewHolder.vTitulo.setText(model.getTitulo());
                viewHolder.vDescricao.setText(model.getDescricao());
                final String titulo = model.getTitulo();
                final String descricao = model.getDescricao();

            }
            };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int contador = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = llm.findLastCompletelyVisibleItemPosition();
             /*   if(lastVisiblePosition == -1 || (positionStart >= (contador -1)&& lastVisiblePosition == (positionStart-1))){
                    recList.scrollToPosition(positionStart);
                }*/

            }
        });
        listNotificacao.setLayoutManager(llm);
        listNotificacao.setAdapter(mFirebaseAdapter);
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
        getMenuInflater().inflate(R.menu.home, menu);
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
            Intent intentHome = new Intent(FeedbackActivity.this, HomeActivity.class);
            startActivity(intentHome);
        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_sugestion) {

        } else if (id == R.id.nav_discipline) {
            Intent intentGrade = new Intent(FeedbackActivity.this, GradeActivity.class);
            startActivity(intentGrade);
        }else if(id == R.id.nav_modificacao){
            Intent intentModificacao = new Intent(FeedbackActivity.this, Cadastro2Activity.class);
            startActivity(intentModificacao);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
