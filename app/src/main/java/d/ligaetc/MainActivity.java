package d.ligaetc;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
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
        tv1.setText(""+t.checkDate(getApplicationContext()));

        //TabHost tbh1 = findViewById();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        switch (item.getItemId()) {
            case R.id.menu_modificari:
                tv1.setText("S-a apasat modificari");
                showNewDialogBox();
                return true;

            case R.id.menu_sport:
                tv1.setText("S-a apasat sport");
                return true;

            case R.id.menu_teme:
                tv1.setText("S-a apasat teme");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void showDialogBox(String t )
    {
        AlertDialog al = new AlertDialog.Builder(MainActivity.this).create();
        al.setTitle(t);
        al.setMessage("Ce tip de " + t + " doriti?");
        al.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        al.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        al.show();
    }

    private void showNewDialogBox(){
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Modificari");
        dialog.setContentView(R.layout.dialog1);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);
        RadioButton rb1 = (RadioButton) dialog.findViewById(R.id.opt_permanente);
        RadioButton rb2 = (RadioButton) dialog.findViewById(R.id.opt_temporare);
        rg.addView(rb1);
        rg.addView(rb2);
        dialog.show();
    }

}
