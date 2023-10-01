package com.example.lab3_20200334_iot.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_20200334_iot.R;
import com.example.lab3_20200334_iot.databinding.FragmentAccelerometerBinding;


public class AccelerometerFragment extends Fragment {

    FragmentAccelerometerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentAccelerometerBinding.inflate(inflater,container,false);


       return binding.getRoot();
    }
}