package com.dada.ga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dada.ga.models.SchoolItem;
import com.dada.ga.utils.DadaUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class AddSchoolStep1Activity extends AppCompatActivity {


    private Toolbar toolbar;
    private static final String TAG = "Inscription1";
    private static final int REQUEST_SIGNUP = 0;
    private String title = "";
    private SchoolItem schoolEcole = new SchoolItem();
    private int categoryorder;
    private String categoryname;

    /* utilisation de la dependence butterknif pour declarer les objects du Xml*/
    @Bind(R.id.email)
    EditText txtEmail;
    @Bind(R.id.telephone)
    EditText txtPhone;
    @Bind(R.id.next1)
    Button ButtonNext;
    @Bind(R.id.description)
    TextView txtDescription;
    @Bind(R.id.quater)
    TextView txtQuater;
    @Bind(R.id.categorie)
    TextView txtCategory;
    @Bind(R.id.nameschool)
    TextView txtlibelleschool;

    @Bind(R.id.spinner)
    Spinner spinnerTown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_activity_step1);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        categoryname = getIntent().getExtras().getString(DadaUtils.FIELD_CATEGORY);
        categoryorder = getIntent().getExtras().getInt(DadaUtils.FIELD_CATEGORY_ORDER);

        toolbar.setTitle("Step1");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        // Set up the login form.

        txtCategory.setText(categoryname);
        txtCategory.setEnabled(false);
        txtEmail.setText("ben@gmail.com");
        CallSchoolActivity();
        ButtonNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    login();
                    updateBeanSchool();
//                    Toast.makeText(getApplicationContext(), schoolEcole.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AddSchoolStep2Activity.class);
                    intent.putExtra(DadaUtils.FIELD_SCHOOL, (Parcelable) schoolEcole);
                    intent.putExtra(DadaUtils.FIELD_CATEGORY,  categoryname );
                    intent.putExtra(DadaUtils.FIELD_CATEGORY_ORDER, categoryorder);
                    startActivity(intent);
                }
            }
        });
    }

    public void updateBeanSchool() {
        schoolEcole.setCategory(categoryorder);
        schoolEcole.setDescription(txtDescription.getText().toString());
        schoolEcole.setEmail(txtEmail.getText().toString());
        schoolEcole.setLibelle(txtlibelleschool.getText().toString());
        schoolEcole.setPhone(txtPhone.getText().toString());
        schoolEcole.setTown(spinnerTown.getSelectedItem().toString());
        schoolEcole.setQuarter(txtQuater.getText().toString());

    }


    /*this method is called for to start SchoolsActivity  */
    private void CallSchoolActivity() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                // this.finish();
            }
        }
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        ButtonNext.setEnabled(true);
    }


    /*Cette méthode est est appelée  et lance la progression */
    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        ButtonNext.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(AddSchoolStep1Activity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


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




    /*this method is called when authentification failed */
    public void onLoginSuccess() {
        ButtonNext.setEnabled(true);
        finish();
    }




    /*This return true if all data is required   */
    public boolean validate() {
        boolean valid = true;
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String description = txtDescription.getText().toString();
        String quater = txtQuater.getText().toString();
        String categorie = txtCategory.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError(getString(R.string.error_field_email));
            valid = false;
        } else {
            txtEmail.setError(null);
        }

        if (phone.isEmpty()) {
            txtPhone.setError(getString(R.string.error_field_phone));
            valid = false;
        } else {
            txtPhone.setError(null);
        }

        if (quater.isEmpty()) {
            txtQuater.setError(getString(R.string.error_field_quater));
            valid = false;
        } else {
            txtQuater.setError(null);
        }

        if (description.isEmpty()) {
            txtDescription.setError(getString(R.string.error_field_description));
            valid = false;
        } else {
            txtDescription.setError(null);
        }
        return valid;
    }

}

