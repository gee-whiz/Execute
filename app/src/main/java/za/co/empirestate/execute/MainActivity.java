package za.co.empirestate.execute;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    AddFragment addFragment;
    Context  context;
    Toolbar toolbar;

    private Animator mCircularReveal;
    private String LOG = "hey Gee";
  private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.MainContainer,mainFragment)
                .commit();
     addFragment = new AddFragment();
        int[] colorIntArray = {R.color.blue,R.color.primary,R.color.accent,R.color.blue,R.color.gray_background};
   context  = getApplicationContext();



    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public  void startAddFragment(final View view){
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.MainContainer, addFragment)
                .commit();
        final View fragmentContainer = findViewById(R.id.MainContainer);
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;
        view.setBackgroundColor(getResources().getColor(R.color.purple));
        int finalRadius = Math.max(fragmentContainer.getWidth(), fragmentContainer.getHeight());
        mCircularReveal = ViewAnimationUtils.createCircularReveal(
                fragmentContainer, centerX, centerY, 0, finalRadius);
        fragmentContainer.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

        mCircularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                mCircularReveal.removeListener(this);
            }
        });

        mCircularReveal.start();

        // the toolbar should not have more elevation than the content while playing
        toolbar.setTitle("Add new ");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));

        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startMainFragment(final  View view)
    {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.MainContainer, mainFragment)
                .commit();
        final View fragmentContainer = findViewById(R.id.MainContainer);
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;
        view.setBackgroundColor(getResources().getColor(R.color.purple));
        int finalRadius = Math.max(fragmentContainer.getWidth(), fragmentContainer.getHeight());
        mCircularReveal = ViewAnimationUtils.createCircularReveal(
                fragmentContainer, centerX, centerY, 0, finalRadius);
        fragmentContainer.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

        mCircularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                mCircularReveal.removeListener(this);
            }
        });

        mCircularReveal.start();

        // the toolbar should not have more elevation than the content while playing
        toolbar.setElevation(0);
        toolbar.setTitle("Execute");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
