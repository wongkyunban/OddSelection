package com.wong.odd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ItemViewHolder> {


    private List<DataBean> list;

    private int selectedPosition = -1;
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.mTV.setText(list.get(position).getName());
        holder.mCB.setChecked(list.get(position).isChecked());
        // 要实现单选的话，就要选择点击事件，然后在点击事件中根据选择状态做变化，不要选用setOnCheckedChangeListener()
        holder.mCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mCB.post(new Runnable() {
                    @Override
                    public void run() {
                        if(selectedPosition != -1){
                            list.get(selectedPosition).setChecked(false);
                            // notifyItemChanged必须在UI线程中执行，否则会报：
                            // java.lang.IllegalStateException: Cannot call this method
                            // while RecyclerView is computing a layout or scrolling
                            notifyItemChanged(selectedPosition);
                        }
                        list.get(position).setChecked(holder.mCB.isChecked());

                        if(holder.mCB.isChecked()){
                            selectedPosition = position;
                        }else{
                            selectedPosition = -1;
                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<DataBean> getList() {
        return list;
    }

    /**
     * 设置要显示的数据
     * @param list
     */
    public void setList(List<DataBean> list) {
        this.list = list;
    }

    /**
     * 返回选中的Item index
     * @return
     */
    public int getSelectedPosition(){
        return selectedPosition;
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox mCB;
        TextView mTV;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mCB = itemView.findViewById(R.id.checkBox);
            mTV = itemView.findViewById(R.id.textView);
        }
    }
}
