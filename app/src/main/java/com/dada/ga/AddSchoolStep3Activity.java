package com.dada.ga;

/**
 * Created by steeve on 4/9/16.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.dada.ga.jobs.DadaNetWorkManager;
import com.dada.ga.jobs.RetrofitSchoolInterface;
import com.dada.ga.models.SchoolItem;
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
import nl.changer.polypicker.utils.ImageInternalFetcher;
import retrofit.RestAdapter;

public class AddSchoolStep3Activity extends AppCompatActivity {

    private static final String TAG = AddSchoolStep3Activity.class.getSimpleName();
    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final int INTENT_REQUEST_GET_N_IMAGES = 14;
    private Context mContext;
    private Toolbar toolbar;
    SchoolItem schoolItem;
    private Button ButtonNext;
    List<String> listImages = new ArrayList<>();
    ProgressDialog progressDialog;
    private int categoryorder;
    private String categoryname;



    RetrofitSchoolInterface dadaService = new RestAdapter.Builder().setEndpoint(DadaUtils.API_URL).build().create(RetrofitSchoolInterface.class);

    private ViewGroup mSelectedImagesContainer;
    HashSet<Uri> mMedia = new HashSet<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_step3);
        categoryname = getIntent().getExtras().getString(DadaUtils.FIELD_CATEGORY);
        categoryorder = getIntent().getExtras().getInt(DadaUtils.FIELD_CATEGORY_ORDER);
        progressDialog = new ProgressDialog(AddSchoolStep3Activity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mContext = AddSchoolStep3Activity.this;
        ButtonNext = (Button) findViewById(R.id.next);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Step3");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        View getNImages = findViewById(R.id.get_n_images);
        getNImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNImages();
            }
        });

        schoolItem = getIntent().getExtras().getParcelable(DadaUtils.FIELD_SCHOOL);
        mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);
        getNImages();
        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    updateBeanSchool();
                    Toast.makeText(getApplicationContext(), listImages.size()+" ", Toast.LENGTH_LONG).show();
                    //go to the network layer and
                    // perform relevant tasks like save school in database...

                    if (DadaNetWorkManager.isNetWork(getApplicationContext()))
                        new SchoolTask().execute(schoolItem);
                    else
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_toas_connection), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_photos_school_empty), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void getNImages() {
        Intent intent = new Intent(mContext,ImagePickerActivity.class);
        Config config = new Config.Builder()
                .setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
                .setTabSelectionIndicatorColor(R.color.blue)
                .setCameraButtonColor(R.color.orange)
                .setSelectionLimit(DadaUtils.SCHOOL_PICTURE_NUMBER)// set photo selection limit. Default unlimited selection.
                .build();
        ImagePickerActivity.setConfig(config);
        startActivityForResult(intent, INTENT_REQUEST_GET_N_IMAGES);
    }


    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (resuleCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_N_IMAGES) {
                mMedia.clear();
                //tableau des parcelables...
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


    private void updateBeanSchool() {
        schoolItem.setPictures(listImages);
        schoolItem.setPhotocouverture(listImages.get(0));
       // listImages.remove(0);
    }


    private boolean validate() {
        return listImages.size()>0;
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);//compression
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showMedia() {
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer.removeAllViews();
        mSelectedImagesContainer.removeAllViewsInLayout();
        listImages.clear();

        Iterator<Uri> iterator = mMedia.iterator();
        ImageInternalFetcher imageFetcher = new ImageInternalFetcher(this, 500);
        while (iterator.hasNext()) {
            Uri uri = iterator.next();

            // showImage(uri);
            Log.i(TAG, " uri: " + uri);
            if (mMedia.size() >= 1) {
                mSelectedImagesContainer.setVisibility(View.VISIBLE);
            }

            View imageHolder = LayoutInflater.from(this).inflate(R.layout.media_layout, null);

            // View removeBtn = imageHolder.findViewById(R.id.remove_media);
            // initRemoveBtn(removeBtn, imageHolder, uri);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);
            Drawable drawable;


            if (!uri.toString().contains("content://")) {
                // probably a relative uri
                uri = Uri.fromFile(new File(uri.toString()));
            }
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                String imageStr = getStringImage(bitmap);
                listImages.add(imageStr);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Logger", Log.getStackTraceString(e));
            }

            imageFetcher.loadImage(uri, thumbnail);
            mSelectedImagesContainer.addView(imageHolder);

            // set the dimension to correctly
            // show the image thumbnail.
            int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,300,getResources().getDisplayMetrics());
            int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));
        }
    }


/* void for onPostExecute,SchoolItemSchoolItem for doInbackgroud*/
    class SchoolTask extends AsyncTask<SchoolItem, Void, SchoolItem> {

        @Override
        protected SchoolItem doInBackground(SchoolItem... params) {
//            Dialog();
           try {
               SchoolItem schoolItem=params[0];
               SchoolItem   choolItemList=dadaService.create(schoolItem);
               return choolItemList;
           }catch (RuntimeException e){
              e.printStackTrace();
               Log.e("Error connection",Log.getStackTraceString(e));
               return  null;
           }


        }

        @Override
        public void onPostExecute(SchoolItem schoolItem) {
//            progressDialog.dismiss();

            if(schoolItem==null){
                Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_service_indisponible), Toast.LENGTH_LONG).show();
            }else {
               Toast.makeText(getApplicationContext(),"success", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(),SchoolsActivity.class);
                intent.putExtra(DadaUtils.FIELD_CATEGORY,categoryname);
                intent.putExtra(DadaUtils.FIELD_CATEGORY_ORDER,categoryorder);
                startActivity(intent);
                finish();
            }

        }
    }

    public void Dialog() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
        progressDialog.show();
    }

}

