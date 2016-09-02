package com.dada.ga;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.dada.ga.utils.TypefaceUtil;

/**
 * Created by steeve on 3/10/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "LearningCurve_OT.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        Toast.makeText(this, "cooooooooooooolllll", Toast.LENGTH_LONG);
    }

    public void clicktest(View view) {
        Toast.makeText(getApplicationContext(), "je suis le rois", Toast.LENGTH_SHORT).show();
    }
}
