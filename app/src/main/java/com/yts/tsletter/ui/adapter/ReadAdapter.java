package com.yts.tsletter.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.ContentAudioItemBinding;
import com.yts.tsletter.databinding.ContentImageItemBinding;
import com.yts.tsletter.databinding.ContentVideoItemBinding;
import com.yts.tsletter.databinding.ReadHeaderItemBinding;
import com.yts.tsletter.viewmodel.write.ContentViewModel;
import com.yts.tsletter.viewmodel.write.ReadHeaderViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ReadAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int CONTENT_IMAGE_TYPE = 1;
    private final int CONTENT_VIDEO_TYPE = 2;
    private final int CONTENT_AUDIO_TYPE = 3;

    private List<Object> mReadList;

    public ReadAdapter(List<Object> readList) {
        mReadList = readList;
    }

    public void setReadList(List<Object> readList) {
        mReadList = readList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mReadList.get(position);
        if (item instanceof Content) {
            Content content = (Content) item;
            if (content.isImage()) {
                return CONTENT_IMAGE_TYPE;
            } else if (content.isVideo()) {
                return CONTENT_VIDEO_TYPE;
            }
            return CONTENT_AUDIO_TYPE;
        } else {
            return HEADER_TYPE;
        }
    }

    @Override
    public long getItemId(int position) {
        Object item = mReadList.get(position);
        if (item instanceof Write) {
            return ((Write) item).getDate();
        } else {
            return position;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CONTENT_IMAGE_TYPE) {
            ContentImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_content_image, parent, false);
            return new ContentImageViewHolder(binding);
        } else if (viewType == CONTENT_VIDEO_TYPE) {
            ContentVideoItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_content_video, parent, false);
            return new ContentVideoViewHolder(binding);
        } else if (viewType == CONTENT_AUDIO_TYPE) {
            ContentAudioItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_content_audio, parent, false);
            return new ContentAudioViewHolder(binding);
        }
        ReadHeaderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_read_header, parent, false);
        return new ReadHeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == CONTENT_IMAGE_TYPE) {
            ContentImageViewHolder holder = (ContentImageViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();

            Object item = mReadList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
                model.setPosition(position);
            }
            holder.setViewModel(model);
        } else if (viewType == CONTENT_VIDEO_TYPE) {
            ContentVideoViewHolder holder = (ContentVideoViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();

            Object item = mReadList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
                model.setPosition(position);
            }
            holder.setViewModel(model);
        } else if (viewType == CONTENT_AUDIO_TYPE) {
            ContentAudioViewHolder holder = (ContentAudioViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();

            Object item = mReadList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
                model.setPosition(position);
            }
            holder.setViewModel(model);
        } else if (viewType == HEADER_TYPE) {
            ReadHeaderViewHolder holder = (ReadHeaderViewHolder) viewHolder;
            ReadHeaderViewModel model = new ReadHeaderViewModel();

            Object item = mReadList.get(position);
            if (item instanceof Write) {
                model.setWrite((Write) item);
            }
            holder.setViewModel(model);
        }
    }

    @Override
    public int getItemCount() {
        if (mReadList != null) {
            return mReadList.size();
        }
        return 0;
    }

    private class ReadHeaderViewHolder extends RecyclerView.ViewHolder {
        private ReadHeaderItemBinding binding;

        public ReadHeaderViewHolder(@NonNull ReadHeaderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(ReadHeaderViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    private class ContentImageViewHolder extends RecyclerView.ViewHolder {
        private ContentImageItemBinding binding;

        public ContentImageViewHolder(@NonNull ContentImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(ContentViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    private class ContentVideoViewHolder extends RecyclerView.ViewHolder {
        private ContentVideoItemBinding binding;

        public ContentVideoViewHolder(@NonNull ContentVideoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(ContentViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    private class ContentAudioViewHolder extends RecyclerView.ViewHolder {
        private ContentAudioItemBinding binding;

        public ContentAudioViewHolder(@NonNull ContentAudioItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(ContentViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}