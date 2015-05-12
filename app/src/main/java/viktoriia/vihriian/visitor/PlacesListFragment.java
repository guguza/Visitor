package viktoriia.vihriian.visitor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Администратор on 09.05.2015.
 */
public class PlacesListFragment extends Fragment {

    public static final String TAG = "PlacesListTag";

    public static PlacesListFragment newInstance() {
        return new PlacesListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.places_list_fragment, container, false);
        return view;
    }
}
