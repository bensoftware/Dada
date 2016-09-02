package com.dada.ga;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dada.ga.adapters.RVSFormationAdapter;
import com.dada.ga.listeners.RecyclerItemClickListener;
import com.dada.ga.models.Formation;
import com.dada.ga.models.SchoolItem;
import com.dada.ga.utils.DadaUtils;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * A login screen that offers login via email/password.
 */
public class AddSchoolStep2Activity extends AppCompatActivity {

    private static final String TAG = "Inscription1";
    SchoolItem schoolItem;
    private Toolbar toolbar;
    private List<Formation> formations = new ArrayList<>();
    private RecyclerView rv;
    private RVSFormationAdapter adapter;
    private EditText txtLibelleFormation;
    private EditText txtFraisFormation;
    private ImageView imgviewAdd;
    private Button ButtonNext;
    private MaterialDialog mMaterialDialog;
    private int categoryorder;
    private String categoryname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryname = getIntent().getExtras().getString(DadaUtils.FIELD_CATEGORY);
        categoryorder = getIntent().getExtras().getInt(DadaUtils.FIELD_CATEGORY_ORDER);


        setContentView(R.layout.activity_inscription_activitystep2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Step1");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        schoolItem = getIntent().getExtras().getParcelable(DadaUtils.FIELD_SCHOOL);

        ButtonNext = (Button) findViewById(R.id.next2button);
        txtFraisFormation = (EditText) findViewById(R.id.prixFormation_);
        txtLibelleFormation = (EditText) findViewById(R.id.libelleFormation_);
        imgviewAdd = (ImageView) findViewById(R.id.plus);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Step2");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RechargeRV();

    }

    /*cette methode recharge la recycle  et lui associer un ecouteur d'évenements*/

    private void RechargeRV() {
        rv = (RecyclerView) findViewById(R.id.rve);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        adapter = new RVSFormationAdapter(formations, getApplicationContext());
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //sending object wich user cliked to Detail DetailSchoolActivity
                        show(view, position);
                    }
                })
        );
        addFormation();
        next();
    }

/*This methode receive the view and his position after confirm whith dialog the view is remove to the List.*/

    public void show(View v, final int positionSup) {
        mMaterialDialog = new MaterialDialog(this);
        if (mMaterialDialog != null) {
            mMaterialDialog.setTitle("Faite votre choix")
                    .setMessage("Voulez vous le supprimer?")
                            //mMaterialDialog.setBackgroundResource(R.drawable.background);
                    .setPositiveButton("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeFormation(positionSup);
                            mMaterialDialog.dismiss();
                            Toast.makeText(AddSchoolStep2Activity.this, "Ok", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("ANNULER",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    Toast.makeText(AddSchoolStep2Activity.this, "Annuler", Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                    .setCanceledOnTouchOutside(true)
                            // You can change the message anytime.
                            // mMaterialDialog.setTitle("提示");
                    .setOnDismissListener(
                            new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    Toast.makeText(AddSchoolStep2Activity.this, "onDismiss", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .show();
            // You can change the message anytime.

        }
    }

    /*This methode  allow to insert data into List when users click imageview imgviewAdd */

    private void addFormation() {

        imgviewAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Formation f = new Formation();
                if (!txtLibelleFormation.getText().toString().trim().isEmpty()) {
                    if (!txtFraisFormation.getText().toString().trim().isEmpty()) {
                        f.setLibelle(txtLibelleFormation.getText().toString());
                        f.setPrix(Double.valueOf(txtFraisFormation.getText().toString()));
                        formations.add(f);
                    } else
                        txtFraisFormation.setError(getResources().getString(R.string.error_frais_formation));
                } else {
                    txtLibelleFormation.setError(getResources().getString(R.string.error_libelle_formation));
                }
                rv.setLayoutManager(new LinearLayoutManager(AddSchoolStep2Activity.this));
                adapter = new RVSFormationAdapter(formations, getApplicationContext());
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                InitEditext();
            }
        });
    }

    public void updateBeanSchool() {
        schoolItem.setFormations(formations);
    }


    /*this methode return void and is called when users click on button   ButtonNext  for to start activity*/
    private void next() {
        ButtonNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formations.isEmpty()) {
                    if (validate()) {
                        Dialog();
                        updateBeanSchool();
                        Toast.makeText(getApplicationContext(), schoolItem.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), AddSchoolStep3Activity.class);
                        intent.putExtra(DadaUtils.FIELD_SCHOOL, (Parcelable) schoolItem);
                        intent.putExtra(DadaUtils.FIELD_CATEGORY, categoryname);
                        intent.putExtra(DadaUtils.FIELD_CATEGORY_ORDER, categoryorder);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(AddSchoolStep2Activity.this, getResources().getString(R.string.info_affiche_formation_vide), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*cette methode initialise les champs Editext*/

    private void InitEditext() {
        txtLibelleFormation.setText("");
        txtFraisFormation.setText("");
    }

    /*cette methode reçois la position d'un objet dans la liste des formation  supprime  ce dernier*/

    private void removeFormation(int i) {
        formations.remove(i);
        rv.setLayoutManager(new LinearLayoutManager(AddSchoolStep2Activity.this));
        adapter = new RVSFormationAdapter(formations, getApplicationContext());
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*this method  is called for to activate the button */

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Echec", Toast.LENGTH_LONG).show();
        ButtonNext.setEnabled(true);
    }

    /*This methode display the dialog after users clisk  ButtonNext */

    public void Dialog() {
        Log.d(TAG, "Login");
        if (!validate()) {
            return;
        }
        ButtonNext.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(AddSchoolStep2Activity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        // TODO: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onLoginSuccess() {
        ButtonNext.setEnabled(true);
        //finish();
    }


    /* Cette methode returne vrai si tous les champs sont remplis et faux au cas contraire */
    public boolean validate() {
        return formations.size() > 0;
    }
}

