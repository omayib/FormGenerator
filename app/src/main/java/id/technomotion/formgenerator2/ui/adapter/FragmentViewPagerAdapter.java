package id.technomotion.formgenerator2.ui.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import id.technomotion.formgenerator2.FormPage;
import id.technomotion.formgenerator2.ui.fragment.FormFragment;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments=new ArrayList<>();

    public FragmentViewPagerAdapter(Context context, FragmentManager fm, List<FormPage> res) {
        super(fm);
        this.context=context;

        for (FormPage formPage:res) {
            fragments.add(FormFragment.newInstance(formPage));
        }


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}