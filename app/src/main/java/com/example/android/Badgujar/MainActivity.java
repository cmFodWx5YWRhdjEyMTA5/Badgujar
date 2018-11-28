package com.example.android.Badgujar;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.util.Log;

import com.example.android.Badgujar.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    CustomViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    int currentPage = 0;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        if (actionbar !=null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        // NavigationView click events
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);

                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();


                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                try {
                    Toast.makeText(MainActivity.this, "Navigation click events", Toast.LENGTH_SHORT).show();
                } catch(NullPointerException e) {
                    Log.e(LOG_TAG, "Problem with onNavigationItemSelected", e);
                }

                return true;
            }
        });

        // Create an ArrayList of badgujar objects
        ArrayList<badgujar> badgujars = new ArrayList<badgujar>();

        {
            badgujars.add(new badgujar(R.drawable.sample_0, "News"));
            badgujars.add(new badgujar(R.drawable.sample_1, "Matrimonial"));
            badgujars.add(new badgujar(R.drawable.sample_2, "Directory"));
            badgujars.add(new badgujar(R.drawable.sample_3, "Business"));
            badgujars.add(new badgujar(R.drawable.sample_4, "Samaj Mandal"));
            badgujars.add(new badgujar(R.drawable.sample_5, "Helping Hand"));
            badgujars.add(new badgujar(R.drawable.sample_6, "History"));
            badgujars.add(new badgujar(R.drawable.sample_7, "About us"));
            badgujars.add(new badgujar(R.drawable.sample_0, "Contact us"));

        }

        // Create an {@link badgujarAdapter}, whose data source is a list of
        // {@link badgujar}s. The adapter knows how to create list item views for each item
        // in the list

        badgujarAdapter adapter = new badgujarAdapter(this, badgujars);

        // Get a reference to the GridView, and attach the adapter to the gridView.
        GridView badgujarGridView = (GridView) findViewById(R.id.gridView);
        badgujarGridView.setAdapter(adapter);

        badgujarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            final Intent intent;
            switch(position)
            {
                case 0:
                intent = new Intent(MainActivity.this, NewsActivity.class);
                break;

                case 1:
                    intent = new Intent(MainActivity.this, NewsActivity.class);
                    break;

                case 2:
                    intent = new Intent(MainActivity.this, MatrimonialActivity.class);
                    break;

                case 3:
                    intent = new Intent(MainActivity.this, DirectoryActivity.class);
                    break;

                case 4:
                    intent = new Intent(MainActivity.this, BusinessActivity.class);
                    break;

                case 5:
                    intent = new Intent(MainActivity.this, SamajMandalActivity.class);
                    break;

                case 6:
                    intent = new Intent(MainActivity.this, HelpingHandActivity.class);
                    break;

                case 7:
                    intent = new Intent(MainActivity.this, HistoryActivity.class);
                    break;

                case 8:
                    intent = new Intent(MainActivity.this, AboutusActivity.class);
                    break;
                default:
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
            }

        });





        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        ViewPagerApdater viewPagerAdapter = new ViewPagerApdater(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Auto start of viewpager

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {

            public void run() {

                if (currentPage == 5 ) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 4000, 4000);

        // Displaying dots indicator
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {

                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
        }
}