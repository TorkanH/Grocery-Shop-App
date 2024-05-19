package com.kansavozer.gulcetorkanbedirhankansavproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.ImageRequest;
//import com.android.volley.toolbox.Volley;
//import org.json.JSONObject;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Groceries> items;
    int pos;
    private int [] imgIds = {R.drawable.fruits, R.drawable.vegetables, R.drawable.dairy};


    public CustomRecyclerAdapter(Context mContext, ArrayList<Groceries> items) {
        this.mContext = mContext;
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = (View) mLayoutInflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder mViewHolder = new ViewHolder(itemView);

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Groceries groceries = items.get(position);

        holder.tvCategory.setText(groceries.getType());
        holder.imgCat.setImageResource(imgIds[position]);
        pos = holder.getAdapterPosition();

        Intent intent = new Intent(mContext, SecondActivity.class);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putParcelable("Object", (Parcelable) groceries);
                b.putInt("pos", pos);
                intent.putExtras(b);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tvCategory;
        ImageView imgCat;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvRecycle);
            imgCat = itemView.findViewById(R.id.imgRecycle);
            parentLayout = itemView.findViewById(R.id.itemLayout);
        }

    }

    private void makeAndShowDialogBox(String message, Drawable dw) {
        AlertDialog.Builder mDialogBox = new AlertDialog.Builder(mContext);
        //mDialogBox.setTitle("Grade Notification for " + course.getCourseCode());
        mDialogBox.setMessage(message);
        mDialogBox.setIcon(dw);
        mDialogBox.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {}
                });
        mDialogBox.create();
        mDialogBox.show();
    }

}
