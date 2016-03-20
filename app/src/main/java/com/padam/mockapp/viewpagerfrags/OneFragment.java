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
public class OneFragment  extends Fragment {

    public OneFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rlv = inflater.inflate(R.layout.fragment_one, container, false);



        return rlv;



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout ongoing=(RelativeLayout)view.findViewById(R.id.laypout_fragmentone);

        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Fragment one", Toast.LENGTH_SHORT ).show();

            }
        });


    }
}

