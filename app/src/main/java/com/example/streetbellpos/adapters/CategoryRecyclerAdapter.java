package com.example.streetbellpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.models.gson.ProductCategories;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductCategories> mCategortList;
    private mCategoryInterface mInterface;


    public CategoryRecyclerAdapter(Context context, ArrayList<ProductCategories> mCategortList, mCategoryInterface mCategoryInterface) {
        this.context = context;
        this.mCategortList = mCategortList;
        this.mInterface = mCategoryInterface;
    }

    @NonNull
    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ViewHolder holder, int position) {
        holder.mCategoryBtn.setText(mCategortList.get(position).getCatText());

    }

    @Override
    public int getItemCount() {
        return mCategortList.size();
    }

    public interface mCategoryInterface {
        void onCategoryClicked(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button mCategoryBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCategoryBtn = itemView.findViewById(R.id.category_btn);

            mCategoryBtn.setOnClickListener(v -> mInterface.onCategoryClicked(getAdapterPosition()));
        }
    }
}
