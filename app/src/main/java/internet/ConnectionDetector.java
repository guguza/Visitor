package internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Администратор on 05.05.2015.
 * Used to detect whether internet is on or off. Connection is checked in the method isConnectedToInternet.
 * If not, returns false.
 */

public class ConnectionDetector {

    private Context myContext;

    public ConnectionDetector(Context context) {
        myContext = context;
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if(info != null) {
                for(int i = 0; i < info.length; i++) {
                    if(info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
