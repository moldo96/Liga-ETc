package d.ligaetc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Andrei on 08.01.2017.
 */

public class ExitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit);
        Intent intent = getIntent();
        createImage();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    private void createImage(){
        Random random = new Random();
        ImageView img = (ImageView) findViewById(R.id.img_exit);
        String estring = "engineer" + random.nextInt(5);
        Context context = getApplicationContext();
        img.setImageDrawable(
                getResources().getDrawable(getPictureID(estring,context))
        );
    }

    private int getPictureID(String estring, Context context){
        return context.getResources().getIdentifier(estring, "drawable",context.getApplicationInfo().packageName);
    }

}
