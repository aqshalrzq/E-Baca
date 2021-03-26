package com.gdtidm.bacaonline.Intro.ComponentsIntro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gdtidm.bacaonline.R;

import java.util.List;

public class AdapterIntro extends PagerAdapter {

    Context context;
    List<ItemIntro> listScreen;

    public AdapterIntro(Context context, List<ItemIntro> listScreen) {
        this.context = context;
        this.listScreen = listScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView img   =   layScreen.findViewById(R.id.intro_img);
        TextView title  =   layScreen.findViewById(R.id.intro_title);
        TextView desc   =   layScreen.findViewById(R.id.intro_description);

        title.setText(listScreen.get(position).getTitle());
        desc.setText(listScreen.get(position).getDescription());
        img.setImageResource(listScreen.get(position).getImage());

        container.addView(layScreen);
        return layScreen;
    }

    @Override
    public int getCount() {
        return listScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
