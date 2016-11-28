package com.test.zhaoziliang.googleplay.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.test.zhaoziliang.googleplay.Enum.LoadResult;
import com.test.zhaoziliang.googleplay.Model.AppInfo;
import com.test.zhaoziliang.googleplay.Protocol.HomeProtocal;
import com.test.zhaoziliang.googleplay.R;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;
import com.test.zhaoziliang.googleplay.Widget.BaseListView;

import java.util.List;

import static com.test.zhaoziliang.googleplay.Protocol.BaseProtocal.SERVER_URL;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class HomeFragment extends BaseFragment {
    private List<AppInfo> data;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();//根据服务器状态来显示
    }

    @Override
    public View createSuccessView() {
//        TextView tv = new TextView(getActivity());
//        tv.setText("Loading Complete!");
//        tv.setTextSize(28);
//        return tv;
        BaseListView mListView = new BaseListView(UiUtils.getContext());
        mListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
        return mListView;
    }

    @Override
    public LoadResult load() {
        HomeProtocal protocal = new HomeProtocal();
        data = protocal.load(1);
        return checkData(data);
    }

    private class HomeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(getActivity(), R.layout.app_item, null);
                holder.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
                holder.item_title = (TextView) convertView.findViewById(R.id.item_title);
                holder.item_size = (TextView) convertView.findViewById(R.id.item_size);
                holder.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
                holder.item_rating = (RatingBar) convertView.findViewById(R.id.item_rating);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            AppInfo info = data.get(position);
            holder.item_title.setText(info.getName());
            String size = Formatter.formatFileSize(UiUtils.getContext(), info.getSize());
            holder.item_size.setText(size);
            holder.item_bottom.setText(info.getDes());
            float stars = info.getStars();
            holder.item_rating.setRating(stars);
            String iconUrl = info.getIconUrl();
            bitmapUtils.display(holder.item_icon, SERVER_URL + iconUrl);
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView item_icon;
        TextView item_title;
        TextView item_size;
        TextView item_bottom;
        RatingBar item_rating;
    }
}
