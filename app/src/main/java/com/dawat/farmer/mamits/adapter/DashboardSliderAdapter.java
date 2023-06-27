package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardSliderAdapter extends PagerAdapter {
    private List<SliderModel> list;
    private Context context;

    public DashboardSliderAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dashboard_slider_layout, null);
        SliderModel model = list.get(position);
        ImageView imageView = (view).findViewById(R.id.slider_image);
        Glide.with(context).load(model.getAttachment()).into(imageView);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void setList(List<SliderModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
