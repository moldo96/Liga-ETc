package d.ligaetc;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Andrei on 06.02.2017.
 */

public class TemporaryJob extends AppCompatActivity{
    String a;
    AlertDialog alertDialog;
    private SectionsPagerAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mSectionPageAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.azi);
                case 1:
                    return getResources().getString(R.string.maine);
                case 2:
                    return getResources().getString(R.string.poimaine);
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.try1, container, false);

            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_modificari:
                showDialogBox("Modificari");
                return true;

            case R.id.menu_sport:
                Snackbar.make(findViewById(R.id.container),"S-a apasat sport",Snackbar.LENGTH_SHORT)
                .show();
                return true;

            case R.id.menu_teme:
                Snackbar.make(findViewById(R.id.container),"S-a apasat teme",Snackbar.LENGTH_SHORT)
                .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showDialogBox(String t) {
        a = getResources().getStringArray(R.array.changes_array)[0];
        AlertDialog.Builder builder = new AlertDialog.Builder(TemporaryJob.this);
        builder.setTitle(t);
        builder.setSingleChoiceItems(R.array.changes_array, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(which == 1)
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
}
