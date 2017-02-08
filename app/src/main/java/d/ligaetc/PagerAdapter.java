package d.ligaetc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Andrei on 08.02.2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabActivity1 tabActivity1 = new TabActivity1();
                return tabActivity1;
            case 1:
                TabActivity1 tabActivity2 = new TabActivity1();
                return tabActivity2;
            case 2:
                TabActivity1 tabActivity3 = new TabActivity1();
                return tabActivity3;
        }

        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
