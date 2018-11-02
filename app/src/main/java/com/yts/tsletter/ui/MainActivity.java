package com.yts.tsletter.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.viewmodel.MainViewModel;
import com.yts.tsletter.R;
import com.yts.tsletter.databinding.MainViewBinding;

public class MainActivity extends BaseActivity {

    private MainViewBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.bottomAppBar);
        binding.animationView.playAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_settings:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
