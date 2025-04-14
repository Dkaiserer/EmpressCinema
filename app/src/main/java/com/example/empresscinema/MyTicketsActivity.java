package com.example.empresscinema;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresscinema.adapter.TicketAdapter;
import com.example.empresscinema.model.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private List<Ticket> ticketList;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        recyclerView = findViewById(R.id.recyclerTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ticketList = new ArrayList<>();
        ticketAdapter = new TicketAdapter(ticketList);
        recyclerView.setAdapter(ticketAdapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        loadUserTickets();
    }

    private void loadUserTickets() {
        String userEmail = auth.getCurrentUser().getEmail();

        db.collection("bookings")
                .whereEqualTo("user_id", userEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ticketList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Ticket ticket = doc.toObject(Ticket.class);
                        ticketList.add(ticket);
                    }
                    ticketAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Hiba a foglalások betöltésekor", Toast.LENGTH_SHORT).show();
                });
    }
}