package com.dada.ga.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Log;

import com.dada.ga.App;
import com.dada.ga.DetailSchoolActivity;
import com.dada.ga.R;

import com.dada.ga.models.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * Created by ben on 03/04/16.
 */

public class DadaUtils {

    public static final String API_URL="http://192.168.1.128:25000";
    private static Context context;
    public static final int SCHOOL_PICTURE_NUMBER = 5;
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_CATEGORY_ORDER = "categoryorder";
    public static final String FIELD_SCHOOL_POSITION = "schoolposition";
    public static final String FIELD_SCHOOL = "school";
    public static final String FCFA = "FCFA";
    public static final String FIELD_SCHOLL_FOR_SWIPE="swipeActivity";

    public DadaUtils(Context context) {
        this.context = context;
    }


    /* this methode receive int and decode it in String  */
    public static String base64Encode(int drawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        String encodedImage = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        return encodedImage;
    }


    public static Drawable base64Decode(String encodedrawable) {
        byte[] bitmapdata = Base64.decode(encodedrawable, Base64.DEFAULT);
        Log.d("Image:",""+ Arrays.toString(bitmapdata));
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        Log.d("Bitmap:",""+ bitmap);
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }


    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
