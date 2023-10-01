package com.example.lab3_20200334_iot.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_20200334_iot.R;
import com.example.lab3_20200334_iot.databinding.FragmentMagnetometerBinding;


public class MagnetometerFragment extends Fragment {

    FragmentMagnetometerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMagnetometerBinding.inflate(inflater,container,false);


        return binding.getRoot();

    }
}