package com.yts.tsletter.ui;

import android.os.Bundle;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.ReadBinding;
import com.yts.tsletter.ui.adapter.ReadAdapter;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.viewmodel.write.WriteViewModel;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReadActivity extends BaseActivity {
    private ReadBinding binding;
    private WriteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);
        model = ViewModelProviders.of(this).get(WriteViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        Write write = getIntent().getParcelableExtra(Keys.WRITE) != null ? (Write) getIntent().getParcelableExtra(Keys.WRITE) : new Write();
        model.setWriteList(write);

        observe();
    }

    public void observe() {
        model.mWriteList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.listRead;
                ReadAdapter adapter = (ReadAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setReadList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    adapter = new ReadAdapter(objects);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }
}
