package info.tiamed.MoeWallpaper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.TabPagerAdapter;

public class MainFragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.main_pager)
    ViewPager mainPager;
    @BindArray(R.array.home_tabs)
    String[] homeTabs;
    @BindView(R.id.tab)
    TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, null);
        unbinder = ButterKnife.bind(this, view);
        mainPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager(), homeTabs));
        tab.setupWithViewPager(mainPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
