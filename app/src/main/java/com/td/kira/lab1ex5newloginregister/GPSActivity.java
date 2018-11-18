package com.td.kira.lab1ex5newloginregister;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class GPSActivity extends AppCompatActivity {

    private Button b;
    private TextView t;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth= FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());
        t = (TextView) findViewById(R.id.textField);
        b = (Button) findViewById(R.id.button);

        ActivityCompat.requestPermissions(GPSActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        b.setOnClickListener(new View.OnClickListener() {

            GpsTracker gt = new GpsTracker(getApplicationContext());
            Location l = gt.getLocation();
            @Override
            public void onClick(View v) {

                l = gt.getLocation();

                if( l == null){
                    Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
                }else {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    final Map<String, Double> locationMap = new HashMap<>();
                    locationMap.put("Longitudine",lat);
                    locationMap.put("Latitudine",lon);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                             userProfile = new UserProfile(userProfile.userAge, userProfile.userEmail, userProfile.userName, userProfile.userBuget, locationMap);
                            databaseReference.setValue(userProfile);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    t.setText("Longitudine = " + lat + "\n" + "Latitudine = " + lon);
                    Toast.makeText(getApplicationContext(),"Lat = "+lat+"\n lon = "+lon,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
