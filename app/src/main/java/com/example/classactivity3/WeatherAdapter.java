package com.example.classactivity3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    // create basic adapter from RecyclerView.Adapter
    // create inner/helper class that specify the custom ViewHolder which gives us access to our views
    private List<Weather> weathers;

    // pass the list to the constructor of the adaptor;
    public WeatherAdapter(List<Weather> weathers){
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // used to inflate layout from xml and return viewHolder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View weatherView = inflater.inflate(R.layout.item_weather, parent, false);
        // return new ViewHolder
        ViewHolder viewHolder = new ViewHolder(weatherView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to populate data into the item through viewHolder

        // grab the actual data model (aka Weather Object) based on the position
        Weather weather = weathers.get(position);

        // set the view based on the data
        holder.textView_date.setText(weather.getDate());
        holder.textView_time.setText(weather.getTime());
        holder.textView_description.setText(weather.getDescription());
        holder.textView_temp.setText(weather.getTemp());
    }

    @Override
    public int getItemCount() {
        // returns the total number of items in a list
        return weathers.size();
    }

    // provides direct reference to each of the views within the data item
    // used to cache the views within the item layout for fast access
    class ViewHolder extends RecyclerView.ViewHolder{
        // should hold all the views you want to set when you render the row
        // date, time, description, temp
        TextView textView_date;
        TextView textView_time;
        TextView textView_description;
        TextView textView_temp;

        // constructor
        public ViewHolder(View itemView) {
            // itemView represents the entire view of each row
            super(itemView);
            // look up each view from custom layout
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_time = itemView.findViewById(R.id.textView_time);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_temp = itemView.findViewById(R.id.textView_temp);
        }
    }
}
