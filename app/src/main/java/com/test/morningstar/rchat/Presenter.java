package com.test.morningstar.rchat;

import android.text.TextUtils;

/**
 * @author morningstar
 * @date 2018/7/25
 */
public class Presenter implements IPresenter{
    private IView mView;

    public Presenter(IView iView){
        mView = iView;
    }

    @Override
    public void search() {
        String inputString = mView.getInputString();
        if(TextUtils.isEmpty(inputString)){
            return;
        }

        int hashCode = inputString.hashCode();
        IUserService iUserService = new UserService();
        String serviceResult = iUserService.search(hashCode);

        String result = "Result:" + inputString + "--"+ serviceResult;
        mView.setResultString(result);
    }
}
