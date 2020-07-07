package com.test.gpsprovider;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends Activity {
    LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Инициализируем LocationManager
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void AddGPS(View view) {
        //Добавляем тестовый провайдер
        mLocationManager.addTestProvider(LocationManager.GPS_PROVIDER, false, false,
                false, false, true, true,
                true, android.location.Criteria.POWER_LOW, android.location.Criteria.ACCURACY_FINE);

        //Включаем тестовый провайдер
        mLocationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        //Задаем фиктивную точку
        Location newLocation = new Location(LocationManager.GPS_PROVIDER);
        newLocation.setLatitude(55.75578);
        newLocation.setLongitude(37.61786);
        newLocation.setTime(System.currentTimeMillis());
        newLocation.setAccuracy(25);
        newLocation.setElapsedRealtimeNanos(System.nanoTime());
        mLocationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);

        LocationServices.getFusedLocationProviderClient(this).setMockMode( true);
        LocationServices.getFusedLocationProviderClient(this).setMockLocation(newLocation);

    }

    public void DelGPS(View view) {
        //Удаляем наш тестовый провайдер
        mLocationManager.removeTestProvider(LocationManager.GPS_PROVIDER);
        LocationServices.getFusedLocationProviderClient(this).setMockMode( false);
    }
}
