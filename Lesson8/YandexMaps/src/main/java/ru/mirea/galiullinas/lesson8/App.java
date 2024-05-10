package ru.mirea.galiullinas.lesson8;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.user_location.UserLocationLayer;

public class App extends Application {
    private final String MAPKIT_API_KEY = "03a77017-e5f1-4277-a657-8e4495e21423";
    private UserLocationLayer userLocationLayer;
    private boolean isWork;

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
