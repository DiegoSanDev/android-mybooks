package br.com.ps.appmybooks.activity.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class LivroFragmentAdapter extends FragmentStatePagerAdapter {



    public LivroFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new LivrosFragment();
            case 1:
                return new LivroCadastroFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
