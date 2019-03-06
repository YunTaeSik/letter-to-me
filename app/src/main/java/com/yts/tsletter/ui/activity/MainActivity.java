package com.yts.tsletter.ui.activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.service.JobSchedulerStart;
import com.yts.tsletter.ui.adapter.MainAdapter;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SendBroadcast;
import com.yts.tsletter.utils.SharedPrefsUtils;
import com.yts.tsletter.viewmodel.MainViewModel;
import com.yts.tsletter.R;
import com.yts.tsletter.databinding.MainViewBinding;

import java.util.Calendar;
import java.util.List;

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

        model.setAdRequest();
        model.setCurrentYear(Calendar.getInstance());

        setSupportActionBar(binding.bottomAppBar);
        binding.animationView.playAnimation();
        JobSchedulerStart.start(this);
        observe();
        registerReceiver(broadcastReceiver, getIntentFilter());
    }

    public void observe() {
        model.mCurrentYear.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String year) {
                model.getWriteList(year);
            }
        });

        model.mWriteList.observe(this, new Observer<List<Write>>() {
            @Override
            public void onChanged(List<Write> writes) {
                RecyclerView view = binding.listWrite;
                MainAdapter adapter = (MainAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setWriteList(writes);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    adapter = new MainAdapter(writes);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
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
                Intent setting = new Intent(this, SettingActivity.class);
                startActivity(setting);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(SendBroadcast.SAVE_WRITE) || action.equals(SendBroadcast.DELETE_WRITE)) {
                    if (model != null) {
                        model.refreshWriteList();
                    }
                } else if (action.equals(SendBroadcast.CHANGE_FONT)) {
                    recreate();
                }
            }
        }
    };

    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SendBroadcast.SAVE_WRITE);
        intentFilter.addAction(SendBroadcast.DELETE_WRITE);
        intentFilter.addAction(SendBroadcast.CHANGE_FONT);
        return intentFilter;
    }
}
