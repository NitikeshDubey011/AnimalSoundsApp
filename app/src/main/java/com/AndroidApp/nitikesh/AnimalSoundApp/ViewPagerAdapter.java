package com.AndroidApp.nitikesh.AnimalSoundApp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Nitikesh Dubey on 12/20/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    String[] Animal;
    LayoutInflater layoutInflater;

    public ViewPagerAdapter(Activity activity,String[] Animal){
        this.activity=activity;
        this.Animal=Animal;
    }


    @Override
    public int getCount() {
        return Animal.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View ImageView=layoutInflater.inflate(R.layout.view_pager_items,container,false);
        android.widget.ImageView imageView=(ImageView)ImageView.findViewById(R.id.animalImages);
        int animalImageId=activity.getResources().getIdentifier(Animal[position],"drawable",activity.getPackageName());
        imageView.setImageResource(animalImageId);
        container.addView(ImageView);
        return ImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewGroup)container).removeView((View)object);
    }
}
