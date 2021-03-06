package com.fixit.auto.fixit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;
import java.util.ArrayList;
import java.util.List;
public class MyMainActivity extends AppCompatActivity implements OnPageItemSelectedListener,
        OnGridItemSelectedListener
        , SwipeRefreshLayout.OnRefreshListener{
    ViewGroup mContainerToolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    MainAdapter adapter;
    GridLayoutManager layoutManager;
    BackendlessCollection <ImageEntity> item_nameBackendlessCollection=new BackendlessCollection<ImageEntity>();
    private List<ImageEntity> item_names_List = new ArrayList<>();
    List<String> stringList=new ArrayList<String>();
    List<String> stringListurl=new ArrayList<String>();
    FastScrollRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.BLACK,Color.GREEN);
        mContainerToolbar= (ViewGroup) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);

        recyclerView = (FastScrollRecyclerView) findViewById(R.id.recycler_view);
        adapter = new MainAdapter(this, R.array.icons,R.array.iconsdesc,item_names_List, stringList,stringListurl);
        layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isPositionHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(this, 2, 2, 2, 2));
        recyclerView.setAdapter(adapter);
        BackendlessDataQuery backendlessGeoQuery=new BackendlessDataQuery();

        Backendless.Data.of(ImageEntity.class).find(new AsyncCallback<BackendlessCollection<ImageEntity>>(){
            @Override
            public void handleResponse(BackendlessCollection<ImageEntity> response) {
                addmoreitem(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


 /*       Backendless.Data.of(Item_Name.class).find(backendlessGeoQuery,
                new DefaultCallback<BackendlessCollection<Item_Name>>(MyMainActivity.this){
                    @Override
                    public void handleResponse(BackendlessCollection<Item_Name> response) {
                        super.handleResponse(response);
                        addmoreitem(response);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        super.handleFault(fault);
                    }
                });*/




        /*  Item_Name item_name=new Item_Name();
        for(int i=0;i<8;i++) {
            item_name.setItem_name("donut");
            Backendless.Persistence.save(item_name, new DefaultCallback<Item_Name>(MyMainActivity.this) {
                @Override
                public void handleResponse(Item_Name response) {
                    super.handleResponse(response);
                    Toast.makeText(MyMainActivity.this, "saved sexfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    super.handleFault(fault);
                    Toast.makeText(MyMainActivity.this, "NOT saved sexfully", Toast.LENGTH_LONG).show();
                }
            });
        }*/




    }


    /*private void addmoreitem(BackendlessCollection<Item_Name> response) {
        item_names_List.addAll(response.getCurrentPage());
        for(int i=0;i<item_names_List.size();i++)
            stringList.add(item_names_List.get(i).getItem_name());
        adapter.notifyDataSetChanged();
    }*/




    private void addmoreitem(BackendlessCollection<ImageEntity> response) {
        item_names_List.addAll(response.getCurrentPage());
        for(int i=0;i<item_names_List.size();i++){
            stringList.add(""+item_names_List.get(i).getUploaded());
            stringListurl.add(item_names_List.get(i).getUrl());
            adapter.notifyDataSetChanged();
        }

    }

    public void refreshStuff(){

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(this, 2, 2, 2, 2));
        // recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(MyMainActivity.this).color(Color.RED).build());
        // recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(MyMainActivity.this).color(Color.RED).build());
        recyclerView.setAdapter(adapter);

        if(swipeRefreshLayout.isRefreshing()==true) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onPagerItemClick(View view, int position) {

    }

    @Override
    public void onGridItemClick(View v, int position) {
        startActivity(new Intent(MyMainActivity.this,Vichicle.class));
    }

    @Override
    public void onRefresh() {
        final MainAdapter adapter = new MainAdapter(this, R.array.icons,R.array.iconsdesc, item_names_List, stringList, stringList);
        refreshStuff();
        swipeRefreshLayout.setRefreshing(false);
    }
}