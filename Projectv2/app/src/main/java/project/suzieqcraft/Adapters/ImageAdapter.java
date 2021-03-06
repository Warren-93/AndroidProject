package project.suzieqcraft.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.suzieqcraft.Interfaces.IGallery;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

import static com.bumptech.glide.Glide.with;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<Image> imageArrayList;
    private IGallery gListener;

    public ImageAdapter(ArrayList<Image> imageArrayList, IGallery gListener) {
        this.imageArrayList = imageArrayList;
        this.gListener = gListener;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        Image image = imageArrayList.get(position);
        with(holder.itemView.getContext())
                .load(image.getGalleryImage())
                .into(holder.imageGalleryView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gListener.onClick(v, position);
            }
        });
    }

    public void updateAdapter(ArrayList<Image> newImageArrayList) {
        imageArrayList.clear();
        imageArrayList.addAll(newImageArrayList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageGalleryView;

        private ImageViewHolder(View itemView) {
            super(itemView);
            imageGalleryView = itemView.findViewById(R.id.galleryImage);
        }
    }
}

