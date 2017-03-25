
package com.fixit.auto.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class product extends AppCompatActivity implements OnItemSelected{
    RecyclerView recyclerView;
    List<GeoPoint> geoMYPointBackendlessCollection;
    List<product_singledesc> mproduct_singledescList=new ArrayList<product_singledesc>();
    ProductAdapter mproductAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        recyclerView = (RecyclerView) findViewById(R.id.myrecycler_view);
        product_singledesc mProduct_singledesc = new product_singledesc();
        mProduct_singledesc.setMyadress("a");
        mProduct_singledesc.setMyservicecenter("b");
        mProduct_singledesc.setMycost("100");
        mProduct_singledesc.setMydistance("50");
        mproductAdapter = new ProductAdapter(product.this, mProduct_singledesc,mproduct_singledescList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mproductAdapter);
        addlocation();
    }

    private void addlocation() {
        List<String> category=new ArrayList<String>();
        category.add("car");
        Map<String,Object> metadata=new HashMap<String, Object>();
        metadata.put("service_centre","HAR HAR MAHADEV");
        metadata.put("adress","KIIT,Bhubaneswar,India");
        metadata.put("tier_cost","1000");
        BackendlessGeoQuery backendlessGeoQuery=new BackendlessGeoQuery();
        backendlessGeoQuery.addCategory("car");
        backendlessGeoQuery.putMetadata("tier_cost","1000");
        backendlessGeoQuery.setIncludeMeta( true );
        Backendless.Geo.getPoints(backendlessGeoQuery, new AsyncCallback<BackendlessCollection<GeoPoint>>() {
            @Override
            public void handleResponse(BackendlessCollection<GeoPoint> geoPointBackendlessCollection) {
            geoMYPointBackendlessCollection = geoPointBackendlessCollection.getCurrentPage();
                addtolist(geoMYPointBackendlessCollection);
            }
            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
     /*   Backendless.Geo.savePoint(20.355259, 85.819462, category, metadata, new AsyncCallback<GeoPoint>() {
            @Override
            public void handleResponse(GeoPoint geoPoint) {
                Toast.makeText(product.this,"geopoints saved",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(product.this,"sorry fault occured",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    private void addtolist(List<GeoPoint> geoMYPointBackendlessCollection) {
        product_singledesc mProduct_singledesc = new product_singledesc();
        Map<String, Object> mymetadata;
        for (int i=0;i<geoMYPointBackendlessCollection.size();i++) {
                mymetadata = geoMYPointBackendlessCollection.get(i).getMetadata();
                mProduct_singledesc = new product_singledesc();
                mProduct_singledesc.setMyadress(mymetadata.get("adress")+"");
                mProduct_singledesc.setMycost(mymetadata.get("tier_cost")+"");
                mProduct_singledesc.setMyservicecenter(mymetadata.get("service_centre")+"");
                mproduct_singledescList.add(mProduct_singledesc);
            }
        mproductAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int position) {
        startActivity(new Intent(product.this,MapsWaliActivity.class));
    }
}