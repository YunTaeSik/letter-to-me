package com.yts.tsletter.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.ContentAudioItemBinding;
import com.yts.tsletter.databinding.ContentImageItemBinding;
import com.yts.tsletter.databinding.ContentVideoItemBinding;
import com.yts.tsletter.databinding.WriteHeaderItemBinding;
import com.yts.tsletter.viewmodel.write.ContentViewModel;
import com.yts.tsletter.viewmodel.write.WriteHeaderViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WriteEditAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int CONTENT_IMAGE_TYPE = 1;
    private final int CONTENT_VIDEO_TYPE = 2;
    private final int CONTENT_AUDIO_TYPE = 3;

    private List<Object> mWriteList;

    public WriteEditAdapter(List<Object> writeList) {
        mWriteList = writeList;
    }

    public void setWriteList(List<Object> writeList) {
        this.mWriteList = writeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mWriteList.get(position);
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
        Object item = mWriteList.get(position);
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
        WriteHeaderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_write_edit_header, parent, false);
        return new WriteHeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == CONTENT_IMAGE_TYPE) {
            ContentImageViewHolder holder = (ContentImageViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();
            Object item = mWriteList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
            }
            holder.setViewModel(model);
        } else if (viewType == CONTENT_VIDEO_TYPE) {
            ContentVideoViewHolder holder = (ContentVideoViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();
            Object item = mWriteList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
            }
            holder.setViewModel(model);
        } else if (viewType == CONTENT_AUDIO_TYPE) {
            ContentAudioViewHolder holder = (ContentAudioViewHolder) viewHolder;
            ContentViewModel model = new ContentViewModel();
            Object item = mWriteList.get(position);
            if (item instanceof Content) {
                model.setContent((Content) item);
            }
            holder.setViewModel(model);
        } else if (viewType == HEADER_TYPE) {
            WriteHeaderViewHolder holder = (WriteHeaderViewHolder) viewHolder;
            WriteHeaderViewModel model = new WriteHeaderViewModel();
            Object item = mWriteList.get(position);
            if (item instanceof Write) {
                //   model.setUser((User) item);
            }
            holder.setViewModel(model);
        }
    }

    @Override
    public int getItemCount() {
        if (mWriteList != null) {
            return mWriteList.size();
        }
        return 0;
    }

    private class WriteHeaderViewHolder extends RecyclerView.ViewHolder {
        private WriteHeaderItemBinding binding;

        public WriteHeaderViewHolder(@NonNull WriteHeaderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(WriteHeaderViewModel model) {
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

