package com.test.morningstar.rchat;

/**
 * @author morningstar
 * @date 2018/7/25
 */
public class UserService implements IUserService{

    @Override
    public String search(int hashCode){
        return "User:" + hashCode;
    }
}
