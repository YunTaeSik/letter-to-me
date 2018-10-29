package com.yts.tsletter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.yts.tsletter.databinding.MainViewBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
    }
}
