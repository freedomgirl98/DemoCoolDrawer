package sg.edu.rp.c346.id19036308.democooldrawer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);
         drawerItems = new String[] {"Bio", "Vaccination", "Anniversary"};
         ab = getSupportActionBar();
         aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,drawerItems);

         drawerList.setAdapter(aa);

         // Set the list's click listener
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                if (i == 0) {
                    fragment = new BioFragment();
                } else if (i == 1) {
                    fragment = new VaccinationFragment();
                } else if (i == 2) {
                    fragment = new AnniversaryFragment();
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.content_frame, fragment);
                trans.commit();

                // Highlight the selected item,
                // update the title, and close the drawer
                drawerList.setItemChecked(i, true);
                currentTitle = drawerItems[i];
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }

        });

            currentTitle = this.getTitle().toString();

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
            {
                @Override
                public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

                @Override
                public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
            };

                drawerLayout.addDrawerListener(drawerToggle);
                ab.setDisplayHomeAsUpEnabled(true);
        }

        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            MainActivity.super.onPostCreate(savedInstanceState);
            drawerToggle.syncState();
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig){
            MainActivity.super.onConfigurationChanged(newConfig);
            drawerToggle.onConfigurationChanged(newConfig);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            if (drawerToggle.onOptionsItemSelected(item)){
                return true;

            }
            return  MainActivity.super.onOptionsItemSelected(item);
        }
    }
