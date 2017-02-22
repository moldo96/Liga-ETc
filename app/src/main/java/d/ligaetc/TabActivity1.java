package d.ligaetc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabActivity1 extends Fragment {

    public TabActivity1(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.try1, container, false);
        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText("LALLA");
        return view;
    }


}