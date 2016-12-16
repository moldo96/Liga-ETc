package d.ligaetc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try1);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar a = getSupportActionBar();
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.logomic);
        getSupportActionBar().setTitle("Liga ETc");
        //getSupportActionBar().setDisplayUseLogoEnabled(true);


        TextView tv1 = (TextView) findViewById(R.id.textView1);
        TimeClass t = new TimeClass();
        //tv1.setText(t.checkDay(getApplicationContext()));
        tv1.setText(""+t.dayExtractor());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
