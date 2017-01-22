package d.ligaetc;

import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {
    AlertDialog alertDialog; String a="";
    ArrayList<Materie> subjectsList;

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
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setLogo(R.drawable.logomic);
        //actionBar.setTitle("Liga ETc");

        //actionBar.setDisplayUseLogoEnabled(true);


        TextView tv1 = (TextView) findViewById(R.id.textView1);
        TimeClass t = new TimeClass();
        TimetableClass tc = new TimetableClass();
        tv1.setText("");
        tv1.setText(tv1.getText() +" " +t.checkDate(getApplicationContext()));
        tv1.setText(t.localdayFormat(Calendar.getInstance()));
        tv1.setText("" + t.getWeekOfFoundPeriod());
        subjectsList = tc.OpenTimetable(getApplicationContext(), t.dayExtractor());
        ArrayAdapter<Materie> adapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.list_materii);
        listView.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<Materie>{
        private MyListAdapter(){
            super(MainActivity.this,R.layout.singlerow_list1, subjectsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.singlerow_list1, parent, false);
            }
            //Find materie
            Materie currentMaterie = subjectsList.get(position);

            // Fill the view
            TextView tv = (TextView)itemView.findViewById(R.id.txt_ora);
            tv.setText(currentMaterie.getOra_i());
            TextView textView1 = (TextView)itemView.findViewById(R.id.txt_numematerie);
            textView1.setText(currentMaterie.getNume());
            TextView textView3 = (TextView)itemView.findViewById(R.id.txt_prof);
            textView3.setText(currentMaterie.getProf());
            ImageView imgview = (ImageView)itemView.findViewById(R.id.img_prof);
            TextView t3 = (TextView)itemView.findViewById(R.id.txt_tip);
            t3.setText(currentMaterie.getTip());
            TextView t4 = (TextView)itemView.findViewById(R.id.txt_sala);
            t4.setText("A106");
            //imgview.setImageDrawable(getResources().getDrawable(R.drawable.stoiciu));
            //Context context = getApplicationContext();
            //String estring = currentMaterie.getProf();
            ///estring = estring.substring(2);
            //estring = estring.toLowerCase();
            //textView1.setText(estring);
            //imgview.setImageResource(getPictureID(estring,context));
                    //getResources().getDrawable(getPictureID(estring,context)));

            //ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView0);
            //imgview.setImageResource(getPictureID(estring,context));

            return itemView;
        }
    }

    private int getPictureID(String estring, Context context){
        return context.getResources().getIdentifier(estring, "drawable",context.getApplicationInfo().packageName);
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
