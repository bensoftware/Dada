package com.dada.ga.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dada.ga.R;


/**
 * Created by ben on 12/03/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {


    private Context ctx;
    private int[] pictures;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx, int[] pic) {
        this.ctx = ctx;
        this.pictures=pic;
    }

    @Override
    public int getCount() {
        return pictures.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==((LinearLayout)object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swape_layout,container,false);
        ImageView imageview= (ImageView) item_view.findViewById(R.id.imageView);
        TextView textView= (TextView) item_view.findViewById(R.id.image_count);
        textView.setText("ju cliok" + position);
        container.addView(item_view);
        imageview.setImageResource(pictures[position]);
      //  imageview.getImageAlpha();
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((LinearLayout) object);
   }

}
