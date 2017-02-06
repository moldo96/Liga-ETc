package d.ligaetc;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Andrei on 06.02.2017.
 */

public class TemporaryJob extends TabActivity implements TabHost.OnTabChangeListener {
    private TabHost tabHost;
    private LocalActivityManager localActivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("today").setIndicator(getResources().getString(R.string.azi)).setContent(new Intent(this, TabActivity1.class)));
        tabHost.addTab(tabHost.newTabSpec("tomorrow").setIndicator(getResources().getString(R.string.maine)).setContent(new Intent(this, TabActivity1.class)));
        tabHost.addTab(tabHost.newTabSpec("in2days").setIndicator(getResources().getString(R.string.poimaine)).setContent(new Intent(this, TabActivity1.class)));
        for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Intent intent = new Intent(this, ExitActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast t = Toast.makeText(getApplicationContext(), "onrestart", Toast.LENGTH_SHORT);
        t.show();
    }


    @Override
    public void onTabChanged(String s) {
        for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
    }
}
