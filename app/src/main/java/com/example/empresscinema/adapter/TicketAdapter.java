package com.example.empresscinema.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresscinema.R;
import com.example.empresscinema.model.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> ticketList;

    public TicketAdapter(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.textMovieId.setText("Film ID: " + ticket.getMovieId());
        holder.textShowtime.setText("Időpont: " + ticket.getShowtime());
        holder.textSeat.setText("Hely: " + ticket.getSeatNumber());
        holder.textUser.setText("Felhasználó: " + ticket.getUserId());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView textMovieId, textShowtime, textSeat, textUser;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            textMovieId = itemView.findViewById(R.id.textMovieId);
            textShowtime = itemView.findViewById(R.id.textShowtime);
            textSeat = itemView.findViewById(R.id.textSeat);
            textUser = itemView.findViewById(R.id.textUser);
        }
    }
}
