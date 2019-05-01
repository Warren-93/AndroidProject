package project.suzieqcraft.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import project.suzieqcraft.Interfaces.IProduct;
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.R;

import static com.bumptech.glide.Glide.with;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public ArrayList<Product> productProductArrayList;
    private IProduct pListener;

    public CustomAdapter(ArrayList<Product> productProductArrayList, IProduct pListener) {
        this.productProductArrayList = productProductArrayList;
        this.pListener = pListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View itemView = inflater.from( parent.getContext() ).inflate( R.layout.card, parent, false );
        return new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = productProductArrayList.get( position );
        holder.productName.setText( product.getProductType() );
        with( holder.itemView.getContext() )
                .load( product.getProductImage() )
                .into( holder.imageView );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick(holder.productName, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productProductArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public ImageView imageView;

        private ViewHolder(View itemView) {
            super( itemView );
            productName = itemView.findViewById( R.id.productName );
            imageView = itemView.findViewById( R.id.imageView );
        }
    }
}