package com.example.admin.sunshine;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.zip.Inflater;

public class DetailActivity extends AppCompatActivity {

    private final static String TAG = DetailActivity.class.getName();
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private String mForecast;
    private TextView mWeatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = (TextView)findViewById(R.id.tv_display_weather);
        Intent pushItent = getIntent();
        if (pushItent != null){
            if (pushItent.hasExtra(Intent.EXTRA_TEXT)){
                mForecast = pushItent.getStringExtra(Intent.EXTRA_TEXT);
                mWeatherDisplay.setText(mForecast);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }

    private Intent createShareForecastIntent(){
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setText(mForecast + FORECAST_SHARE_HASHTAG)
                .setType("text/plain")
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivity);
        }
        return super.onOptionsItemSelected(item);
    }
}
