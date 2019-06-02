package cn.itcast.service.impl;

import cn.itcast.service.PlusService;

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
