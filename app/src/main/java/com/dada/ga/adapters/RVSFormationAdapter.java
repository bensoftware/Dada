package com.dada.ga.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dada.ga.R;
import com.dada.ga.models.Formation;
import com.dada.ga.models.SchoolItem;

import java.util.List;

/**
 * Created by steeve on 3/20/16.
 */
public class RVSFormationAdapter extends RecyclerView.Adapter<RVSFormationAdapter.FormationViewHolder> {


    private List<Formation> formations;
    private Context mcontext;
    private LayoutInflater mlayoutInflater;


    public RVSFormationAdapter(List<Formation> formations, Context mcontext) {

        this.formations = formations;
        this.mcontext = mcontext;
        this.mlayoutInflater = (LayoutInflater) mcontext.getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
    }

    public static class FormationViewHolder extends RecyclerView.ViewHolder {

        TextView libelle_formation;
        TextView prix_formation;

        FormationViewHolder(View itemView) {
            super(itemView);

            libelle_formation =(TextView)itemView.findViewById(R.id.libelleFormation);
            prix_formation= (TextView) itemView.findViewById(R.id.prixFormation);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public FormationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formationitem,parent, false);
        FormationViewHolder pvh = new FormationViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FormationViewHolder holder, int i) {

        if(formations.get(i).getLibelle()!=null){
            holder.libelle_formation.setText(formations.get(i).getLibelle().toString());
            holder.prix_formation.setText(String.valueOf(formations.get(i).getPrix()));//formations.get(i).getPrix_formation();
        }
//
    }




    public void addlistItemAjouter(Formation f,int position){
        formations.add(f);
        notifyItemInserted(position);
    }

    public void RemovelistItemEffacer(Formation f,int position){
       formations.remove(f);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return formations.size();
    }

}
