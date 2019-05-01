package project.suzieqcraft.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;
import project.suzieqcraft.View.Fullscreen;

import static com.bumptech.glide.Glide.with;


public class FullscreenAdapter extends RecyclerView.Adapter<FullscreenAdapter.FullscreenHolder> {

    private ArrayList<Image> fullscreenArrayList;


    public FullscreenAdapter(ArrayList<Image> fullscreenArrayList) {
        this.fullscreenArrayList = fullscreenArrayList;
    }

    @NonNull
    @Override
    public FullscreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View itemView = inflater.from( parent.getContext() ).inflate( R.layout.fullscreen_image, parent, false );
        return new FullscreenHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull FullscreenHolder holder, int position) {
        Image image = fullscreenArrayList.get(position);
        with(holder.itemView.getContext())
                .load(image.getGalleryImage())
                .into(holder.fullsizeImage);
    }



    @Override
    public int getItemCount() {
        return fullscreenArrayList.size();
    }

    class FullscreenHolder extends RecyclerView.ViewHolder {

        public ImageView fullsizeImage;

        private FullscreenHolder(View itemView) {
            super( itemView );
            fullsizeImage = itemView.findViewById( R.id.fullsizeImage );
        }
    }

}

