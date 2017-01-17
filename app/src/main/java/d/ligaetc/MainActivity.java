package d.ligaetc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    AlertDialog alertDialog; String a="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try1);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.setTitle("Liga ETc");
        //myToolbar.setTitleTextAppearance(getApplicationContext(),);

        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setLogo(R.drawable.logomic);
        //actionBar.setTitle("Liga ETc");

        //actionBar.setDisplayUseLogoEnabled(true);


        TextView tv1 = (TextView) findViewById(R.id.textView1);
        TimeClass t = new TimeClass();
        tv1.setText("");
        tv1.setText(tv1.getText() +" " +t.checkDate(getApplicationContext()));
        String[] er = {"pupp", "lfsldfs"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.singlerow_list1, er);
        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ExitActivity.class);
        this.startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        a = getResources().getStringArray(R.array.changes_array)[0];
        switch (item.getItemId()) {
            case R.id.menu_modificari:
                tv1.setText("S-a apasat modificari");
                showDialogBox("Modificari");

                return true;

            case R.id.menu_sport:
                tv1.setText("S-a apasat sport");
                return true;

            case R.id.menu_teme:
                tv1.setText("S-a apasat adaugare/editare");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void CreateActionBar(){

    }

    private void showDialogBox(String t )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(t);
        builder.setSingleChoiceItems(R.array.changes_array,0,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                if(which == 1)
                    a = getResources().getStringArray(R.array.changes_array)[1];
            }
        });
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Toast toast = Toast.makeText(getApplicationContext(), "You clicked " + a, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
