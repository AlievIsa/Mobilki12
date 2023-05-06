package com.example.mobilki12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mobilki12.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (id == R.id.audio) {
                    replaceFragment(new AudioFragment());
                } else if (id == R.id.clips) {
                    replaceFragment(new ClipsFragment());
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (binding.bottomNavigationView.getSelectedItemId() != R.id.home) {
            replaceFragment(new HomeFragment());
            binding.bottomNavigationView.setSelectedItemId(R.id.home);
        }
        else {
            finish();
        }
    }
}