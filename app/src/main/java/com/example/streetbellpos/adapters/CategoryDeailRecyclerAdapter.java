package com.example.streetbellpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.models.gson.ProductCategories;

import java.util.ArrayList;

public class CategoryDeailRecyclerAdapter extends RecyclerView.Adapter<CategoryDeailRecyclerAdapter.ViewHolder>{
    private Context context;
    private int selectedPos;
    private ArrayList<ProductCategories>mCategoryDeailList;
    private mCategoryDeailInterface mCategoryDeailInterface;

    public CategoryDeailRecyclerAdapter(Context context, int selectedPos, ArrayList<ProductCategories> mCategoryDeailList, mCategoryDeailInterface mCategoryDeailInterface) {
        this.context = context;
        this.mCategoryDeailList = mCategoryDeailList;
        this.selectedPos = selectedPos;
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

        if (selectedPos == position) {
            holder.mCategoryDeailBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_corner_curved_black));
            holder.mCategoryDeailBtn.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else {
            holder.mCategoryDeailBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_corner_curved_gray));
            holder.mCategoryDeailBtn.setTextColor(ContextCompat.getColor(context, R.color.after_grey));
        }


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
        Button mCategoryDeailBtn;
                public ViewHolder(View itemview){
                    super(itemview);
                    mCategoryDeailBtn = itemview.findViewById(R.id.category_deail_btn);
                    mCategoryDeailBtn.setOnClickListener(view -> mCategoryDeailInterface.onCategoryDeailClicked(getAdapterPosition()));


                    mCategoryDeailBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int oldpos = selectedPos;
                            selectedPos = getAdapterPosition();
                            notifyItemChanged(oldpos);
                            notifyItemChanged(selectedPos);

                            mCategoryDeailInterface.onCategoryDeailClicked(getAdapterPosition());
                        }
                    });


                }
    }
}
