package com.test.morningstar.common.widget.recyler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.morningstar.common.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author morningstar
 * @date 2018/7/16
 */
public abstract class RecylerAdapter<Data>
        extends RecyclerView.Adapter<RecylerAdapter.ViewHolder<Data>>
            implements View.OnClickListener, View.OnLongClickListener, AdapterCallback<Data>{
    private final List<Data> mDataList = new ArrayList<>();

    /**
     * 创建一个ViewHolder
     * @param parent RecyclerView
     * @param viewType 界面的类型,约定为XML布局的Id
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 得到LayoutInflater用于把XML初始化为View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // 把XML id为ViewType的文件初始化为一个root View
        View root = inflater.inflate(viewType, parent,false);
        // 通过子类必须实现的方法，得到一个ViewHolder
        ViewHolder<Data> holder = onCreateViewHolder(root , viewType);

        // 设置事件点击
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);
        // 设置View的Tag为ViewHolder，进行双向绑定
        root.setTag(R.id.tag_recycler_holder);

        // 进行界面注解绑定
        holder.unbinder = ButterKnife.bind(holder, root);
        // 绑定callback
        holder.callback = this;

        return null;
    }

    /**
     * 得到一个新的ViewHolder
     * @param root 根布局
     * @param viewType 布局的类型，其实就是XML的Id
     * @return ViewHolder
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    /**
     * 绑定数据到一个Holder上
     * @param holder ViewHolder
     * @param position 坐标
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder<Data> holder, int position) {
        // 得到需要绑定的数据
        Data data = mDataList.get(position);
        // 触发Holder的绑定方法
        holder.bind(data);
    }

    /**
     * 得到当前集合的数据量
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder{
        private Unbinder unbinder;
        private AdapterCallback<Data> callback;
        protected Data mData;

        public ViewHolder(View itemView){
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         * @param data 绑定的数据
         */
        void bind(Data data){
            this.mData = data;
            onBind(data);
        }

        /**
         * 当触发绑定数据时的回掉，必须复写
         * @param data 绑定的数据
         */
        protected abstract void onBind(Data data);

        /**
         * Holder自己对自己对应的Data进行更新操作
         * @param data Data数据
         */
        public void updateData(Data data){
            if(this.callback != null){
                this.callback.update(data ,this);
            }
        }
    }
}