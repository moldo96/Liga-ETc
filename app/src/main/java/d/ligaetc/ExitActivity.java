package d.ligaetc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrei on 08.01.2017.
 */

public class ExitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit);
        Intent intent = getIntent();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        //android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

}
