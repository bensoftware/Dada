package com.dada.ga;

/**
 * Created by ben on 30/03/16.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dada.ga.models.User;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private Boolean statPublicateur=false;
    private Boolean statChercheur=false;
    private User u;

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_phone) EditText _phonedText;
    @Bind(R.id.btn_signup) Button _signupButton;
   /* @Bind(R.id.link_login) TextView _loginLink;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        CheckBox c=(CheckBox)findViewById(R.id.checkBox_visiteur);
        c.setChecked(true);
      _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxPublicat:
                if (checked){
                    CheckBox ch=(CheckBox)findViewById(R.id.checkBox_visiteur);
                    ch.setChecked(false);
                    // Put some meat on the sandwich
                     statPublicateur=true;
                    Toast.makeText(SignupActivity.this,
                            "Bro, try Android :)", Toast.LENGTH_LONG).show();
                } else{
                    CheckBox ch=(CheckBox)findViewById(R.id.checkBoxPublicat);
                    ch.setChecked(true);
                    statPublicateur=false;
                }

                // Remove the meat
                break;

            case R.id.checkBox_visiteur:
                if (checked){
                    // Cheese me
                    CheckBox ch=(CheckBox)findViewById(R.id.checkBoxPublicat);
                    ch.setChecked(false);
                    Toast.makeText(SignupActivity.this,
                            "Bro, ben :)", Toast.LENGTH_LONG).show();
                    statChercheur=true;
                }

                else{
                    CheckBox ch=(CheckBox)findViewById(R.id.checkBox_visiteur);
                    ch.setChecked(true);
                    Toast.makeText(SignupActivity.this,
                            "Bro, ben :)", Toast.LENGTH_LONG).show();
                    statChercheur=false;
                }
                // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich

        }
    }



    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _phonedText.getText().toString();


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);


        Intent inten=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inten);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _phonedText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("plus de trois caractères");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Entrez une adresse valide");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _phonedText.setError("Entre 4 et 10 caractères alphanumeric");
            valid = false;
        } else {
            _phonedText.setError(null);
        }
        return valid;
    }

    private Boolean verifcation(String test) {
        test="ben@gmail.com";
        return  test.equals(_emailText.getText().toString());
    }

}
