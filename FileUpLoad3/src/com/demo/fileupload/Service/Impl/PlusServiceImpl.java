package com.demo.fileupload.Service.Impl;

import com.demo.fileupload.Service.PlusService;

public class PlusServiceImpl implements PlusService {
    @Override
    public int plus(int... num) {
        int sum=0;
        for(int n : num){
            sum+=n;
        }
        return sum;
    }
}
