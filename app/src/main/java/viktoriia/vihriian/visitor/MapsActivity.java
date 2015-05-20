package viktoriia.vihriian.visitor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import internet.ConnectionDetector;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import ui.parts.AlertDialogManager;

public class MapsActivity extends AppCompatActivity implements MaterialTabListener{
    Fragment mFragment;
    MaterialTabHost materialTabHost;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    FragmentTransaction transaction;
    ConnectionDetector connection;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setToolbar();

        mContext = getApplicationContext();
        connection = new ConnectionDetector(mContext);
        materialTabHost = (MaterialTabHost)findViewById(R.id.material_tab_host);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                    materialTabHost.setSelectedNavigationItem(position);
                    viewPagerAdapter.getItem(position);

                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, mFragment);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
            materialTabHost.addTab(
                    materialTabHost.newTab()
                            .setIcon(getIcon(i))
                            .setTabListener(this)
            );
        }

        if(mFragment == null) {
            mFragment = MapFragment.newInstance();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, mFragment);

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Visitor");
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public Fragment getItem(int num) {
            switch(num) {
                case 0:
                    mFragment = MapFragment.newInstance();
                    break;
                case 1:
                    mFragment = PlacesListFragment.newInstance();
                    break;
                default:
                    Log.d("Tabs - GetItem", "Wrong position");
                    break;
            }
            return mFragment;
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "tab";
        }
    }


    /*
    * It doesn't matter the color of the icons, but they must have solid colors
    */
    private Drawable getIcon(int position) {
        Drawable icon = null;
        switch(position) {
            case 0:
                icon = ContextCompat.getDrawable(this, R.mipmap.ic_map_tab);
                break;
            case 1:
                icon = ContextCompat.getDrawable(this, R.mipmap.ic_list_tab);
                break;
            default:
                break;
        }
        return icon;
    }
}
