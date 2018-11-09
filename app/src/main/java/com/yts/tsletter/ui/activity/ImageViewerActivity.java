package com.yts.tsletter.ui.activity;

import android.os.Bundle;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.databinding.ImageViewerBinding;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.viewmodel.ImageViewerViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

public class ImageViewerActivity extends AppCompatActivity {
    private ImageViewerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_viewer);

        String transName = getIntent().getStringExtra(Keys.TRANS_NAME);
        Content content = getIntent().getParcelableExtra(Keys.CONTENT);

        ImageViewerViewModel model = new ImageViewerViewModel(this, content);
        binding.setModel(model);

        if (content.isImage()) {
            ViewCompat.setTransitionName(binding.imagePhoto, transName);
        }
    }
}
