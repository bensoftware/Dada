package com.dada.ga;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dada.ga.models.Scholarship;
import com.dada.ga.utils.DadaUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;

public class AddScholarshipStep1Activity extends AppCompatActivity {

    private static final String TAG = AddScholarshipStep1Activity.class.getSimpleName();
    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final int INTENT_REQUEST_GET_N_IMAGES = 14;
    private ImageView addScholarShip;
    HashSet<Uri> mMedia = new HashSet<Uri>();
    private TextView txtClick;
    private ImageView preview;
    private List<String> listImages = new ArrayList<>();
    private EditText txtnameorganisation;
    private EditText txtPhone;
    private EditText txtDescription;
    private String ImvImage;
    private EditText txtweb;
    private String imageStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scholarship_step1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nouvelle bourse");
        setSupportActionBar(toolbar);
        preview = (ImageView) findViewById(R.id.preview);
        addScholarShip = (ImageView) findViewById(R.id.ImgAddScholarship);


        txtnameorganisation = (EditText) findViewById(R.id.OrganisationName_);
        txtDescription = (EditText) findViewById(R.id.OrganisationDescription);
        txtPhone = (EditText) findViewById(R.id.organisationPhone);
        txtweb=(EditText) findViewById(R.id.organisationweb);
        final Button valider=(Button)findViewById(R.id.valider);
                ((ImageView) findViewById(R.id.preview)).getDrawable();
        txtClick = (TextView) findViewById(R.id.txtClick);
        txtClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNImages();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    Toast.makeText(getApplicationContext(),"Ok 7 bon",Toast.LENGTH_LONG).show();
                    updateBeanScholarsghip();
                }else{
                    Toast.makeText(getApplicationContext(),"Ok 7 pas bon ymbi",Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);//compression
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void getNImages() {
        Intent intent = new Intent(getApplicationContext(), ImagePickerActivity.class);
        Config config = new Config.Builder()
                .setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
                .setTabSelectionIndicatorColor(R.color.blue)
                .setCameraButtonColor(R.color.orange)
                .setSelectionLimit(DadaUtils.SCHOOL_PICTURE_NUMBER)// set photo selection limit. Default unlimited selection.
                .build();
        ImagePickerActivity.setConfig(config);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }


    /*this method is called when getimage finish    */
    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);
        if (resuleCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_N_IMAGES) {
                mMedia.clear();
                //tableau des URI des images ...
                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

                if (parcelableUris == null) {
                    return;
                }
                // Java doesn't allow array casting, this is a little hack
                Uri[] uris = new Uri[parcelableUris.length];
                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);
                if (uris != null) {
                    for (Uri uri : uris) {
                        Log.i(TAG, " uri: " + uri);
                        mMedia.add(uri);
                    }
                    showMedia();
                }
            }
        }
    }


    /*this method is used for to hydrate scholarship   */
    public void updateBeanScholarsghip(){
        Scholarship scholarship=new Scholarship();
        scholarship.setOrganisationName(txtnameorganisation.getText().toString());
        scholarship.setDescriptionScholarship(txtDescription.getText().toString());
        scholarship.setPhneScholarship(Integer.parseInt(txtPhone.getText().toString()));
        scholarship.setWebadressScholship(txtweb.getText().toString());
        scholarship.setImages(imageStr);
    }



    private void showMedia() {
        listImages.clear();
        Iterator<Uri> iterator = mMedia.iterator();
        while (iterator.hasNext()) {
            Uri uri = iterator.next();
            if (!uri.toString().contains("content://")) {
                // probably a relative uri
                uri = Uri.fromFile(new File(uri.toString()));
            }
            try {
                /*get images */
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                /*Tranformation bitmap to string*/
                imageStr = getStringImage(bitmap);
                listImages.add(imageStr);
                preview.setImageDrawable(DadaUtils.base64Decode(imageStr));
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Logger", Log.getStackTraceString(e));
            }
        }
    }



    public Boolean validate() {
        Boolean valid = false;
        if (txtnameorganisation.getText().toString().isEmpty() || txtnameorganisation.getText().length() < 3) {
            txtnameorganisation.setError("Le nom doit avoir plus de 3 caractère");
            valid = false;
        } else {
        }

        if (txtDescription.getText().toString().isEmpty() || txtDescription.getText().length() < 20) {
            txtDescription.setError("Texte trop court");
        } else {
        }


        if (txtweb.getText().toString().isEmpty()) {
            txtweb.setError("Renseignez le site web de l'oraganisation");
        } else {
        }


        if (txtPhone.getText().toString().isEmpty()) {
            txtPhone.setError("Renseignez lze numero de téléphone");
            valid = false;
        } else {
        }

        if (((ImageView) findViewById(R.id.preview)).getDrawable() == null) {

        }
        return valid;
    }


}