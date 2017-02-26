package d.ligaetc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Andrei on 06.02.2017.
 */

public class IntroActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Button OKbtn_intro = (Button) findViewById(R.id.btn_ok);
        OKbtn_intro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup_student);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButtonChecked = radioGroup.findViewById(radioButtonID);
                int index = radioGroup.indexOfChild(radioButtonChecked);
                RadioButton r = (RadioButton) radioGroup.getChildAt(index);
                String text = r.getText().toString();
                if(text.startsWith("Student")) {
                    Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
