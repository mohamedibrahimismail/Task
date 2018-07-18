package com.example.miestro.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MIESTRO on 18/07/2018.
 */

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "StaggeredRecyclerViewAd";

    private ArrayList<Contents> content_list=new ArrayList<Contents>();
    private Context mContext;

    public StaggeredRecyclerViewAdapter(Context mContext , ArrayList<Contents> content_list) {
        this.content_list = content_list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item,parent,false);

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.d(TAG,"onBindViewHolder");
       // holder.image.setMinimumHeight(Integer.parseInt(content_list.get(position).getHeight()));
      /*  RequestOptions requestOptions = new RequestOptions()
        .placeholder(R.mipmap.ic_launcher);
        Glide.with(mContext)
                .load(content_list.get(position).getImage())
                .apply(requestOptions)
                .into(holder.image);*/
        Picasso.with(mContext).load(content_list.get(position).getImage()).into(holder.image);
        holder.description.setText(content_list.get(position).getProduct_Description());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: "+position);
                Toast.makeText(mContext,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        holder.price.setText("$"+content_list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    ImageView image;
    TextView  description;
    TextView  price;

    public ViewHolder(View itemView) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.description = itemView.findViewById(R.id.description);
        this.price = itemView.findViewById(R.id.price);
    }
}
}
