package com.dada.ga;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dada.ga.adapters.CustomSwipeAdapter;
import com.dada.ga.models.SchoolItem;

public class SwipeImageActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private CustomSwipeAdapter   adapter;
    private SchoolItem school;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewpager = (ViewPager) findViewById(R.id.view_pager);
        adapter =new CustomSwipeAdapter(this,getAllpictures());
        viewpager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                finish();
            }
        });


    }




    private int[] getAllpictures(){

        int[] pictures={ R.drawable.ecole_1,R.drawable.ecole_2,R.drawable.ecole_3, R.drawable.ecole_4
                ,R.drawable.ecole_5,R.drawable.ecole_6,R.drawable.img7};
        return pictures;
    }


  /*  public  Drawable[] getAllpictures(){
        LinearLayout ll = null;
        Drawable[] pictures=null;
        for (int boucle = 0; boucle < SchoolsManager.pictures.size();boucle++) {
            pictures[boucle]= DadaUtils.base64Decode(SchoolsManager.pictures.get(boucle));
        }
        return pictures;
    }*/

}
