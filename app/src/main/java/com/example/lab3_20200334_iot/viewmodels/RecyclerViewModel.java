package com.example.lab3_20200334_iot.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab3_20200334_iot.pojos.Result;

import java.util.ArrayList;

public class RecyclerViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Result>> getListaMagnetoFragment() {
        return listaMagnetoFragment;
    }

    public MutableLiveData<ArrayList<Result>> getListaAccelereFragment() {
        return listaAccelereFragment;
    }


    public MutableLiveData<Result> getResultMutableLiveDataMagneto() {
        return resultMutableLiveDataMagneto;
    }

    public MutableLiveData<Result> getResultMutableLiveDataAccele() {
        return resultMutableLiveDataAccele;
    }

    private MutableLiveData<Result> resultMutableLiveDataMagneto  = new MutableLiveData<>();

    private MutableLiveData<Result> resultMutableLiveDataAccele  = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Result>> listaMagnetoFragment  = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Result>>  listaAccelereFragment = new MutableLiveData<>();
}
