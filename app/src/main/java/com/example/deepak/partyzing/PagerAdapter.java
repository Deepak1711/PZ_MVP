package com.example.deepak.partyzing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by deepak on 30/6/16.
 */
public class PagerAdapter extends android.support.v4.view.PagerAdapter {
    int image_resources[] = {R.drawable.bday, R.drawable.anniversary, R.drawable.graduate};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public PagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_pager, container, false);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.image_view1);
        imageView1.setImageResource(image_resources[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
