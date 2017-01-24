package d.ligaetc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.StringWriter;

/**
 * Created by Andrei on 24.01.2017.
 */

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro2);
        final String haha="fssdfs";
        Intent intent = getIntent();
        final TextView year = (TextView)findViewById(R.id.lbl_yearvalue);
        SeekBar seekBar = (SeekBar) findViewById(R.id.skb_year);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                year.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button OKbtn_intro2 = (Button) findViewById(R.id.btn_ok2);
        OKbtn_intro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                String FILENAME = "PROFILE.xml";
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    XmlSerializer xmlSerializer = Xml.newSerializer();
                    StringWriter writer = new StringWriter();
                    xmlSerializer.setOutput(writer);
                    xmlSerializer.startDocument("UTF-8", true);
                    xmlSerializer.startTag(null, "profile");
                    xmlSerializer.attribute("","name",haha);
                    xmlSerializer.endTag(null,"profile");
                    xmlSerializer.endDocument();
                    xmlSerializer.flush();
                    fileOutputStream.write(writer.toString().getBytes());

                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ExitActivity.class);
        this.startActivity(intent);
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        Toast toast = Toast.makeText(getApplicationContext(),""+progress,Toast.LENGTH_SHORT);
        toast.show();
    }

}
