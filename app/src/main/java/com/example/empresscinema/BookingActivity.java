package com.example.empresscinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {

    Spinner timeSpinner;
    Button bookButton;
    FirebaseFirestore db;
    String movieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Toolbar beállítása
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timeSpinner = findViewById(R.id.spinnerTimes);
        bookButton = findViewById(R.id.btnBook);
        db = FirebaseFirestore.getInstance();

        movieId = getIntent().getStringExtra("movie_id");

        // Egyedi spinner stílus
        String[] times = {"14:00", "17:30", "20:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item, // egyedi megjelenés
                times
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

        bookButton.setOnClickListener(v -> {
            String selectedTime = timeSpinner.getSelectedItem().toString();

            String userEmail = "tesztfelhasznalo@example.com";
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            }

            Map<String, Object> booking = new HashMap<>();
            booking.put("movie_id", movieId);
            booking.put("time", selectedTime);
            booking.put("user_id", userEmail);

            db.collection("bookings")
                    .add(booking)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Foglalás sikeres!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Hiba történt: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    // Menü
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.menu_tickets) {
            startActivity(new Intent(this, MyTicketsActivity.class));
            return true;
        } else if (id == R.id.menu_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Életciklus hook: újraindításkor toast
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Foglalási oldal elindult", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Foglalási oldal leáll", Toast.LENGTH_SHORT).show();
    }
}


