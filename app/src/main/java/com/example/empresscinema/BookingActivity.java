package com.example.empresscinema;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        timeSpinner = findViewById(R.id.spinnerTimes);
        bookButton = findViewById(R.id.btnBook);

        db = FirebaseFirestore.getInstance();

        movieId = getIntent().getStringExtra("movie_id");

        // Dummy időpontok – később Firestore-ból is betölthető
        String[] times = {"14:00", "17:30", "20:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

        bookButton.setOnClickListener(v -> {
            String selectedTime = timeSpinner.getSelectedItem().toString();
            Map<String, Object> booking = new HashMap<>();
            booking.put("movie_id", movieId);
            booking.put("time", selectedTime);
            booking.put("user_id", "tesztfelhasznalo@example.com"); // itt használhatod FirebaseAuth.getCurrentUser().getEmail()

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
}
