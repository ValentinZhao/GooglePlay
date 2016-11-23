package com.test.zhaoziliang.googleplay.Enum;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public enum LoadResult {
    error(2), empty(3), success(4);

    int value;

    LoadResult(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
