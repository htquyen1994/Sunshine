package com.example.admin.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolderz> {

    private String[] mWeatherData;
    interface ForecastAdapterOnClickHandler{
        public void onClick(String parameter);
    }
    private ForecastAdapterOnClickHandler mClickHandler;

    public ForecastAdapter(ForecastAdapterOnClickHandler callback){
        mClickHandler = callback;
    }

    @Override
    public ForecastAdapterViewHolderz onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = layoutInflater.inflate(R.layout.forecast_list_item, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolderz(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolderz forecastAdapterViewHolderz, int i) {
        String weatherForThisDay = mWeatherData[i];
        forecastAdapterViewHolderz.mWeatherTextView.setText(weatherForThisDay);
    }

    @Override
    public int getItemCount() {
        if (null == mWeatherData) return 0;
        return mWeatherData.length;
    }

    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    public class ForecastAdapterViewHolderz extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolderz(View itemView) {
            super(itemView);
            mWeatherTextView = (TextView)itemView.findViewById(R.id.tv_weather_data) ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String data = mWeatherData[position];
            mClickHandler.onClick(data);
        }
    }
}
