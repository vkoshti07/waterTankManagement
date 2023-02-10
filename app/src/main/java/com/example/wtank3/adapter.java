package com.example.wtank3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wtank3.model.responseModel;


import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    List<responseModel> list;

    public adapter(List<responseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
        return  new ViewHolder(view);
      //  View view = LayoutInflater.from(context).inflate(R.layout.category,parent,false);
       // return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        responseModel model = list.get(position);
        holder.Id.setText(model.getId());
        holder.sensor.setText(model.getSensor());
        holder.location.setText(model.getLocation());
        holder.value1.setText(model.getValue1());
        holder.value2.setText(model.getValue2());
        holder.value3.setText(model.getValue3());
        holder.reading_time.setText(model.getReading_time());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Id,sensor,location,value1,value2,value3,reading_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Id = itemView.findViewById(R.id.dataId);
            sensor = itemView.findViewById(R.id.dataStatus);
            location = itemView.findViewById(R.id.dataLocation);
            value1= itemView.findViewById(R.id.dataLow);
            value2 = itemView.findViewById(R.id.dataMiddle);
            value3 = itemView.findViewById(R.id.dataHigh);
            reading_time = itemView.findViewById(R.id.dataTimeStamp);
        }
    }
}
