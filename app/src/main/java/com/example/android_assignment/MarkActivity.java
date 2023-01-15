package com.example.android_assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MarkActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button failed, approved;
    EditText titleEdt, markEdt, commentEdt;
    Button navHome, navMark, navWork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        findViews();
        setListeners();
        navMark = findViewById(R.id.navMark);
        navHome = findViewById(R.id.navHome);
        navWork = findViewById(R.id.navWork);
        Button logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MarkActivity.this, LoginActivity.class));
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, supervisor.class));
            }
        });

        navWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, WorkSup.class));
            }
        });

        navMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, MarkActivity.class));
            }
        });
    }

    private void findViews() {
        titleEdt = findViewById(R.id.titleInput);
        markEdt = findViewById(R.id.markInput);
        commentEdt = findViewById(R.id.commentInput);
        failed = findViewById(R.id.upload_btn_reject);
        approved = findViewById(R.id.upload_btn_approve);
    }

    private void setListeners() {


        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTitle = titleEdt.getText().toString().trim();
                String myMark = markEdt.getText().toString().trim();
                String myComment = commentEdt.getText().toString().trim();

                if (myTitle.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the title.", Toast.LENGTH_SHORT).show();
                }else if (myMark.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the mark.", Toast.LENGTH_SHORT).show();
                }else if (myComment.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the comment.", Toast.LENGTH_SHORT).show();
                }else{
                    String dbTitle = "Title";
                    String dbMark = "Mark";
                    String dbComment = "Comment";
                    String dbStatus = "Status";

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference(" Evaulation of " + myTitle);

                    databaseReference.child(dbStatus).setValue("Approved").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            }else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbTitle).setValue(myTitle).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                titleEdt.setText("");
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbMark).setValue(myMark).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                markEdt.setText("");
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbComment).setValue(myComment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                commentEdt.setText("");
                                Toast.makeText(MarkActivity.this, "Evaluation done", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTitle = titleEdt.getText().toString().trim();
                String myMark = markEdt.getText().toString().trim();
                String myComment = commentEdt.getText().toString().trim();

                if (myTitle.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the title.", Toast.LENGTH_SHORT).show();
                }else if (myMark.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the mark.", Toast.LENGTH_SHORT).show();
                }else if (myComment.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Please enter the comment.", Toast.LENGTH_SHORT).show();
                }else{
                    String dbTitle = "Title";
                    String dbMark = "Mark";
                    String dbComment = "Comment";
                    String dbStatus = "Status";

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference(" Evaulation of " + myTitle);

                    databaseReference.child(dbStatus).setValue("Failed").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            }else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbTitle).setValue(myTitle).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                titleEdt.setText("");
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbMark).setValue(myMark).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                markEdt.setText("");
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbComment).setValue(myComment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                commentEdt.setText("");
                                Toast.makeText(MarkActivity.this, "Evaluation done", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MarkActivity.this,supervisor.class);
                        intent.putExtra("finish", true); // if you are checking for this in your other Activities
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}





