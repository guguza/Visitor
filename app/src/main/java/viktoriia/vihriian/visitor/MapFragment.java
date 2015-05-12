package viktoriia.vihriian.visitor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Администратор on 09.05.2015.
 */

public class MapFragment extends Fragment {

    public static final String TAG = "MapFragment";
    private GoogleMap googleMap;
    private MapView mapView;
    protected Context mContext;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        //inflate and return the layout
        View root = inflater.inflate(R.layout.map_fragment, container, false);
        mContext = getActivity().getApplicationContext();
        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(bundle);

        mapView.onResume(); //needed to get the map displayed immediately
        setMapView();
        return root;
    }

    private void setMapView() {
        try {
            MapsInitializer.initialize(mContext);

            switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext)) {
                //if connection to Google services is established, set up MapView
                case ConnectionResult.SUCCESS:
                    if(mapView != null) {
                        googleMap = mapView.getMap();
                        // latitude and longitude
                        double latitude = 17.385044;
                        double longitude = 78.486671;

                        // create marker
                        MarkerOptions marker = new MarkerOptions().position(
                                new LatLng(latitude, longitude)).title("Hello Maps");

                        // Changing marker icon
                        marker.icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                        // adding marker
                        googleMap.addMarker(marker);
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
                        googleMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
                        setMapSettings();
                    }
                    break;
                case ConnectionResult.SERVICE_MISSING:
                    Toast.makeText(mContext,
                            "Download Google Play services!", Toast.LENGTH_LONG).show();
                    break;
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                    Toast.makeText(mContext,
                            "Update Google Play services!", Toast.LENGTH_LONG).show();
                    break;
                case ConnectionResult.NETWORK_ERROR:
                    Toast.makeText(mContext,
                        "Check connection to the Internet!", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }catch (Exception e) {
                e.printStackTrace();
        }
    }

    private void setMapSettings() {
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}

