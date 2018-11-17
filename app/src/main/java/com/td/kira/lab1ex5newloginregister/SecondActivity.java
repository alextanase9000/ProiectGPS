package com.td.kira.lab1ex5newloginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout, bet, newMatch;
    private TextView firstTeam, cota1, secondTeam, cota2, score, buget, cashWon;
    private Switch aSwitch;
    private EditText miza;
    static float cota1_new, cota2_new;

    private FirebaseDatabase firebaseDatabase;

    private String won[] = {"First team won", "Second team won"};

    private String echipe[] = {"Steaua", "FC Barcelona", "Athletic Club", "Atlético de Madrid",
            "Getafe CF", "Real Madrid C.F.", "FC Baia Mare", "Villarreal CF", "Valencia CF",
            "Sevilla FC"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        logout = (Button) findViewById(R.id.btnLogOut);
        bet = (Button) findViewById(R.id.btnBet);
        newMatch = (Button) findViewById(R.id.btnNewMatch);

        firstTeam = (TextView) findViewById(R.id.tvFirstTeam);
        secondTeam = (TextView) findViewById(R.id.tvSecondTeam);
        cota1 = (TextView) findViewById(R.id.tvCota1);
        cota2 = (TextView) findViewById(R.id.tvCota2);
        score = (TextView) findViewById(R.id.tvScore);
        buget = (TextView) findViewById(R.id.tvBuget);
        cashWon = (TextView) findViewById(R.id.tvCashWon);

        aSwitch = (Switch) findViewById(R.id.swBet);

        miza = (EditText) findViewById(R.id.etMiza);

        buget.setText("Buget: " + UserProfile.getUserBuget());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });


        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i, j;
                Random rand = new Random();
                i = rand.nextInt(10);
                j = rand.nextInt(10);
                while (i == j) {
                    j = rand.nextInt(10);
                }
                firstTeam.setText(echipe[i]);
                secondTeam.setText(echipe[j]);
                cota1_new = rand.nextFloat() * 10 + 1;
                cota2_new = rand.nextFloat() * 10 + 1;
                cota1.setText("Cota: " + String.valueOf(cota1_new));
                cota2.setText("Cota: " + String.valueOf(cota2_new));
                buget.setText("Buget: " + UserProfile.getUserBuget());
            }
        });

        bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int i = rand.nextInt(2);
                score.setText(won[i]);

                if (aSwitch.isChecked()) {
                    if (i == 1) {
                        //won
                        if (validate()) {
                            String miza_user = miza.getText().toString().trim();
                            float castig = Float.valueOf(miza_user) * Float.valueOf(cota2_new);
                            final float buget_nou = castig + Float.valueOf(UserProfile.getUserBuget());
                            buget.setText("Buget: " + String.valueOf(buget_nou));
                            cashWon.setText("Cash won: " + castig);

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                                    userProfile.setUserBuget(String.valueOf(buget_nou));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }


                    } else {
                        //lose
                        if (validate()) {
                            String miza_user = miza.getText().toString().trim();
                            float castig = Float.valueOf(miza_user);
                            final float buget_nou = Float.valueOf(UserProfile.getUserBuget()) - castig;
                            buget.setText("Buget: " + String.valueOf(buget_nou));
                            cashWon.setText("Cash lost: " + miza_user);

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                                    userProfile.setUserBuget(String.valueOf(buget_nou));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                } else {
                    if (i == 0) {
                        //won
                        if (validate()) {
                            String miza_user = miza.getText().toString().trim();
                            float castig = Float.valueOf(miza_user) * Float.valueOf(cota1_new);
                            final float buget_nou = castig + Float.valueOf(UserProfile.getUserBuget());
                            buget.setText("Buget: " + String.valueOf(buget_nou));
                            cashWon.setText("Cash won: " + castig);

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                                    userProfile.setUserBuget(String.valueOf(buget_nou));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    } else {
                        //lose
                        if (validate()) {
                            String miza_user = miza.getText().toString().trim();
                            float castig = Float.valueOf(miza_user);
                            final float buget_nou = Float.valueOf(UserProfile.getUserBuget()) - castig;
                            buget.setText("Buget: " + String.valueOf(buget_nou));
                            cashWon.setText("Cash lost: " + miza_user);

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                                    userProfile.setUserBuget(String.valueOf(buget_nou));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }
            }
        });
    }

    private Boolean validate() {
        Boolean result = false;

        String miza_validate = miza.getText().toString();

        if (miza_validate.isEmpty()) {
            Toast.makeText(this, "Please enter miza", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }


    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())) {
            case R.id.logoutMenu: {
                Logout();
            }
            case R.id.profileMenu: {
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
            }
            case R.id.GPSMenu: {
                startActivity(new Intent(SecondActivity.this, GPSActivity.class));
            }
        }
            return super.onOptionsItemSelected(item);
    }
}

