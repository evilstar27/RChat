package com.test.morningstar.common.widget.recycler;

/**
 * @author morningstar
 * @date 2018/7/16
 */
public interface AdapterCallback<Data> {
    void update(Data data , RecyclerAdapter.ViewHolder<Data> holder);
}
