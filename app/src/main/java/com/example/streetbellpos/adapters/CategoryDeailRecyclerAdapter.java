package com.example.streetbellpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.models.gson.ProductCategories;

import java.util.ArrayList;

public class CategoryDeailRecyclerAdapter extends RecyclerView.Adapter<CategoryDeailRecyclerAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ProductCategories>mCategoryDeailList;
    private mCategoryDeailInterface mCategoryDeailInterface;
    public CategoryDeailRecyclerAdapter(Context context, ArrayList<ProductCategories> mCategoryDeailList, mCategoryDeailInterface mCategoryDeailInterface) {
        this.context = context;
        this.mCategoryDeailList = mCategoryDeailList;
        this.mCategoryDeailInterface = mCategoryDeailInterface;
    }
    @NonNull
    @Override

    public CategoryDeailRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_category_deail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDeailRecyclerAdapter.ViewHolder holder, int position) {
        holder.mCategoryDeailBtn.setText(mCategoryDeailList.get(position).getCatText());

    }


    @Override
    public int getItemCount() {
        return mCategoryDeailList.size();
    }
    public interface mCategoryDeailInterface {
        void onCategoryDeailClicked(int pos);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mCategoryDeailBtn;
                public ViewHolder(View itemview){
            super(itemview);
            mCategoryDeailBtn=itemview.findViewById(R.id.category_deail_btn);
            mCategoryDeailBtn.setOnClickListener(view -> mCategoryDeailInterface.onCategoryDeailClicked(getAdapterPosition()));

                }
    }
}
