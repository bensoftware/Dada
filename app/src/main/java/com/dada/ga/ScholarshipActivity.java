package com.dada.ga;

import android.content.Context;
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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dada.ga.adapters.RVScolarshipAdapter;
import com.dada.ga.models.Scholarship;
import com.dada.ga.utils.DadaUtils;

import java.util.ArrayList;
import java.util.List;

public class ScholarshipActivity extends AppCompatActivity {


    private EditText inputSearch;
    private RecyclerView rv_scolarship;
    private Scholarship bourse;
    private List<Scholarship> listScolarship;
    private Toolbar toolbar;
    private String   title;
    private ImageView  addScolarShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__scolarship);

        addScolarShip=(ImageView)findViewById(R.id.submitAddScolarship);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        addScolarShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScholarshipInscription();
            }
        });
        InitialiseDataBourses();
        InitialiseAllAboutToolbar();
        InitRecycleScolarship();//*1
        InitAdapter();
        Search();
    }



     private void  ScholarshipInscription(){
         Intent intent=new Intent(getApplicationContext(),AddScholarshipStep1Activity.class);
         startActivity(intent);
     }

    private void InitialiseAllAboutToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = getIntent().getExtras().getString("category","Vide");
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("category", title);
                startActivity(intent);
            }
        });
    }

private void InitRecycleScolarship(){
    rv_scolarship=(RecyclerView)findViewById(R.id.rv_scolarship);
    LinearLayoutManager llm=new LinearLayoutManager(this);
    rv_scolarship.setLayoutManager(llm);
    rv_scolarship.hasFixedSize();
}



    private void Search(){
        inputSearch=(EditText)findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                String query=s.toString().toLowerCase();
                List<Scholarship> Scolarships=new ArrayList<Scholarship>();

                for(int i=0;i<listScolarship.size();i++){
                    if (isAddMatch(listScolarship.get(i),query)) {
                        Scolarships.add(listScolarship.get(i));
                    }
                }

                rv_scolarship.setLayoutManager(new LinearLayoutManager(ScholarshipActivity.this));
                RVScolarshipAdapter adapter=new RVScolarshipAdapter(Scolarships);
                rv_scolarship.setAdapter(adapter);


                adapter.notifyDataSetChanged();
                Log.d("Screen", "size" + Scolarships.size());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Boolean isAddMatch(Scholarship scolarship,String query){
        String text=scolarship.getLibelleScholarship().toLowerCase()+" "+scolarship.getDescriptionScholarship().toLowerCase()+" "+scolarship.getPhneScholarship()+" "
                +scolarship.getLibelleScholarship().toLowerCase()+" "+scolarship.getEmailScholarship().toLowerCase();
        return text.contains(query.toLowerCase());
    }


    private void InitialiseDataBourses() {

        new DadaUtils(getApplicationContext());
        listScolarship=new ArrayList<>();
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_1),"ssss","ssss","ssss","ssss",4555,"www.amzon.fr"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_2),"ssss","ssss","ssss","ssss",8535,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_3),"ssss","ssss","ssss","ssss",5535,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_4),"ssss","ssss","ssss","ssss",25368,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_5), "ssss", "ssss", "ssss", "ssss",2868,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_6), "ssss", "ssss", "ssss", "ssss",5383,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole), "ssss", "ssss", "ssss", "ssss", 5758,"www.ziko.com"));
        listScolarship.add(new Scholarship(DadaUtils.base64Encode(R.drawable.ecole_2), "ssss", "ssss", "ssss", "ssss",585868,"www.ziko.com"));
       // return listScolarship;
    }

    private void InitAdapter(){
        RVScolarshipAdapter adapterScolrship=new RVScolarshipAdapter(listScolarship);
        rv_scolarship.setAdapter(adapterScolrship);
    }

}
