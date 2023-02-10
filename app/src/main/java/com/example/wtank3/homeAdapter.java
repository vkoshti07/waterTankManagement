package com.example.wtank3;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wtank3.model.homeModel;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder> {

    Context context;
    ArrayList<homeModel> list;

    public homeAdapter(Context context, ArrayList<homeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.home_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        homeModel model = list.get(position);

            holder.area.setText(model.getArea());
            if(position==0) {
                holder.area.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, katargam.class);
                        context.startActivity(intent);

                    }
                });
            }
         if(position==1) {
            holder.area.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,majuragate.class);
                    context.startActivity(intent);

                }
            });
        }
         if(position==2) {
            holder.area.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Athvagate.class);
                    context.startActivity(intent);

                }
            });
        }

        if(position==3) {
            holder.area.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView area;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            area=itemView.findViewById(R.id.area);
        }
    }
}
