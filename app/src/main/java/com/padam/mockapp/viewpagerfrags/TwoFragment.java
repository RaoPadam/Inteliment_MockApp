package com.padam.mockapp.viewpagerfrags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.padam.mockapp.R;

/**
 * Created by padam on 20-03-2016.
 */
public class TwoFragment extends Fragment {

    public TwoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rlv = inflater.inflate(R.layout.fargment_two, container, false);



        return rlv;



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout ongoing=(RelativeLayout)view.findViewById(R.id.laypout_fragmenttwo);

        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Fragment two", Toast.LENGTH_SHORT ).show();

            }
        });


    }
}

