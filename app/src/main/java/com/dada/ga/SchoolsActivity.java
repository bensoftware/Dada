package com.dada.ga;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import static com.dada.ga.utils.DadaUtils.*;
import com.dada.ga.adapters.RVSchoolsAdapter;
import com.dada.ga.jobs.RetrofitSchoolInterface;
import com.dada.ga.managers.SchoolsManager;
import com.dada.ga.models.Formation;
import com.dada.ga.models.SchoolItem;
import com.dada.ga.listeners.RecyclerItemClickListener;
import com.dada.ga.models.EnumCategory;
import com.dada.ga.models.User;
import com.dada.ga.utils.DadaUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class SchoolsActivity extends AppCompatActivity {

    private EditText inputSearch;
    private RecyclerView rv;
    private Toolbar toolbar;
    private String title;
    public static ImageView addSchool;
    private int categoryorder;
    private String categoryname;
    private Boolean _testPublicateur2 = false;
    private User user;
    private DadaUtils d;
    private String TAG = "SchoolActivityTAG";
    private
    RetrofitSchoolInterface dadaService = new RestAdapter.Builder().setEndpoint(DadaUtils.API_URL).build().create(RetrofitSchoolInterface.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);
        categoryorder = getIntent().getExtras().getInt(DadaUtils.FIELD_CATEGORY_ORDER);
        categoryname = getIntent().getExtras().getString(DadaUtils.FIELD_CATEGORY);


        InitialiseAllAboutToolbar();
        initialiseRecycleSchool();
        initializeDataSchool();
       // new SchoolTask().execute();
        initializeAdapterSchool();
        Search();
        ClickOnrecycleSchool();
        Clime();
    }


    //this method is called when activity is calledit initialize toolbar and others objects...
    private void InitialiseAllAboutToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = getIntent().getExtras().getString(FIELD_CATEGORY);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);

        addSchool = (ImageView) findViewById(R.id.submitAdd);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //this method return void and initialize the Recycleview which used for display Schools object.
    private void initialiseRecycleSchool() {
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
    }


    /*This method return void and allow us to do search,it is called into the costructor*/
    private void Search() {
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                String query = cs.toString().toLowerCase();
                final List<SchoolItem> filteredList = new ArrayList<SchoolItem>();
                for (int i = 0; i < SchoolsManager.schools.size(); i++) {
                    if (isAdMatch(SchoolsManager.schools.get(i), query)) {
                        filteredList.add(SchoolsManager.schools.get(i));
                    }
                }
                rv.setLayoutManager(new LinearLayoutManager(SchoolsActivity.this));
                RVSchoolsAdapter adapter = new RVSchoolsAdapter(filteredList,getApplicationContext());
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "size" + filteredList.size());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    //This method return void and is called when user click on Recycleview Item.
    private void ClickOnrecycleSchool() {
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //sending position of object wich user cliked to  DetailSchoolActivity
                        Intent intent = new Intent(getApplicationContext(), DetailSchoolActivity.class);
                        intent.putExtra(FIELD_SCHOOL_POSITION,position);
                        startActivity(intent);
                    }
                })
        );
    }


    /**
     * Il sied de rapelrer qu'il y a deux types d'université public et privé
     * La variable Get recupère la valeur de la catégorie et est utilseé dans la methode CallMe pour faire des tests.
     */

    private void initializeDataSchool() {
        //schools = new ArrayList<>();
        String stat1 = "Cet établissement a été crée en 1970 par les chefs d'état des 11 onzes pays membre.cette institution forme les " +
                "les analyste-programmeur,les Ingenieurs ainsi que les maîtres en informatique de gestion. ";

        String stat2 = "Etablissement mieux encore université public crée en 1965 par le Président Gabonais de l'époque Omar Bango." +
                "Cette dernière forme des mathématiciens,stattisticiens,economiste,des juristes....";

        String stat3 = "Etablissement privé crée le 18 Janvier 2002 par BenJudicaêlle.ce dernièr est un établissemnt qui n'est censé formé que des gestionnaires";



        /*Création des formations  pour des étatblissements*/
        List<Formation> formati = new ArrayList<>();
        Formation f = new Formation("IngenieurAP", 1458.5, 2);
        formati.add(new Formation("Ingenieur", 1458.5, 3));
        formati.add(new Formation("Analyste", 1458.5, 4));
        formati.add(new Formation("Programmeur", 1458.5, 12));

       /*Liste des images pour un etablisement dans le cadre de ce projet,nous en avons  5  images*/
        List<String> lesImages = new ArrayList<>();
        d = new DadaUtils(getApplicationContext());
        lesImages.add(d.base64Encode(R.drawable.ecole_3));
        lesImages.add(d.base64Encode(R.drawable.img6));
        lesImages.add(d.base64Encode(R.drawable.ecole));
        lesImages.add(d.base64Encode(R.drawable.ecole_5));
        lesImages.add(d.base64Encode(R.drawable.emma));



        /*Test on school categorie */
        if (title.equals(EnumCategory.UNIVERSITE.getLibelle())) {
            SchoolsManager.schools.add(new SchoolItem(15 / 20, lesImages, DadaUtils.base64Encode(R.drawable.emma), stat1, "Libreville", EnumCategory.UNIVERSITE.getOrder(), "IAI", "00242 45 877", "prive", "ben@gmail.com", 125487.2, true, formati, "Institut Africain d'Informatique"));
            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImages, DadaUtils.base64Encode(R.drawable.ecole_5), stat1, "Libreville", EnumCategory.UNIVERSITE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "Université Omar Bango"));
            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImages, DadaUtils.base64Encode(R.drawable.ecole_6), stat1, "Libreville", EnumCategory.UNIVERSITE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "Zukus University"));
            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImages, DadaUtils.base64Encode(R.drawable.ecole_3), stat1, "Libreville", EnumCategory.UNIVERSITE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "Zorobo University"));
        } else if (title.equals(EnumCategory.PREPRIMAIRE.getLibelle())) {
            String t1 = " Jardin d'enfants public creée en 1922 par le prêtre Sassou Nguesso Bernart alis le boss de la paix et de la prière";
            String t2 = "Jardin d'enfants  public rée le 4 Mars 1999 par le bosss";

            List<String> lesImagespreprimaire = new ArrayList<>();
            d = new DadaUtils(getApplicationContext());
            lesImages.add(d.base64Encode(R.drawable.im8));
            lesImages.add(d.base64Encode(R.drawable.im17));
            lesImages.add(d.base64Encode(R.drawable.im30));
            lesImages.add(d.base64Encode(R.drawable.ecole_5));
            lesImages.add(d.base64Encode(R.drawable.im8));


            List<Formation> formationPreprimaire = new ArrayList<>();
            formationPreprimaire.add(new Formation("IngenieurAP", 1458.5, 2));
            formationPreprimaire.add(new Formation("Ingenieur", 1458.5, 3));
            formationPreprimaire.add(new Formation("Analyste", 1458.5, 4));
            formationPreprimaire.add(new Formation("Programmeur", 1458.5, 12));


            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImagespreprimaire, DadaUtils.base64Encode(R.drawable.ecole_5), t1, "Librevile", EnumCategory.PREPRIMAIRE.getOrder(), "IAIA", "+241 06488841 ", "public", "Saint@gmail.com", 14587, true, formationPreprimaire, "Ecole Kotazo"));
            SchoolsManager.schools.add(new SchoolItem(18 / 20, lesImagespreprimaire, DadaUtils.base64Encode(R.drawable.img1), t2, "Port-gentil", EnumCategory.PREPRIMAIRE.getOrder(), "Centre ville", "+241 012547895 ", "privée", "Marie@gmail.com", 14588, false, formationPreprimaire, "Fipine"));

        } else if (title.equals(EnumCategory.PRIMAIRE.getLibelle())) {

            String ts1 = "Ecole primaire public crée en 2010 par Mr Mboungou Judicaêlle dont l'objectif principale était et est de" + "promouvoir l'excellence et mettre en plce un sytème éducatif de base visant.... ";
            String ts2 = "Ecole primare  public  crée en 2005 par Pouti Arsène.Ce dernier vise à augmenter le niveau d'étude de nos enfants...";


            List<String> lesImagesprimaire = new ArrayList<>();
            d = new DadaUtils(getApplicationContext());
            lesImagesprimaire.add(d.base64Encode(R.drawable.ecole));
            lesImagesprimaire.add(d.base64Encode(R.drawable.ecole_6));
            lesImagesprimaire.add(d.base64Encode(R.drawable.ecole_4));
            lesImagesprimaire.add(d.base64Encode(R.drawable.ecole_5));
            lesImagesprimaire.add(d.base64Encode(R.drawable.ecole_3));


            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImagesprimaire, DadaUtils.base64Encode(R.drawable.im3), ts1, "Odoua", EnumCategory.PRIMAIRE.getOrder(), "IAI", "00242 45 877", "prive", "ben@gmail.com", 125487.2, true, formati, "Ecole Catholique Fatima"));
            SchoolsManager.schools.add(new SchoolItem(17 / 20, lesImagesprimaire, DadaUtils.base64Encode(R.drawable.im17), ts2, "Libreville", EnumCategory.PRIMAIRE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "Ecole Primaire Saint Jean"));
        } else if (title.equals(EnumCategory.SECONDAIRE.getLibelle())) {
            String s1 = "Collège public crée en 1879 par l'état Gabonnais visant à former les jeunes Gabonnais du sécondaire;mieux encore à les préparer à la cours des grands qu'est le lycée";
            String s2 = "Collège privé crée par Mr Adan Paul en 1999,cet établissement dont la slogan est 'Mbolo' signifiant Bonjour en fraçais";

        /*Liste des images*/
            List<String> lesImagespSecondaires = new ArrayList<>();
            d = new DadaUtils(getApplicationContext());
            lesImagespSecondaires.add(d.base64Encode(R.drawable.img4));
            lesImagespSecondaires.add(d.base64Encode(R.drawable.im30));
            lesImagespSecondaires.add(d.base64Encode(R.drawable.im12));
            lesImagespSecondaires.add(d.base64Encode(R.drawable.im8));
            lesImagespSecondaires.add(d.base64Encode(R.drawable.im1));


            SchoolsManager.schools.add(new SchoolItem(10 / 20, lesImages, DadaUtils.base64Encode(R.drawable.ecole), s1, "Odoua", EnumCategory.SECONDAIRE.getOrder(), "IAI", "00242 45 877", "prive", "ben@gmail.com", 125487.2, true, formati, "CEG-HAMMAR"));
            SchoolsManager.schools.add(new SchoolItem(11 / 20, lesImages, DadaUtils.base64Encode(R.drawable.emma), s2, "Libreville", EnumCategory.SECONDAIRE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "CEG-CENTRAL"));

        } else if (title.equals(EnumCategory.LYCEE.getLibelle())) {

            String ss1 = "Cet établissement est un  lycée public a été crée en 1785 par le président Léon Mba Kidiaba.";
            String ss2 = "Cet établissemnt est un lycée privé creée en 1995 par Ben Judicaëlle Mboungou de la street";

            int pictures9[] = {R.drawable.im12, R.drawable.im17, R.drawable.im30, R.drawable.im1};

            SchoolsManager.schools.add(new SchoolItem(12 / 20, lesImages, DadaUtils.base64Encode(R.drawable.ecole), ss1, "Odoua", EnumCategory.LYCEE.getOrder(), "IAI", "00242 45 877", "prive", "ben@gmail.com", 125487.2, true, formati, "Lycée Justin Victor Sathoud"));
            SchoolsManager.schools.add(new SchoolItem(13 / 20, lesImages, DadaUtils.base64Encode(R.drawable.emma), ss2, "Libreville", EnumCategory.LYCEE.getOrder(), "IAI", "00242 45 877", "public", "kk@gmail.com", 125487.2, false, formati, "Lycée Victor Auganeur"));

        } else {
            Toast.makeText(getApplicationContext(), "la base de donné est vide", Toast.LENGTH_LONG).show();
        }
    }


    /*  this method is called for to  */
    private void CallInscription1() {
        Intent it = new Intent(getApplicationContext(), AddSchoolStep1Activity.class);
        it.putExtra(DadaUtils.FIELD_CATEGORY,categoryname);
        it.putExtra(DadaUtils.FIELD_CATEGORY_ORDER, categoryorder);
        startActivity(it);
    }

    /* This methode return void and is called when users clik on Recycleview Schools: */
    /* Cette methode est appelée lorsque l'utilisateur choisi une categorie p : */
    private void Clime() {
        addSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (categoryorder == EnumCategory.UNIVERSITE.getOrder()) {
                    CallInscription1();
                }

                if (categoryorder == EnumCategory.PREPRIMAIRE.getOrder()) {
                      CallInscription1();
                }

                if (categoryorder == EnumCategory.PRIMAIRE.getOrder()) {
                    CallInscription1();
                }

                if (categoryorder == EnumCategory.LYCEE.getOrder()) {
                    CallInscription1();
                }

                if (categoryorder == EnumCategory.SECONDAIRE.getOrder()) {
                     CallInscription1();
                }
            }
        });
    }


    /*this ;ethode initialise adapter*/
    private void initializeAdapterSchool() {
        RVSchoolsAdapter adapter = new RVSchoolsAdapter(SchoolsManager.schools,getApplicationContext());
        rv.setAdapter(adapter);
    }

    /*this methode Get SchoolItem and String object and return String object which contain....*/
    public boolean isAdMatch(SchoolItem schoolItem, String query) {
        String text = schoolItem.getDescription().toLowerCase() +" " +schoolItem.getTown().toLowerCase() +" "+schoolItem.getQuarter().toLowerCase()+" " +schoolItem.getPhone().toLowerCase()+" ";
        return text.contains(query.toLowerCase());
    }



  public void initDynamicData(){
      List<SchoolItem> schools=new ArrayList<>();
        schools=dadaService.getAll(categoryorder);
     for(SchoolItem sc:schools){
          SchoolsManager.schools.add(sc);
      }
    }





    class SchoolTask extends AsyncTask<SchoolItem, Void, SchoolItem> {
        @Override
        protected SchoolItem doInBackground(SchoolItem... params) {
//            Dialog();
            try {
                initDynamicData();
            }catch (RuntimeException e){
                e.printStackTrace();
                Log.e("Error connection",Log.getStackTraceString(e));

            }
            return  null;
        }


        @Override
        public void onPostExecute(SchoolItem schoolItem) {
//            progressDialog.dismiss();

            if(schoolItem==null){
                Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_service_indisponible), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"success", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }


}
