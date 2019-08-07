package id.foodbang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import id.foodbang.fragment.DeliveryFragment;
import id.foodbang.fragment.HomeFragment;
import id.foodbang.fragment.NotificationFragment;
import id.foodbang.fragment.ProfileFragment;

public class NavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigation.getMenu().getItem(0).setChecked(true));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                getSupportActionBar().setTitle("Home");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_content, new HomeFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                return true;
            case R.id.navigation_list:
                getSupportActionBar().setTitle("My Booking");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_content, new DeliveryFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                return true;
            case R.id.navigation_notifications:
                getSupportActionBar().setTitle("Notification");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_content, new NotificationFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                return true;
            case R.id.navigation_profile:
                getSupportActionBar().setTitle("Profile");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_content, new ProfileFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                return true;
        }
        return false;
    }
}
