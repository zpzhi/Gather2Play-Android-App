package com.example.pengzhizhou.meetup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class PostActivityFragment extends Fragment {

    GridView gridView;
    ArrayList<Item> gridArray;
    CustomGridViewAdapter customGridAdapter;
    private String loginUser = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences settings = this.getActivity().getSharedPreferences("MyPrefsFile", 0);
        loginUser = settings.getString("KEY_LOGIN_USER", null);
        View V = null;

        if (loginUser == null){
            Intent myIntent;
            myIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(myIntent);

        }else {
            V = inflater.inflate(R.layout.post_activity_view, container, false);


            ((TabHostActivity) getActivity())
                    .setActionBarTitle("发布活动");
            ((TabHostActivity) getActivity()).setImageViewable(View.GONE);
            ((TabHostActivity) getActivity()).setSearchCityViewable(View.GONE);
            //set grid view item
            Bitmap zhuoyouIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.zhuoyou);
            Bitmap jieriIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.jieri);
            Bitmap dianyingIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.dianying);
            Bitmap chuangyeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.chuangye);
            Bitmap mishiIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.mishi);
            Bitmap tiyuIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.tiyu);
            Bitmap jiangzuoIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.jiangzuo);
            Bitmap qitaIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.qita);

            //Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.zhuoyou);
            gridArray = new ArrayList<Item>();
            gridArray.add(new Item(jieriIcon, "节日派对"));
            gridArray.add(new Item(zhuoyouIcon, "桌游聚会"));
            gridArray.add(new Item(mishiIcon, "密室逃脱"));
            gridArray.add(new Item(chuangyeIcon, "创意展览"));
            gridArray.add(new Item(jiangzuoIcon, "行业讲座"));
            gridArray.add(new Item(dianyingIcon, "电影鉴赏"));
            gridArray.add(new Item(tiyuIcon, "体育活动"));
            gridArray.add(new Item(qitaIcon, "其他类别"));

            gridView = (GridView) V.findViewById(R.id.gridView1);
            customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.grid_single, gridArray);
            gridView.setAdapter(customGridAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent myIntent;
                    myIntent = new Intent(getActivity(), PostActivityDetailActivity.class);
                    myIntent.putExtra("activityTypes", gridArray.get(position).getTitle());
                    myIntent.putExtra("activityTypesId", Integer.toString(position));
                    startActivity(myIntent);
                }
            });
        }
        return V;
    }
}