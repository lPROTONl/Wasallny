package com.example.peter.wasallny.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.wasallny.R;

class SubRecyclerAdapter extends RecyclerView.Adapter<SubRecyclerAdapter.ViewHolder>{
    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.context);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
            viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.tv.setText(StationsViewModel.stationsNames.getValue().get(i));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String x = StationsViewModel.stationsNames.getValue().get(i);
                    builder.setMessage(MainActivity.context.getSharedPreferences("metrotrain", Context.MODE_PRIVATE).getString(x, "")).create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
            return StationsViewModel.stationsNames.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv=itemView.findViewById(R.id.tv);
        }
    }

}
