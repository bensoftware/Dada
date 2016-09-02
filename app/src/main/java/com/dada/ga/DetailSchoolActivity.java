package com.dada.ga;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dada.ga.managers.SchoolsManager;
import com.dada.ga.models.EnumCategory;
import com.dada.ga.models.Formation;
import com.dada.ga.models.SchoolItem;
import com.dada.ga.models.User;
import com.dada.ga.utils.DadaUtils;

import me.drakeet.materialdialog.MaterialDialog;

import static com.dada.ga.utils.DadaUtils.FIELD_SCHOOL_POSITION;

public class DetailSchoolActivity extends AppCompatActivity {

    private MaterialDialog mMaterialDialog;
    private ImageView imageview_map;
    private CardView cardview_image;
    private LinearLayout linearLayout_phone;
    private LinearLayout linearLayout_sms;
    private SchoolItem school;
    private AppBarLayout appbar;
    private TextView txt_phone;
    private TextView txt_offre;
    private TextView txt_statut;
    private TextView txt_geoloca;
    private TextView txt_sms;
    private TextView prix_inscription;
    private ImageView imageScrol;
    private int boucle = 0;
    private Toolbar toolbar;
    private HorizontalScrollView scrool;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_school);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_phone = (TextView) findViewById(R.id.textphone);
        txt_offre = (TextView) findViewById(R.id.text_ofree);
        txt_statut = (TextView) findViewById(R.id.textstatut);
        txt_geoloca = (TextView) findViewById(R.id.text_geolocalisation);
        txt_sms = (TextView) findViewById(R.id.text_sms);
        prix_inscription = (TextView) findViewById(R.id.text_prixinscription);
        imageScrol = (ImageView) findViewById(R.id.category_icon);
        scrool = (HorizontalScrollView) findViewById(R.id.scrollphoto);

        mMaterialDialog = new MaterialDialog(this);


        /* On récupère notre barre de vote pour attraper la nouvelle note que sélectionnera l’utilisateur*/
        ((RatingBar) findViewById(R.id.RatingBar01)).setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating,
                                                boolean fromUser) {
                        // On affiche la nouvelle note sélectionnée par l’utilisateur
                        afficheToast("Nouvelle note : " + rating);
                    }
                });



        /* recuperation de ce qui a été envoyé sur cette page on recupere le school */
        school = SchoolsManager.schools.get(getIntent().getExtras().getInt(FIELD_SCHOOL_POSITION));
        GetSchoolFromRecycle();
        ClickOnimagesScroll();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SchoolsActivity.class);
                finish();
            }
        });

        imageview_map = (ImageView) findViewById(R.id.image_map);
        linearLayout_sms = (LinearLayout) findViewById(R.id.linear_sms);
        linearLayout_phone = (LinearLayout) findViewById(R.id.linear_phone);
        linearLayout_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callus();
            }
        });
        linearLayout_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SenSms();
            }
        });
        linearLayout_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   SendMail();
               /* PopupMenu popup = new PopupMenu(getApplicationContext(), linearLayout_phone);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_detail_school, popup.getMenu());
                popup.show();*/
            }
        });


        cardview_image = (CardView) findViewById(R.id.cardImages);
        cardview_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void GetSchoolFromRecycle() {
        appbar = (AppBarLayout) findViewById(R.id.app_bar);
        appbar.setBackground(DadaUtils.base64Decode(school.getPhotocouverture()));
        toolbar.setTitle(school.getLibelle());
        txt_phone.setText(school.getPhone());
        txt_statut.setText(school.getDescription());
        txt_geoloca.setText(school.getQuarter());
        txt_sms.setText(school.getPhone());
        prix_inscription.setText(String.valueOf(school.getPrixinscription() + DadaUtils.FCFA));

        if (school.getCategory() == EnumCategory.UNIVERSITE.getOrder())
            for (Formation sc : school.getFormations()) {
                Formation f = sc;
                txt_offre.setText(f.getLibelle().toString());
            }
        else {

            // txt_offre.setText(school.getCategorie());
        }
    }


    /* Méthode d’aide qui simplifiera la tâche d’affichage d’un message*/
    public void afficheToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    public void ClickOnimagesScroll() {
        LinearLayout ll = null;
        SchoolsManager.pictures = school.getPictures();
        for (boucle = 0; boucle < SchoolsManager.pictures.size(); boucle++) {
            final ImageView imagesScroll = new ImageView(this);
            imagesScroll.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            imagesScroll.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imagesScroll.setPadding(16, 1, 16, 1);
            imagesScroll.setFocusable(true);
            imagesScroll.setImageDrawable(DadaUtils.base64Decode(school.getPictures().get(boucle)));
            ll = (LinearLayout) findViewById(R.id.ref_images);
            ll.addView(imagesScroll);
            imagesScroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getImages(imagesScroll.getDrawable());

                }
            });

        }
    }


    /*This methode receive an image drawable coming to an imageScroll and put it into setviews */
    public void getImages(Drawable drawable) {
        if (drawable != null)
            setViews(drawable);

    }


    /*This methode allow us to send Mail   */
    public void SendMail() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"dada@yahoo.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Sujet");
        email.putExtra(Intent.EXTRA_TEXT, "message");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));

    }


    /* This methode is used for to send sms */
    public void SenSms() {
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:07483387"));
        sms.putExtra("sms_body", "Salut ben !");
        startActivity(sms);
    }

    /* This methode is used for to call*/
    public void Callus() {
        Intent appel = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:07483387"));
        startActivity(appel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_school, menu);
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
            Toast.makeText(getApplicationContext(), "Tu as cliké", Toast.LENGTH_LONG).show();
            Intent itent = new Intent(getApplicationContext(), SwipeImageActivity.class);
            startActivity(itent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    int j = 0;

    /* This method receive view,ressource and displays images into the Dialog     */
    public void setViews(Drawable drawable) {
        mMaterialDialog = new MaterialDialog(this);
        if (mMaterialDialog != null) {
            if (i % 2 != 0) {
                mMaterialDialog.setBackground(drawable);
            } else {
                //  Resources res = getResources();
                //  Bitmap bmp = BitmapFactory.decodeResource(res,resources);
                //  BitmapDrawable bitmapDrawable = new BitmapDrawable(String.valueOf(drawable));
                //   mMaterialDialog.setBackground(bitmapDrawable);
                mMaterialDialog.setBackground(drawable);
            }
            mMaterialDialog.setCanceledOnTouchOutside(true).show();
            i++;

        } else {
            Toast.makeText(getApplicationContext(),
                    "You should init firstly!", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    int i = 0;

    /*This methode is called when user click to the cover picture*/
    public void setView(View v) {
        mMaterialDialog = new MaterialDialog(this);
        if (mMaterialDialog != null) {
            if (i % 2 != 0) {
                mMaterialDialog.setBackground(DadaUtils.base64Decode(school.getPhotocouverture()));
            } else {
                mMaterialDialog.setBackground(DadaUtils.base64Decode(school.getPhotocouverture()));
            }
            mMaterialDialog.setCanceledOnTouchOutside(true).show();
            i++;

        } else {
            Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
        }
    }

}
