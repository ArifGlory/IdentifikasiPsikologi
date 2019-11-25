package com.example.fujimiya.clientpakarpsikologi.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fujimiya.clientpakarpsikologi.DetailKategori;
import com.example.fujimiya.clientpakarpsikologi.R;

import java.util.List;



/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.MyViewHolder> {

    private Context mContext;

    String[] namaKategori ={"cat_autonomy","cat_environment","cat_personal","cat_positive",
            "cat_purpose","cat_self"};
    private String nama_key;
    private Resources res;
    Intent intent;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaKategori;
        public LinearLayout lineItem;


        public MyViewHolder(View view) {
            super(view);
            tvNamaKategori = view.findViewById(R.id.tvNamaKategori);
            lineItem = view.findViewById(R.id.lineItem);

        }
    }

    public AdapterKategori(Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kategori, parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (namaKategori.length == 0){

            Log.d("isi Layanan: ",""+namaKategori.length);
        }else {

           /* namaKategori ={"cat_autonomy","cat_environment","cat_personal","cat_positive",
                    "cat_purpose","cat_self"};
            */
            res = mContext.getResources();
            nama_key     = namaKategori[position];
            int resIdentifier    = res.getIdentifier(nama_key,"string",mContext.getPackageName());

            holder.tvNamaKategori.setText(resIdentifier);

            holder.lineItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String id_cat_title,id_cat_desc,id_cat_skill,id_cat_high,id_cat_low,
                            catTitle,catDesc,catSkill,catHigh,catLow;
                    int resTitle,resDesc,resSkill,resHigh,resLow;
                    Log.d("nama_key:",nama_key);


                    switch (position){

                        case 0:
                            id_cat_title  = "cat_autonomy";
                            id_cat_desc  = "cat_autonomy_desc";
                            id_cat_skill = "cat_autonomy_skill";
                            id_cat_high = "cat_autonomy_high";
                            id_cat_low = "cat_autonomy_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;

                        case 1:
                            id_cat_title  = "cat_environment";
                            id_cat_desc  = "cat_environment_desc";
                            id_cat_skill = "cat_environment_skill";
                            id_cat_high = "cat_environment_high";
                            id_cat_low = "cat_environment_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;

                        case 2:
                            id_cat_title  = "cat_personal";
                            id_cat_desc  = "cat_personal_desc";
                            id_cat_skill = "cat_personal_skill";
                            id_cat_high = "cat_personal_high";
                            id_cat_low = "cat_personal_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;

                        case 3:
                            id_cat_title  = "cat_positive";
                            id_cat_desc  = "cat_positive_desc";
                            id_cat_skill = "cat_positive_skill";
                            id_cat_high = "cat_positive_high";
                            id_cat_low = "cat_positive_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;

                        case 4:
                            id_cat_title  = "cat_purpose";
                            id_cat_desc  = "cat_purpose_desc";
                            id_cat_skill = "cat_purpose_skill";
                            id_cat_high = "cat_purpose_high";
                            id_cat_low = "cat_purpose_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;

                        case 5:
                            id_cat_title  = "cat_self";
                            id_cat_desc  = "cat_self_desc";
                            id_cat_skill = "cat_self_skill";
                            id_cat_high = "cat_self_high";
                            id_cat_low = "cat_self_low";
                            resTitle    = res.getIdentifier(id_cat_title,"string",mContext.getPackageName());
                            resDesc    = res.getIdentifier(id_cat_desc,"string",mContext.getPackageName());
                            resSkill    = res.getIdentifier(id_cat_skill,"string",mContext.getPackageName());
                            resHigh    = res.getIdentifier(id_cat_high,"string",mContext.getPackageName());
                            resLow    = res.getIdentifier(id_cat_low,"string",mContext.getPackageName());

                            catTitle = res.getString(resTitle);
                            catDesc = res.getString(resDesc);
                            catSkill = res.getString(resSkill);
                            catHigh = res.getString(resHigh);
                            catLow = res.getString(resLow);

                            intent = new Intent(mContext, DetailKategori.class);
                            intent.putExtra("catTitle",catTitle);
                            intent.putExtra("catDesc",catDesc);
                            intent.putExtra("catSkill",catSkill);
                            intent.putExtra("catHigh",catHigh);
                            intent.putExtra("catLow",catLow);
                            mContext.startActivity(intent);
                            break;


                    }
                }
            });




        }

    }


    @Override
    public int getItemCount() {
        //return namaWisata.length;
        return namaKategori.length;
    }
}
