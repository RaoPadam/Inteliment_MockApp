package com.padam.mockapp.navdrawerfragments;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.padam.mockapp.R;
import com.padam.mockapp.viewpagerfrags.FourFragment;
import com.padam.mockapp.viewpagerfrags.OneFragment;
import com.padam.mockapp.viewpagerfrags.ThreeFragment;
import com.padam.mockapp.viewpagerfrags.TwoFragment;


public class TestOneFragment extends Fragment {

    private ViewPager viewPager;

    Button item1, item2, item3, item4, item5;
    TextView txt_displayItem, txt_textview1, txt_textview2, txt_textview3;

    Button btn_red, btn_blue, btn_green;

    TestOneFragment hf;

    public TestOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);




        item1 = (Button) rootView.findViewById(R.id.button1);
        item2 = (Button) rootView.findViewById(R.id.button2);
        item3 = (Button) rootView.findViewById(R.id.button3);
        item4 = (Button) rootView.findViewById(R.id.button4);
        item5 = (Button) rootView.findViewById(R.id.button5);


        btn_red = (Button) rootView.findViewById(R.id.btn_red);
        btn_blue = (Button) rootView.findViewById(R.id.btn_blue);
        btn_green = (Button) rootView.findViewById(R.id.btn_green);

        txt_displayItem = (TextView) rootView.findViewById(R.id.Textview_Display_ITemNAme);

        txt_textview1 = (TextView) rootView.findViewById(R.id.Textview1);
        txt_textview2 = (TextView) rootView.findViewById(R.id.Textview2);
        txt_textview3 = (TextView) rootView.findViewById(R.id.Textview3);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        SetClickListeners();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void SetClickListeners() {


        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ColorDrawable cd = new ColorDrawable(getActivity().getResources().getColor(
                        R.color.redcolor));


                getActivity().getWindow().setBackgroundDrawable(cd);

            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            ColorDrawable cd = new ColorDrawable(getActivity().getResources().getColor(
                R.color.greencolor));


                getActivity().getWindow().setBackgroundDrawable(cd);

            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ColorDrawable cd = new ColorDrawable(getActivity().getResources().getColor(
                R.color.bluecolor));


                getActivity().getWindow().setBackgroundDrawable(cd);

            }
        });


        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_displayItem.setText(item1.getText());
            }
        });


        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_displayItem.setText(item2.getText());
            }
        });


        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_displayItem.setText(item3.getText());
            }
        });


        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_displayItem.setText(item4.getText());
            }
        });


        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_displayItem.setText(item5.getText());
            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        adapter.addFragment(new FourFragment(), "Four");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}


