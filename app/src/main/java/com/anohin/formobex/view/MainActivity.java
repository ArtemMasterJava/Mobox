package com.anohin.formobex.view;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anohin.formobex.Main2Activity;
import com.anohin.formobex.OnFlowerwListener;
import com.anohin.formobex.R;
import com.anohin.formobex.controller.Controller;
import com.anohin.formobex.model.adapter.FlowersAdapter;
import com.anohin.formobex.model.pojo.Flower;
import com.anohin.formobex.view.service.AlarmReceiver;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import static com.anohin.formobex.R.id.swipe;

public class MainActivity extends AppCompatActivity implements Controller.FlowerCallbackListener {

    private static final int INTERVAL = 2000 * 60;
    private static final int DELAY = 500;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Flower> mFlowerList = new ArrayList<>();
    private FlowersAdapter mFlowersAdapter;
    private Controller mController;
    private Context mContext;
    private SwipeLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        Intent alarm = new Intent(this.mContext, AlarmReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this.mContext, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmRunning) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.mContext, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, DELAY, INTERVAL, pendingIntent);
        }
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();

    }

    private void configToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configViews() {
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(swipe);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mFlowersAdapter = new FlowersAdapter(mFlowerList);
        recyclerView.setAdapter(mFlowersAdapter);
        mFlowersAdapter.setListener(new OnFlowerwListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFlowerSelected(View view, Flower flower) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, view, "profile");
                intent.putExtra("image", flower.mPhoto);
                intent.putExtra("info", flower.mInstructions);

                MainActivity.this.startActivity(intent, options.toBundle());
            }
        });

        //noinspection deprecation
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Flower flower) {
        mFlowersAdapter.addFlower(flower);
    }

    @Override
    public void onFetchProgress(List<Flower> flowerList) {

    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }

    private CardView findCardView(View view){
        CardView cardView = null;
        View parent = (View) view.getParent();

        do {
            if (parent instanceof  CardView){
                cardView = (CardView) parent;
            }else{
                parent = (View) parent.getParent();
            }
        }while (cardView == null);

        return cardView;
    }

    private int findPosition(View view){
        int position = 0;
        CardView cardView = findCardView(view);
        RecyclerView recyclerView = (RecyclerView) cardView.getParent();
        position = recyclerView.getChildAdapterPosition(cardView);
        return position;
    }

    public void onClickTrash(View view) {
        int position = findPosition(view);
        mFlowersAdapter.deleteItem(position);
    }

    public void onClickMagnifier(View view) {
        Toast.makeText(MainActivity.this, "Click to image for details", Toast.LENGTH_SHORT).show();

    }
}
