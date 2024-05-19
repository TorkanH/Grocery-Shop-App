package com.kansavozer.gulcetorkanbedirhankansavproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomCartRecyclerAdapter extends RecyclerView.Adapter<CustomCartRecyclerAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Cart> recyclerItemValues;
    int totP = 0;
    TextView totalPrice;

    DatabaseHelper dbHelper;
    public CustomCartRecyclerAdapter(Context context, ArrayList<Cart> values){
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCartRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Cart cartItem = recyclerItemValues.get(position);

        holder.tvItemName.setText(cartItem.getName());
        holder.tvItemPrice.setText(cartItem.getPrice()+" TL");
        holder.tvItemType.setText(cartItem.getType());


        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean res = CartDB.delete(dbHelper,cartItem.getId()+"");

                if(res) {
                    Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
                    recyclerItemValues.remove(position);
                }else{
                    Toast.makeText(context, "deletion failed", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tvItemName, tvItemPrice, tvItemType;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvItemType = itemView.findViewById(R.id.tvItemType);
            parentLayout = itemView.findViewById(R.id.linearLayout);
        }

    }
}
