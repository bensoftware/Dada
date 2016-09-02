package com.dada.ga;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dada.ga.adapters.CardItem;
import com.dada.ga.adapters.GridViewAdapter;
import com.dada.ga.models.EnumCategory;
import com.dada.ga.models.User;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import static com.dada.ga.utils.DadaUtils.FIELD_CATEGORY;
import static com.dada.ga.utils.DadaUtils.FIELD_CATEGORY_ORDER;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG ="MainActivity" ;
    private Toolbar toolbar;
    private GridView gridview;
    private GridViewAdapter gridviewAdapter;
    private ArrayList<CardItem> data = new ArrayList<CardItem>();
    private Boolean _testPublicateur;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //for to get token for google FCM
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New Token: " + refreshedToken);
        ///
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dada");


        setSupportActionBar(toolbar);
        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapter(); // Set the Data Adapter
    }


    // Initialize the GUI Components
    private void initView() {
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Insert The Data
    private void fillData() {
        data.add(new CardItem(EnumCategory.PRIMAIRE.getLibelle(), getResources().getDrawable(EnumCategory.PRIMAIRE.getResource()), EnumCategory.PRIMAIRE.getOrder()));
        data.add(new CardItem(EnumCategory.SECONDAIRE.getLibelle(), getResources().getDrawable(EnumCategory.SECONDAIRE.getResource()), EnumCategory.SECONDAIRE.getOrder()));
        data.add(new CardItem(EnumCategory.PREPRIMAIRE.getLibelle(), getResources().getDrawable(EnumCategory.PREPRIMAIRE.getResource()), EnumCategory.PREPRIMAIRE.getOrder()));
        data.add(new CardItem(EnumCategory.UNIVERSITE.getLibelle(), getResources().getDrawable(EnumCategory.UNIVERSITE.getResource()), EnumCategory.UNIVERSITE.getOrder()));
        data.add(new CardItem(EnumCategory.EXAMENCONCOUR.getLibelle(), getResources().getDrawable(EnumCategory.EXAMENCONCOUR.getResource()), EnumCategory.EXAMENCONCOUR.getOrder()));
        data.add(new CardItem(EnumCategory.BOURSESTECHNIQUES.getLibelle(), getResources().getDrawable(EnumCategory.BOURSESTECHNIQUES.getResource()), EnumCategory.BOURSESTECHNIQUES.getOrder()));
        data.add(new CardItem(EnumCategory.LYCEE.getLibelle(), getResources().getDrawable(EnumCategory.LYCEE.getResource()), EnumCategory.LYCEE.getOrder()));
        data.add(new CardItem(EnumCategory.BOURSESSUPERIEURS.getLibelle(), getResources().getDrawable(EnumCategory.BOURSESSUPERIEURS.getResource()), EnumCategory.BOURSESSUPERIEURS.getOrder()));
        data.add(new CardItem(EnumCategory.MOTEUR_RECHERCHE.getLibelle(), getResources().getDrawable(EnumCategory.MOTEUR_RECHERCHE.getResource()), EnumCategory.MOTEUR_RECHERCHE.getOrder()));
    }

    // Set the Data Adapter
    private void setDataAdapter() {
        gridviewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.card_item, data);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id) {
        String category = data.get(position).getTitle();
        int categoryorder = data.get(position).getOrder();
        if (categoryorder == EnumCategory.BOURSESSUPERIEURS.getOrder()) {
            Intent intent = new Intent(MainActivity.this, ScholarshipActivity.class);
            intent.putExtra(FIELD_CATEGORY, category);
            MainActivity.this.startActivity(intent);
        } else if (categoryorder == EnumCategory.EXAMENCONCOUR.getOrder()) {
            Intent intent = new Intent(MainActivity.this, CompetitionActivity.class);
            intent.putExtra(FIELD_CATEGORY, category);
            MainActivity.this.startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, SchoolsActivity.class);
            intent.putExtra(FIELD_CATEGORY, category);
            intent.putExtra(FIELD_CATEGORY_ORDER, categoryorder);
            MainActivity.this.startActivity(intent);
        }

    }

}
