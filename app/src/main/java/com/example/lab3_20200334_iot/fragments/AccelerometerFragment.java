package com.example.lab3_20200334_iot.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab3_20200334_iot.AppActivity;
import com.example.lab3_20200334_iot.R;
import com.example.lab3_20200334_iot.adapters.UsersAdapter;
import com.example.lab3_20200334_iot.databinding.FragmentAccelerometerBinding;
import com.example.lab3_20200334_iot.pojos.Result;
import com.example.lab3_20200334_iot.sensorListeners.AccManager;
import com.example.lab3_20200334_iot.sensorListeners.SensorAccListener;
import com.example.lab3_20200334_iot.viewmodels.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AccelerometerFragment extends Fragment {

    FragmentAccelerometerBinding binding;
    UsersAdapter adapter;

    SensorManager mSensorManager;
    SensorAccListener listener = new SensorAccListener();

    AccManager accManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentAccelerometerBinding.inflate(inflater,container,false);

       RecyclerViewModel recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        recyclerViewModel.getListaAccelereFragment().observe(getViewLifecycleOwner(), listaUsers -> {
            adapter = new UsersAdapter(listaUsers,container.getContext());
            //adapter.setContext(container.getContext());
            adapter.setListUsers(listaUsers);
            binding.rvAcceler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    listaUsers.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            //linearLayoutManager.setStackFromEnd(true);
            binding.rvAcceler.setLayoutManager(linearLayoutManager);
        });




        /*
        recyclerViewModel.getResultMutableLiveDataAccele().observe(getViewLifecycleOwner(), user -> {
            //UsersAdapter adapter = new UsersAdapter();
            //adapter.setContext(container.getContext());
            adapter.addItem(user);
            adapter.notifyDataSetChanged();
            //binding.rvAcceler.setAdapter(adapter);
            //binding.rvAcceler.scrollToPosition(adapter.getItemCount() - 1);
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            //linearLayoutManager.setStackFromEnd(true);
            //binding.rvAcceler.setLayoutManager(linearLayoutManager);

        });*/

        // Inicializar AcelerometroManager
        accManager = new AccManager(getContext(),binding.rvAcceler);
        // Iniciar el monitoreo del acelerómetro
        accManager.iniciar();

        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        accManager.detener();
    }
}