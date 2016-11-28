package com.test.zhaoziliang.googleplay.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.test.zhaoziliang.googleplay.R;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;

/**
 * Created by zhaoziliang on 16/11/26.
 */

public class BaseListView extends ListView {
    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
//		setSelector  点击显示的颜色
//		setCacheColorHint  拖拽的颜色
//		setDivider  每个条目的间隔	的分割线
        this.setSelector(R.drawable.nothing);  // 什么都没有
        this.setCacheColorHint(R.drawable.nothing);
        this.setDivider(UiUtils.getDrawalbe(R.drawable.nothing));
    }

}
