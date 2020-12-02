package com.sphurti.royalrajasthan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridAdapterRecyclerView extends RecyclerView.Adapter<GridAdapterRecyclerView.GridViewHolder>{

    List<String> categoryList;
    List<String> categoryDescriptionList;
    List<String> imageList;
    ClickInterface customClickListener;
    Context homeContext;

    public GridAdapterRecyclerView(Context context, List<String> categoryList, List<String> categoryDescriptionList, List<String> imageList, ClickInterface customClickListener) {
        this.homeContext = context;
        this.categoryList = categoryList;
        this.imageList = imageList;
        this.categoryDescriptionList = categoryDescriptionList;
        this.customClickListener = customClickListener;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.intro_card_1, parent, false);
        final GridViewHolder viewHolder = new GridViewHolder(itemView);
        viewHolder.imageView = itemView.findViewById(R.id.image);
        viewHolder.textViewCategoryName = itemView.findViewById(R.id.heading);
        viewHolder.textViewCategoryDescription = itemView.findViewById(R.id.cityDescription);
        itemView.setOnClickListener(v -> customClickListener.onItemClick(v, viewHolder.getPosition()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        String image_url= imageList.get(position);
        Glide.with(homeContext).load(image_url).apply(options).into(holder.imageView);
        holder.imageView.setImageResource(R.drawable.splash1);
        holder.textViewCategoryName.setText(categoryList.get(position));
        holder.textViewCategoryDescription.setText(categoryDescriptionList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewCategoryName, textViewCategoryDescription;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
