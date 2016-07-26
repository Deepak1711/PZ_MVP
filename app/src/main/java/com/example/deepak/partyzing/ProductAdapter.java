package com.example.deepak.partyzing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created on 13/7/16.
 */
public class ProductAdapter extends android.support.v4.view.PagerAdapter {
    private final Context ctx;
    private final ShowHideInterface showHideInterface;
    private final OnClickInterface onClickInterface;
    private final String title[];
    private final float PAGE_WIDTH = 0.45f;
    private final int ONE = 1;
    private final int TWO = 2;
    private boolean select = false;
    private ArrayList<Integer> selectedPositions = new ArrayList<>();

    public ProductAdapter(Context ctx, ShowHideInterface showHideInterface, OnClickInterface onClickInterface, String title[]) {
        this.ctx = ctx;
        this.showHideInterface = showHideInterface;
        this.onClickInterface = onClickInterface;
        this.title = title;
    }

    /**
     * As we are displaying two items per page,therefore length is divided by 2
     * and returned value is rounded to upper bound for the cases of odd length
     *
     * @return integer value representing total no of pages
     */
    @Override
    public int getCount() {
        return (int) Math.ceil(((double) title.length) / TWO);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return PAGE_WIDTH;
    }

    /**
     * Here we are displaying two items per page
     * hence a temporary variable is used to handle the position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        int tmp = 0;
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.adapter_item, container, false);
        tmp = position * TWO;

        if (tmp < title.length) {
            FrameLayout frameLayoutOne = (FrameLayout) view.findViewById(R.id.flcontainer_one);
            frameLayoutOne.setBackgroundResource(R.drawable.icon_selector);
            TextView textViewOne = (TextView) view.findViewById(R.id.text_one);
            textViewOne.setText(title[tmp]);
            frameLayoutOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, position * TWO);
                }
            });
            if (tmp + ONE < title.length) {
                FrameLayout frameLayoutTwo = (FrameLayout) view.findViewById(R.id.flcontainer_two);
                frameLayoutTwo.setBackgroundResource(R.drawable.icon_selector);
                TextView textViewTwo = (TextView) view.findViewById(R.id.text_two);
                textViewTwo.setText(title[tmp + ONE]);
                frameLayoutTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(v, (position * TWO) + ONE);
                    }
                });
            }
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    /**
     * Changing the presentation of the item when the item is selected or unselected
     * and also updates the total amount for the selected products
     *
     * @param v        view which is tapped
     * @param position gives the position of the view tapped,where position count starting from zero
     */
    public void onItemClick(View v, int position) {
        v.setSelected(!v.isSelected());
        if (v.isSelected() && select == true) {
            selectedPositions.add(position);
            onClickInterface.onItemClick();
        } else if (!v.isSelected()) {
            for (int i = 0; i < selectedPositions.size(); i++) {
                if (selectedPositions.get(i) == position) {
                    selectedPositions.remove(i);
                    break;
                }
            }
            if (selectedPositions.isEmpty()) {
                select = false;
                showHideInterface.hideNext();
            } else {
                onClickInterface.onItemClick();
            }
        } else {
            select = true;
            selectedPositions.add(position);
            onClickInterface.onItemClick();
            showHideInterface.showNext();
        }
    }

    //return list of currently selected products
    public ArrayList<Integer> getSelectedPositions() {
        return selectedPositions;
    }
}
