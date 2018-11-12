package com.yts.tsletter.ui.activity;

import android.os.Bundle;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.databinding.SettingBinding;
import com.yts.tsletter.viewmodel.SettingViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class SettingActivity extends BaseActivity {
    private SettingBinding binding;
    private SettingViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        model = ViewModelProviders.of(this).get(SettingViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        model.setVersion(this);
    }
}
