package com.test.zhaoziliang.googleplay.Fragment;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.zhaoziliang.googleplay.Enum.LoadResult;
import com.test.zhaoziliang.googleplay.Model.SubjectInfo;
import com.test.zhaoziliang.googleplay.Protocol.SubjectProtocol;
import com.test.zhaoziliang.googleplay.R;
import com.test.zhaoziliang.googleplay.Utils.HttpHelper;
import com.test.zhaoziliang.googleplay.Utils.UiUtils;

import java.util.List;

public class SubjectFragment extends BaseFragment {

    private List<SubjectInfo> datas;

    @Override
    public View createSuccessView() {
        ListView listView=new ListView(UiUtils.getContext());
        listView.setAdapter(new SubjectAdapter());
        return listView;
    }
    private class SubjectAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if(convertView!=null){
                view=convertView;
                holder=(ViewHolder) view.getTag();
            }else{
                view=UiUtils.inflate(R.layout.item_subject);
                holder=new ViewHolder();
                holder.item_icon=(ImageView) view.findViewById(R.id.item_icon);
                holder.item_txt=(TextView) view.findViewById(R.id.item_txt);
                view.setTag(holder);
            }
            SubjectInfo info=datas.get(position);
            holder.item_txt.setText(info.getDes());
            bitmapUtils.display(holder.item_icon, HttpHelper.URL+"image?name="+info.getUrl());
            return view;
        }

    }
    class ViewHolder{
        ImageView item_icon;
        TextView item_txt;
    }

    @Override
    public LoadResult load() {
        SubjectProtocol protocol=new SubjectProtocol();
//        datas = protocol.load(0);
        return checkData(datas);
    }
}
