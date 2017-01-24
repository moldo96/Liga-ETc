package d.ligaetc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Andrei on 24.01.2017.
 */

public class Try extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try1);
        Intent intent = getIntent();
        TextView tv = (TextView) findViewById(R.id.textView1);
        try{
        FileInputStream fileInputStream = openFileInput("PROFIL.xml");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line="";
            while ((line = bufferedReader.readLine())!=null){
                tv.setText(tv.getText() + line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
