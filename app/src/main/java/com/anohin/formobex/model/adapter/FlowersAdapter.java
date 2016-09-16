package com.anohin.formobex.model.adapter;

import com.anohin.formobex.OnImageViewListener;
import com.anohin.formobex.R;
import com.anohin.formobex.databinding.ItemRowBinding;
import com.anohin.formobex.model.pojo.Flower;
import com.anohin.formobex.model.utilities.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.swipe.SwipeLayout;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

///**
// * Created by Artem on 15.09.2016.
// */

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.Holder> {

    private List<Flower> mFlowers;
    private OnImageViewListener mListener;

    public FlowersAdapter(List<Flower> flowers) {
        mFlowers = flowers;
    }

    @BindingAdapter("bind:img")
    public static void loadImage(ImageView imageView, String v) {
        Glide.with(imageView.getContext()).load(Constants.PHOTO_URL + v)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public void addFlower(Flower flower) {
        mFlowers.add(flower);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRowBinding binding = ItemRowBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        //View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,
        // false);

        return new Holder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Flower currentFlower = mFlowers.get(position);
        holder.mBinding.setFlower(currentFlower);
        holder.mImageUrl = currentFlower.mPhoto;
    }

    @Override
    public int getItemCount() {
        return mFlowers.size();
    }

    public void deleteItem(int position) {
        mFlowers.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void setListener(OnImageViewListener listener) {
        mListener = listener;
    }

    class Holder extends RecyclerView.ViewHolder {

//        @Bind(R.id.mName)           TextView flowerName ;
//        @Bind(R.id.mCategory)       TextView flowerCategory;
//        @Bind(R.id.mPrice)          TextView flowerPrice;
//        @Bind(R.id.mInstructions)   TextView flowerInstructions;
//        @Bind(R.id.mImage)          Image flowerImage;

        ItemRowBinding mBinding;

        TextView mName, mCategory, mPrice, mInstructions;
        ImageView mImage;
        String mImageUrl;
        SwipeLayout mSwipeLayout;

        Holder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onImageViewClicked(v, mImageUrl);
                    }

                }
            });
        }
    }

}