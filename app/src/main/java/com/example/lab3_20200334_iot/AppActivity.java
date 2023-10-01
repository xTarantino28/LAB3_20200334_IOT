package com.example.lab3_20200334_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lab3_20200334_iot.adapters.UsersAdapter;
import com.example.lab3_20200334_iot.api.UserService;
import com.example.lab3_20200334_iot.databinding.ActivityAppBinding;
import com.example.lab3_20200334_iot.fragments.AccelerometerFragment;
import com.example.lab3_20200334_iot.fragments.AccelerometerFragmentDirections;
import com.example.lab3_20200334_iot.fragments.MagnetometerFragment;
import com.example.lab3_20200334_iot.fragments.MagnetometerFragmentDirections;
import com.example.lab3_20200334_iot.pojos.Result;
import com.example.lab3_20200334_iot.pojos.RootPojo;
import com.example.lab3_20200334_iot.viewmodels.RecyclerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppActivity extends AppCompatActivity {

    ActivityAppBinding binding;
    UserService userService;

    ArrayList<Result> listaMagnetoFragment = new ArrayList<>();

    ArrayList<Result> listaAccelereFragment = new ArrayList<>();
    Result user;

    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        RecyclerViewModel recyclerViewModel = new ViewModelProvider(AppActivity.this).get(RecyclerViewModel.class);
        recyclerViewModel.getListaMagnetoFragment().setValue(listaMagnetoFragment);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.nav_host_fragment, MagnetometerFragment.class, null)
                .commit();

        /*String navhostfragmentid = Integer.toString(navHostFragment.getId());
        String fragmentMagnetoometerID = Integer.toString(R.id.magnetometerFragment);
        String fragmentacelerometroID = Integer.toString(R.id.accelerometerFragment);
        String currentdestinatio = Integer.toString(navController.getCurrentDestination().getId());
        Log.d("msg-test",navhostfragmentid);
        Log.d("msg-test",fragmentMagnetoometerID);
        Log.d("msg-test",fragmentacelerometroID);
        Log.d("msg-test",currentdestinatio);*/
        binding.buttonNavFragment.setOnClickListener( view -> {
            if (navController.getCurrentDestination().getId() == R.id.magnetometerFragment)   {
                binding.buttonNavFragment.setText("Ir a Acelerómetro");
                Log.d("msg-test","Actualmente en MagnetometroFragment");

                //RecyclerViewModel recyclerViewModel = new ViewModelProvider(AppActivity.this).get(RecyclerViewModel.class);
                recyclerViewModel.getListaAccelereFragment().setValue(listaAccelereFragment);
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.nav_host_fragment, AccelerometerFragment.class, null)
                        .commit();
                navController.navigate(MagnetometerFragmentDirections.actionMagnetometerFragmentToAccelerometerFragment());


            } else { //LA OTRA OPCION ES UNICAMENTE ACCELEROMETER FRAGMENT
                Log.d("msg-test","Actualmente en AcelerometroFragment");
                binding.buttonNavFragment.setText("Ir a Magnetómetro");
                //RecyclerViewModel recyclerViewModel = new ViewModelProvider(AppActivity.this).get(RecyclerViewModel.class);
                recyclerViewModel.getListaMagnetoFragment().setValue(listaMagnetoFragment);
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.nav_host_fragment, MagnetometerFragment.class, null)
                        .commit();
                navController.navigate(AccelerometerFragmentDirections.actionAccelerometerFragmentToMagnetometerFragment());

            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchWebServiceUserData();
            }
        });


    }


    public void fetchWebServiceUserData() {

        if(tengoInternet()){
            Log.d("msg-test", "HOLLAAAAA");

            userService.getUserInfo().enqueue(new Callback<RootPojo>() {
                @Override
                public void onResponse(@NonNull Call<RootPojo> call, @NonNull Response<RootPojo> response) {
                    //aca estoy en el UI Thread
                    if(response.isSuccessful()){
                        RootPojo rootPojo = response.body();
                        if (rootPojo != null) {
                            user = rootPojo.getResults().get(0);
                            Log.d("msg-test", "ASJIGNASGOIANSASGASGAGAS");
                            if(navController.getCurrentDestination().getId() == R.id.magnetometerFragment)   {
                                Log.d("msg-test","Actualmente en MagnetometroFragment");

                                listaMagnetoFragment.add(user);

                                RecyclerViewModel recyclerViewModel = new ViewModelProvider(AppActivity.this).get(RecyclerViewModel.class);
                                recyclerViewModel.getListaMagnetoFragment().setValue(listaMagnetoFragment);
                                getSupportFragmentManager().beginTransaction()
                                        .setReorderingAllowed(true)
                                        .add(R.id.nav_host_fragment, MagnetometerFragment.class, null)
                                        .commit();
                            } else { //LA OTRA OPCION ES UNICAMENTE ACCELEROMETER FRAGMENT
                                Log.d("msg-test","Actualmente en AcelerometroFragment");
                                listaAccelereFragment.add(user);

                                RecyclerViewModel recyclerViewModel = new ViewModelProvider(AppActivity.this).get(RecyclerViewModel.class);
                                recyclerViewModel.getListaAccelereFragment().setValue(listaAccelereFragment);
                                getSupportFragmentManager().beginTransaction()
                                        .setReorderingAllowed(true)
                                        .add(R.id.nav_host_fragment, AccelerometerFragment.class, null)
                                        .commit();

                            }
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<RootPojo> call, @NonNull Throwable t) {
                    Log.d("msg-test", "FALLO CONSULTA API");
                }
            });
        } else {
            Toast.makeText(AppActivity.this, "Internet connection required to display user information", Toast.LENGTH_SHORT).show();
        }


    }


    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test", "Internet: " + tieneInternet);

        return tieneInternet;
    }
}