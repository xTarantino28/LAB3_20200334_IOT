package com.example.lab3_20200334_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.lab3_20200334_iot.databinding.ActivityAppBinding;
import com.example.lab3_20200334_iot.fragments.AccelerometerFragment;
import com.example.lab3_20200334_iot.fragments.AccelerometerFragmentDirections;
import com.example.lab3_20200334_iot.fragments.MagnetometerFragment;
import com.example.lab3_20200334_iot.fragments.MagnetometerFragmentDirections;

public class AppActivity extends AppCompatActivity {

    ActivityAppBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
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
                navController.navigate(MagnetometerFragmentDirections.actionMagnetometerFragmentToAccelerometerFragment());
            } else { //LA OTRA OPCION ES UNICAMENTE ACCELEROMETER FRAGMENT
                Log.d("msg-test","Actualmente en AcelerometroFragment");
                binding.buttonNavFragment.setText("Ir a Magnetómetro");
                navController.navigate(AccelerometerFragmentDirections.actionAccelerometerFragmentToMagnetometerFragment());

            }
        });

    }
}