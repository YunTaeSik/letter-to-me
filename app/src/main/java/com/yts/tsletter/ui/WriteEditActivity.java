package com.yts.tsletter.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.yalantis.ucrop.UCrop;
import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.callback.WriteEditCallback;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.WriteEditBinding;
import com.yts.tsletter.ui.adapter.WriteEditAdapter;
import com.yts.tsletter.utils.Convert;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.RequestCode;
import com.yts.tsletter.utils.ShowIntent;
import com.yts.tsletter.viewmodel.write.WriteEditViewModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.functions.Consumer;

public class WriteEditActivity extends BaseActivity implements WriteEditCallback {

    private WriteEditBinding binding;
    private WriteEditViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_edit);
        model = ViewModelProviders.of(this).get(WriteEditViewModel.class);
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
                    adapter = new WriteEditAdapter(objects, WriteEditActivity.this);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            if (requestCode == RequestCode.IMAGE_SELECT) {
                ShowIntent.imageCroup(this, data, RequestCode.IMAGE_CROP);
            } else if (requestCode == RequestCode.IMAGE_CROP) {
                model.addImage(UCrop.getOutput(data).getPath());
            } else if (requestCode == RequestCode.VIDEO_SELECT) {
                Uri uri = data.getData();
                if (model != null) {
                    model.isLoading.setValue(true);
                    mCompositeDisposable.add(Convert.contentUriToVideo(this, uri).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String path) throws Exception {
                            if (model != null) {
                                model.addVideo(path);
                                model.isLoading.setValue(false);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    }));
                }
            } else if (requestCode == RequestCode.AUDIO_SELECT) {
                Uri uri = data.getData();
                if (model != null) {
                    model.isLoading.setValue(true);
                    mCompositeDisposable.add(Convert.contentUriToAudio(this, uri).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String path) throws Exception {
                            if (model != null) {
                                model.addVideo(path);
                                model.isLoading.setValue(false);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    }));
                }
            }
        }
    }

    @Override
    public void onReceiveDateChange() {
        if (model != null) {
            model.receiveDateChange(this);
        }
    }

    @Override
    public void onTitleChange(String title) {
        if (model != null) {
            model.changeTitle(title);
        }
    }

    @Override
    public void onTextChange(String text) {
        if (model != null) {
            model.changeText(text);
        }
    }

    @Override
    public void onContentTextChange(int position, String text) {
        if (model != null) {
            model.changeContentText(position, text);
        }
    }
}
