package project.suzieqcraft.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.R;
import static com.bumptech.glide.Glide.with;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public ArrayList<Product> productProductArrayList;

    public CustomAdapter(ArrayList<Product> productProductArrayList) {
        this.productProductArrayList = productProductArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productProductArrayList.get(position);
        holder.productName.setText( product.getProductType());
        with(holder.itemView.getContext())
                .load(product.getProductImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productProductArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public ImageView imageView;

        private ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}