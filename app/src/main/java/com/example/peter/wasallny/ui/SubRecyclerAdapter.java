package com.example.peter.wasallny.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.wasallny.R;

import java.util.ArrayList;

class SubRecyclerAdapter extends RecyclerView.Adapter<SubRecyclerAdapter.ViewHolder>{
    AlertDialog.Builder builder;
    Context context;
    MutableLiveData<ArrayList<String>> stationsNames;
    public SubRecyclerAdapter(Context context, MutableLiveData<ArrayList<String>> stationsNames) {
        this.context = context;
        this.stationsNames = stationsNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        builder=new AlertDialog.Builder(context);
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
            viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.tv.setText(stationsNames.getValue().get(i));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String x = stationsNames.getValue().get(i);
                    builder.setMessage(context.getSharedPreferences("metrotrain", Context.MODE_PRIVATE).getString(x, "")).create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
            return stationsNames.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv=itemView.findViewById(R.id.tv);
        }
    }

}
