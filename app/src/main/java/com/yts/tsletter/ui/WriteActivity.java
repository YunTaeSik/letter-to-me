package com.yts.tsletter.ui;

import android.os.Bundle;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.databinding.WriteBinding;
import com.yts.tsletter.viewmodel.WriteViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class WriteActivity extends BaseActivity {

    private WriteBinding binding;
    private WriteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write);
        model = ViewModelProviders.of(this).get(WriteViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
    }
}
