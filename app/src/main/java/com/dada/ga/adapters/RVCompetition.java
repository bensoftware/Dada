package com.dada.ga.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dada.ga.R;
import com.dada.ga.models.Competition;

import java.util.List;

/**
 * Created by ben on 30/03/16.
 */
public class RVCompetition extends RecyclerView.Adapter< RVCompetition.AdViewHolder> {


    List<Competition> ListeCompetitions;


    public RVCompetition(List<Competition> ads) {
        this.ListeCompetitions = ads;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVCompetition.AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.competition_item, parent, false);
        AdViewHolder pvh = new AdViewHolder(v);
        return pvh;
    }



    @Override
    public void onBindViewHolder( RVCompetition.AdViewHolder holder, int i) {

        holder. Description_competition.setText(ListeCompetitions.get(i).getLibelle_competition());
        holder. Image_competition.setImageResource(ListeCompetitions.get(i).getPhoto_Competition());
        //holder.adTown.setText(ads.get(i).getTown());
    }

    @Override
    public int getItemCount() {
        return  this.ListeCompetitions.size();
    }


    public static class AdViewHolder extends RecyclerView.ViewHolder {
        // CardView cv;
        TextView Description_competition;
        ImageView Image_competition;
        TextView Libelle_competition;

        AdViewHolder(View itemView) {
            super(itemView);
            Description_competition = (TextView) itemView.findViewById(R.id.description_competition);
            //Titre=(TextView) itemView.findViewById(R.id.ad_publish_date);
            Image_competition = (ImageView) itemView.findViewById(R.id.photo_competition);
            // adTown=(TextView) itemView.findViewById(R.id.ad_town);
        }
    }

}
