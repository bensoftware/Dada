package com.dada.ga;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    MediaPlayer splashsound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  splashsound=new MediaPlayer.create();
         Thread chrono=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                    int progress;
                    for (progress=0;progress<=1000000;progress++)
                    {
                        //la méthode publishProgress met à jour l'interface en invoquant la méthode onProgressUpdate;
                      //  mProgressBar.setProgress(progress);
                    }
                }catch (InterruptedException e){


                }finally {
                    Intent i=new Intent(getApplicationContext(),SignupActivity.class);
                    startActivity(i);
                }

            }

        });
chrono.start();

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



  /**  private class BigCalcul extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            // Mise à jour de la ProgressBar
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            int progress;
            for (progress=0;progress<=10000000;progress++)
            {
                for (int i=0; i<10000000; i++){}
                //la méthode publishProgress met à jour l'interface en invoquant la méthode onProgressUpdate
                publishProgress(progress);
                progress++;
            }
             if(progress==10000000){
                 Intent i=new Intent(getApplicationContext(),AcceuilActivity.class);
                 startActivity(i);
             }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
        }
    }*/
}
