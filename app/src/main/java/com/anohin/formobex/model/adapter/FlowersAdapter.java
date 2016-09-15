package com.anohin.formobex.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anohin.formobex.R;
import com.anohin.formobex.model.pojo.Flower;
import com.anohin.formobex.model.utilities.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

///**
// * Created by Artem on 15.09.2016.
// */

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.Holder> {

    private List<Flower> mFlowers;

    public FlowersAdapter(List<Flower> flowers) {
        mFlowers = flowers;
    }

    public void addFlower(Flower flower) {
        mFlowers.add(flower);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Flower currentFlower = mFlowers.get(position);
        holder.mName.setText(currentFlower.mName);
        holder.mCategory.setText(currentFlower.mCategory);
        holder.mPrice.setText(Double.toString(currentFlower.mPrice));
        holder.mInstructions.setText(currentFlower.mInstructions);


        Glide.with(holder.itemView.getContext())
                .load(Constants.PHOTO_URL + currentFlower.mPhoto)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImage);


       // Picasso.with(holder.itemView.getContext()).load(Constants.PHOTO_URL + currentFlower.mPhoto).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mFlowers.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView mName, mCategory, mPrice, mInstructions;
        ImageView mImage;

        Holder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.flowerInstruction);
        }
    }
}