package com.beginner.example2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager=(ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new myadapter1(getSupportFragmentManager()));


    }


    public class myadapter1 extends FragmentPagerAdapter {
        public myadapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i) {case 0:return new fragmentNews();
              case 1: return new fragmentMap();
                default: return new fragmentNews();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position){
            if (position==0){return "News";}
            else {return "Map";}
        }

    }

}

