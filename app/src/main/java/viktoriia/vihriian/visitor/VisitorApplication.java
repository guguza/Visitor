package viktoriia.vihriian.visitor;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Администратор on 19.04.2015.
 */
public class VisitorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "xAiTVUjUxSHHXIg2U7Nth91Ivox9mq2XpbffOLXS", "2SroWbDfH2FjkgkiTcpFWVg61Pg6fdgCIeDQLYcX");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        //enable push-notifications
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
       ParseFacebookUtils.initialize(this);
    }
}
