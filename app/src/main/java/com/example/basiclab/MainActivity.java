package com.example.basiclab;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rnd = new Random();
                View someView = view.getRootView();
                someView.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String msg = "";

        switch(id){
            case R.id.action_settings: msg = "Łustawienia"; break;
            case R.id.action_destruction: msg = "sudo rm -fr /*"; break;
            case R.id.action_osiemGwiazd: msg = "Jeb*ć PiS"; break;
            case R.id.action_first: NavHostFragment.findNavController(getVisibleFragment())
                    .navigate(R.id.action_global_FirstFragment);
                msg = "First Fragment";
                break;
            case R.id.action_second: NavHostFragment.findNavController(getVisibleFragment())
                    .navigate(R.id.action_global_SecondFragment);
                msg = "Second Fragment";
                break;
            case R.id.action_third: NavHostFragment.findNavController(getVisibleFragment())
                    .navigate(R.id.action_global_thirdFragment);
                msg = "Third Fragment";
                break;
        }

        Snackbar.make(findViewById(R.id.rootLayout),msg, Snackbar.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
}