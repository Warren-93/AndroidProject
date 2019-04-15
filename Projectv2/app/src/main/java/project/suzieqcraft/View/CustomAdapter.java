package project.suzieqcraft.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import project.suzieqcraft.Model.ProductArrayList;
import project.suzieqcraft.Model.Product;
import project.suzieqcraft.R;
import static com.bumptech.glide.Glide.with;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;



    CustomAdapter(Context context, List<Product> productProductArrayList) {
        this.context = context;
//        this.productProductArrayList = productProductArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( context );
        //View itemView = inflater.inflate(R.layout.card, null);

        View itemView = inflater.from( context ).inflate( R.layout.card, parent, false );
        //View itemView = inflater.from(parent.getContext()).inflate(R.layout.activity_test,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private ImageView imageView;

        private ViewHolder(View itemView) {
            super( itemView );
            productName = itemView.findViewById( R.id.productName );
            imageView = itemView.findViewById( R.id.imageView );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.productName.setText( product.getProductList().toString());
//        with(context).load(product).into(holder.imageView);
    }
}
