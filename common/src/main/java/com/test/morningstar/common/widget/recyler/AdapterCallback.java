package com.test.morningstar.common.widget.recyler;

/**
 * @author morningstar
 * @date 2018/7/16
 */
public interface AdapterCallback<Data> {
    void update(Data data , RecylerAdapter.ViewHolder<Data> holder);
}
