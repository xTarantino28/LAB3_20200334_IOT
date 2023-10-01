package com.example.lab3_20200334_iot.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_20200334_iot.R;
import com.example.lab3_20200334_iot.adapters.UsersAdapter;
import com.example.lab3_20200334_iot.databinding.FragmentMagnetometerBinding;
import com.example.lab3_20200334_iot.pojos.Result;
import com.example.lab3_20200334_iot.viewmodels.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;


public class MagnetometerFragment extends Fragment {

    FragmentMagnetometerBinding binding;
    UsersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMagnetometerBinding.inflate(inflater,container,false);

        RecyclerViewModel recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);

        recyclerViewModel.getListaMagnetoFragment().observe(getViewLifecycleOwner(), listaUsers -> {
            adapter = new UsersAdapter(listaUsers,container.getContext());
           // adapter.setContext(container.getContext());
            adapter.setListUsers(listaUsers);
            adapter.notifyDataSetChanged();
            binding.rvMagneto.setAdapter(adapter);
            adapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    listaUsers.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            //linearLayoutManager.setStackFromEnd(true);
            binding.rvMagneto.setLayoutManager(linearLayoutManager);

        });

        /*
        recyclerViewModel.getResultMutableLiveDataMagneto().observe(getViewLifecycleOwner(), user -> {
            //UsersAdapter adapter = new UsersAdapter(listaUsers,container.getContext());
            //adapter.setContext(container.getContext());
            Log.d("msg-test",user.getName().getFirst());
            adapter.addItem(user);
            adapter.notifyDataSetChanged();
            //binding.rvMagneto.setAdapter(adapter);
            //binding.rvMagneto.scrollToPosition(adapter.getItemCount() - 1);
           // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            //linearLayoutManager.setStackFromEnd(true);
            //binding.rvMagneto.setLayoutManager(linearLayoutManager);

        });*/

        return binding.getRoot();
    }
}