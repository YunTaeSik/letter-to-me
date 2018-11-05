package com.yts.tsletter.ui;

import android.os.Bundle;

import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.WriteEditBinding;
import com.yts.tsletter.ui.adapter.WriteEditAdapter;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.viewmodel.write.WriteViewModel;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WriteEditActivity extends BaseActivity {

    private WriteEditBinding binding;
    private WriteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write);
        model = ViewModelProviders.of(this).get(WriteViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        Write write = getIntent().getParcelableExtra(Keys.WRITE) != null ? (Write) getIntent().getParcelableExtra(Keys.WRITE) : new Write();
        model.setWriteList(write);
        observe();
    }

    private void observe() {
        model.mWriteList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.listWrite;
                WriteEditAdapter adapter = (WriteEditAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setWriteList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    adapter = new WriteEditAdapter(objects);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }
}
