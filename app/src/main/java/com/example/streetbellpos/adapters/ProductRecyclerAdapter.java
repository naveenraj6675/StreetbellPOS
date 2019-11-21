package com.example.streetbellpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.managers.GlideManager;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.options.GLIDE_TRANSFORM_OPTION;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Products> mProductList;
    private ProductInterface mInterface;

    public ProductRecyclerAdapter(Context context, ArrayList<Products> mProductList, ProductInterface mInterface) {
        this.context = context;
        this.mProductList = mProductList;
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ViewHolder holder, int position) {
        GlideManager.loadImage(context, holder.mProductIV, mProductList.get(position).getImageList().get(0).getUrl(), GLIDE_TRANSFORM_OPTION.NO_TRANSFORM);

        holder.mPriceTV.setText("\u20B9 " + mProductList.get(position).getPrice());
        holder.mNameTV.setText(mProductList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public interface ProductInterface {
        void onProductClicked(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mProductIV;
        TextView mPriceTV, mNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mProductIV = itemView.findViewById(R.id.product_iv);
            mPriceTV = itemView.findViewById(R.id.price_tv);
            mNameTV = itemView.findViewById(R.id.name_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInterface.onProductClicked(getAdapterPosition());
                }
            });

        }
    }
}
