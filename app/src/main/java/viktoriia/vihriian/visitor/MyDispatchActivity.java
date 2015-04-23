package viktoriia.vihriian.visitor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ui.ParseLoginDispatchActivity;


public class MyDispatchActivity extends ParseLoginDispatchActivity {
//if user has been authorized
    @Override
    protected Class<?> getTargetClass() {
        return MyMainActivity.class;
    }
}
