package com.dada.ga;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dada.ga.adapters.RVCompetition;
import com.dada.ga.listeners.RecyclerItemClickListener;
import com.dada.ga.models.Competition;
import com.dada.*;
import com.dada.ga.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionActivity extends AppCompatActivity {

    private RecyclerView rvc;
    private TextView descriptionC;
    private List<Competition> lesCompetions;
    private Toolbar toolbar;
    private String title;
    private ImageView addCompetition;
    private EditText Search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InitialiseAllAboutToolbar();
        initdata();
        initRecycleAndAdapter();
        Search();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void InitialiseAllAboutToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = getIntent().getExtras().getString("category", "Vide");
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        addCompetition = (ImageView) findViewById(R.id.submitAddScolarship);
        Search = (EditText) findViewById(R.id.search_competition);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("category", title);
                startActivity(intent);
            }
        });
    }

    public void Search() {
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String query = s.toString().toLowerCase();

                List<Competition> compet = new ArrayList<Competition>();

                for (int i = 0; i < lesCompetions.size(); i++) {
                    if (filtreQuery(lesCompetions.get(i), query)) {
                        compet.add(lesCompetions.get(i));
                    }
                }
                //   rv.setLayoutManager(new LinearLayoutManager(SchoolsActivity.this));
                rvc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                RVCompetition adapterc = new RVCompetition(compet);
                rvc.setAdapter(adapterc);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void initRecycleAndAdapter() {
        rvc = (RecyclerView) findViewById(R.id.rv_competition);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RVCompetition adapterc = new RVCompetition(lesCompetions);
        rvc.setLayoutManager(llm);
        rvc.setAdapter(adapterc);
    }

    public void initdata() {
        lesCompetions = new ArrayList<>();
        lesCompetions.add(new Competition("Institut Africain d'Informatique", "Concours enam", R.drawable.ecole_6));
        lesCompetions.add(new Competition("Ambassade des Etat Unies", "Coucours", R.drawable.ecole_6));
        lesCompetions.add(new Competition("Amlbassade de chine", "Coucours", R.drawable.ecole_6));
        lesCompetions.add(new Competition("Union Europeenne", "Coucours", R.drawable.ecole_6));
        lesCompetions.add(new Competition("Republique de Chine", "Coucours", R.drawable.ecole_6));
        lesCompetions.add(new Competition("Republique du Congo", "Coucours", R.drawable.ecole_6));
    }

    private Boolean filtreQuery(Competition c, String query) {
        String text = c.getDescription_competition().toLowerCase() + " " + c.getLibelle_competition().toLowerCase() + "";
        return text.contains(query.toLowerCase());
    }
}
