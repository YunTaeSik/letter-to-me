package com.yts.tsletter.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.MainItemBinding;
import com.yts.tsletter.viewmodel.write.WriteViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter {
    private List<Write> mWriteList;

    public MainAdapter(List<Write> writeList) {
        mWriteList = writeList;
    }

    public void setWriteList(List<Write> writeList) {
        this.mWriteList = writeList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        Write write = mWriteList.get(position);
        return write.getDate();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MainViewHolder holder = (MainViewHolder) viewHolder;
        WriteViewModel model = new WriteViewModel();
        Write write = mWriteList.get(position);
        model.setWrite(write);
        holder.setViewModel(model);
    }

    @Override
    public int getItemCount() {
        if (mWriteList != null) {
            return mWriteList.size();
        }
        return 0;
    }

    private class MainViewHolder extends RecyclerView.ViewHolder {
        private MainItemBinding binding;

        public MainViewHolder(@NonNull MainItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(WriteViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

}
