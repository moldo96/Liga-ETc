package d.ligaetc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
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
        Intent intent = getIntent();
        final TextView year = (TextView)findViewById(R.id.lbl_yearvalue);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.skb_year);

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
            public void onClick (View view) {
                createProfileXml(seekBar);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ExitActivity.class);
        this.startActivity(intent);
    }

    private void createProfileXml(SeekBar seekBar){
        EditText editText = (EditText)findViewById(R.id.txt_username);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_group);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_subgroup);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        try {
            FileOutputStream fileOutputStream = openFileOutput("PROFIL.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "profil");
            xmlSerializer.attribute("", "nume",editText.getText().toString());
            xmlSerializer.attribute("", "an","" + seekBar.getProgress());
            xmlSerializer.attribute("", "G", spinner1.getSelectedItem().toString());
            xmlSerializer.attribute("", "g", spinner2.getSelectedItem().toString());
            xmlSerializer.attribute("", "serie", ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
            xmlSerializer.endTag(null,"profil");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            fileOutputStream.write(writer.toString().getBytes());
            fileOutputStream.close();
            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
