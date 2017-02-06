package d.ligaetc;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

/**
 * Created by Andrei on 06.02.2017.
 */

public class TemporaryJob extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("today").setIndicator("Azi").setContent(new Intent(this, TabActivity1.class)));
        tabHost.addTab(tabHost.newTabSpec("tomorrow").setIndicator("Maine").setContent(new Intent(this, TabActivity1.class)));
    }

}
