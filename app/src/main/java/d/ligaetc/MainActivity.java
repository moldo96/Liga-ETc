package d.ligaetc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    AlertDialog alertDialog; String a="";
    ArrayList<Materie> subjectsList;
    //TimeClass t = new TimeClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        createToolBar();
        Button btnAzi = (Button) findViewById(R.id.btnazi);
        Button btnMaine = (Button) findViewById(R.id.btnmaine);
        Button btnPoimaine = (Button) findViewById(R.id.btnpoimaine);
        btnAzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsList = new TimeClass().checkDate(getApplicationContext(), 0);
                createAdapter();
            }
        });
        btnMaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsList = new TimeClass().checkDate(getApplicationContext(), 1);
                createAdapter();
            }
        });
        btnPoimaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsList = new TimeClass().checkDate(getApplicationContext(), 2);
                createAdapter();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ExitActivity.class);
        this.startActivity(intent);
    }

    private class MyListAdapter extends ArrayAdapter<Materie> {
        //TimeClass t;

        private MyListAdapter(/*TimeClass timeClass*/) {
            super(MainActivity.this, R.layout.singlerow_list1, subjectsList);
            //t = timeClass;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.singlerow_list1, parent, false);
            }
            //Find materie
            Materie currentMaterie = subjectsList.get(position);
            //if(t.getHourDifference(currentMaterie.getOra_i(), currentMaterie.getOra_f())!="A TRECUT") {
            TextView tv = (TextView) itemView.findViewById(R.id.txt_ora);
            //TimeClass t = new TimeClass();
            tv.setText(currentMaterie.getAdditionalCommentString());
            //tv.setText(t.getHourDifference(currentMaterie.getOra_i(), currentMaterie.getOra_f()));
            //tv.setText(""+t.dayExtractor());
            TextView textView1 = (TextView) itemView.findViewById(R.id.txt_numematerie);
            textView1.setText(currentMaterie.getNume());
            TextView textView3 = (TextView) itemView.findViewById(R.id.txt_prof);
            textView3.setText(currentMaterie.getProf());
            ImageView imgview = (ImageView) itemView.findViewById(R.id.img_prof);
            TextView t3 = (TextView) itemView.findViewById(R.id.txt_tip);
            t3.setText(currentMaterie.getTip());
            TextView t4 = (TextView) itemView.findViewById(R.id.txt_sala);
            t4.setText("A106");
            //imgview.setImageDrawable(getResources().getDrawable(R.drawable.blaj));
            Context context = getApplicationContext();
            String estring = "d.stoiciu";
            estring = estring.substring(2);
            estring = estring.toLowerCase();
            textView1.setText(estring);
            //imgview.setImageResource(getPictureID(estring, context));
            //getResources().getDrawable(getPictureID(estring, context));

            //ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView0);
            //imgview.setImageResource(getPictureID(estring,context));

            return itemView;
            //}
            //return null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_modificari:
                showDialogBox("Modificari");
                return true;

            case R.id.menu_sport:
                Toast.makeText(getApplicationContext(),"S-a apasat sport",Toast.LENGTH_SHORT).show();

                return true;

            case R.id.menu_teme:
                Toast.makeText(getApplicationContext(),"S-a apasat teme",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_deconectare:
                //Toast.makeText(getApplicationContext(),"S-a apasat deconectare",Toast.LENGTH_SHORT).show();
                conectare();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showDialogBox(String t) {
        a = getResources().getStringArray(R.array.changes_array)[0];
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(t);
        builder.setSingleChoiceItems(R.array.changes_array, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1)
                    a = getResources().getStringArray(R.array.changes_array)[1];
            }
        });
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(), "You clicked " + a, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    void createToolBar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main2_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

    }

    void createAdapter(){
        ArrayAdapter<Materie> adapter = new MainActivity.MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.list_materii);
        listView.setAdapter(adapter);
    }
void conectare()
{
    Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
    startActivity(intent);
}
}
