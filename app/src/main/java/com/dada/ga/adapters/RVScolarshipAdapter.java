package com.dada.ga.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dada.ga.models.Scholarship;
import  com.dada.ga.R;
import com.dada.ga.utils.DadaUtils;

import java.util.List;

/**
 * Created by ben on 21/03/16.
 */
public class RVScolarshipAdapter extends RecyclerView.Adapter< RVScolarshipAdapter.AdViewHolder> {

    List<Scholarship> bourses;


    public RVScolarshipAdapter(List<Scholarship> ads) {
        this.bourses = ads;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public  RVScolarshipAdapter.AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bourse_item, parent, false);
        AdViewHolder pvh = new AdViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder( RVScolarshipAdapter.AdViewHolder holder, int i) {
        holder.adDescription.setText(bourses.get(i).getLibelleScholarship());
        holder.adImage.setImageDrawable(DadaUtils.base64Decode(bourses.get(i).getImages()));

    }


    @Override
    public int getItemCount() {
        return bourses.size();
    }


    public static class AdViewHolder extends RecyclerView.ViewHolder {
        TextView adDescription;
        ImageView adImage;
        TextView Titre;
        AdViewHolder(View itemView) {
            super(itemView);
            adDescription = (TextView) itemView.findViewById(R.id.ad_description);
            Titre=(TextView) itemView.findViewById(R.id.ad_publish_date);
            adImage = (ImageView) itemView.findViewById(R.id.ad_photo);
        }
    }


}
