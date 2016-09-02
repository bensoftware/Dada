package com.dada.ga.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dada.ga.R;
import com.dada.ga.models.SchoolItem;
import com.dada.ga.utils.DadaUtils;

import java.util.List;

/**
 * Created by steeve on 3/20/16.
 */
public class RVSchoolsAdapter extends RecyclerView.Adapter<RVSchoolsAdapter.SchoolViewHolder> {


    List<SchoolItem> schools;
    DadaUtils dadautils;


    public static class SchoolViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView description;
        TextView town;
        ImageView picture;
        TextView school_category;
        TextView School_quartier;
        TextView School_phone;


        SchoolViewHolder(View itemView) {
            super(itemView);
            cv =(CardView) itemView.findViewById(R.id.cv);
            description =(TextView) itemView.findViewById(R.id.school_description);
            town = (TextView) itemView.findViewById(R.id.school_town);
            picture =(ImageView) itemView.findViewById(R.id.school_picture);
          //  school_category=(TextView) itemView.findViewById(R.id.school_category);
            //nouveelle variable ajoutée Shool_phone
            School_quartier= school_category=(TextView) itemView.findViewById(R.id.school_category);
          //  School_phone= (TextView) itemView.findViewById(R.id.textphone);
        }
    }

    public RVSchoolsAdapter(List<SchoolItem> schools,Context context) {
        this.schools = schools;
        dadautils=new DadaUtils(context);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVSchoolsAdapter.SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);
        SchoolViewHolder pvh = new SchoolViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVSchoolsAdapter.SchoolViewHolder holder, int i) {
        Log.d("Taille=", "" + schools.size());
        holder.description.setText(schools.get(i).getLibelle());
        holder.town.setText(schools.get(i).getTown());

        holder.picture.setImageDrawable(dadautils.base64Decode(schools.get(i).getPhotocouverture()));
       // holder.school_category.setText(schools.get(i).getCategorie().toLowerCase());
        holder.School_quartier.setText(schools.get(i).getQuarter());
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }


    public void addSchool(SchoolItem sc) {
        schools.add(sc);
    }



    }
