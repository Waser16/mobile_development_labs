package ru.mirea.galiullinas.mireaproject.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.GeoObjectSelectionMetadata;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.search.Address;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.search.SearchOptions;
import com.yandex.mapkit.search.Session;
import com.yandex.mapkit.search.ToponymObjectMetadata;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;

import java.util.Objects;

import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.ActivityMapBinding;

public class MapActivity extends AppCompatActivity implements
        UserLocationObjectListener {

    private ActivityMapBinding binding;
    private MapView mapView;
    private UserLocationLayer userLocationLayer;
    private boolean isWork = false;
    private final int REQUEST_CODE_PERMISSION = 100;
    private SearchManager searchManager;
    public Session searchSession;
    private GeoObjectTapListener tapListener = new GeoObjectTapListener() {
        @Override
        public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
            GeoObjectSelectionMetadata selectionMetadata = geoObjectTapEvent.getGeoObject()
                    .getMetadataContainer().getItem(GeoObjectSelectionMetadata.class);
            binding.mapView.getMap().selectGeoObject(selectionMetadata.getId(), selectionMetadata.getLayerId());
            return false;
        }
    };;
    private Session.SearchListener searchListener = new Session.SearchListener() {
        @Override
        public void onSearchResponse(Response response) {
            ToponymObjectMetadata toponymObjectMetadata = response.getCollection().getChildren().stream()
                    .map(child -> child.getObj().getMetadataContainer().getItem(ToponymObjectMetadata.class))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);

            String street = "Информация об улице не найдена";

            if (toponymObjectMetadata != null) {
                street = toponymObjectMetadata.getAddress().getComponents().stream()
                        .filter(component -> component.getKinds().contains(Address.Component.Kind.STREET))
                        .map(Address.Component::getName)
                        .findFirst()
                        .orElse("Информация об улице не найдена");
            }

            Toast.makeText(getApplicationContext(), street, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSearchError(@NonNull Error error) {

        }
    };

    private final InputListener inputListener = new InputListener() {
        @Override
        public void onMapTap(Map map, Point point) {
            searchSession = searchManager.submit(point, 20, new SearchOptions(), searchListener);
        }

        @Override
        public void onMapLongTap(Map map, Point point) {
            // Обработка долгого нажатия на карту (если нужно)
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPermissions();
        MapKitFactory.initialize(this);
        SearchFactory.initialize(this);  // Добавил инициализацию SearchFactory
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (isWork) {
            mapView = binding.mapView;
            mapView.getMap().move(
                    new CameraPosition(new Point(55.753215, 37.622504), 11.0f, 0.0f,
                            0.0f),
                    new Animation(Animation.Type.SMOOTH, 0),
                    null
            );

            loadUserLocationLayer();

            binding.mapView.getMap().addTapListener(tapListener);
            searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE);
            binding.mapView.getMap().addInputListener(inputListener);

        } else {
            Toast.makeText(this, "Даны не все разрешения", Toast.LENGTH_SHORT).show();
        }
    }

    private void setPermissions() {
        int mapPermissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int coarsePermissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int backgroundPermissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        if (mapPermissionStatus == PackageManager.PERMISSION_GRANTED && coarsePermissionStatus == PackageManager.PERMISSION_GRANTED
                && backgroundPermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onObjectAdded(@NonNull UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float) (mapView.getWidth() * 0.5),
                        (float) (mapView.getHeight() * 0.5)),
                new PointF((float) (mapView.getWidth() * 0.5),
                        (float) (mapView.getHeight() * 0.83)));
// При определении направления движения устанавливается следующая иконка
        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                this, android.R.drawable.arrow_up_float));
// При получении координат местоположения устанавливается следующая иконка
        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();
        pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(this, R.drawable.point_icon),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );

        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);
    }

    @Override
    public void onObjectRemoved(@NonNull UserLocationView userLocationView) {

    }

    @Override
    public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {

    }

    private void loadUserLocationLayer() {
        MapKit mapKit = MapKitFactory.getInstance();
        mapKit.resetLocationManagerToDefault();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);
        userLocationLayer.setObjectListener(this);
    }
}
