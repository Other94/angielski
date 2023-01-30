package pl.example.angielski2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, english, polish, score;
    private OnButtonClickListener listener;
    public MyAdapter( ArrayList id, ArrayList english, ArrayList polish, ArrayList score, OnButtonClickListener listener) {
        this.context = context;
        this.id = id;
        this.english = english;
        this.polish = polish;
        this.score = score;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.english.setText(String.valueOf(english.get(position)));
        holder.polish.setText(String.valueOf(polish.get(position)));
        holder.score.setText(String.valueOf(score.get(position)));
        holder.imageButton.setOnClickListener(v -> listener.onButtonClick(position));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, english, polish, score;
        ImageButton imageButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            english = itemView.findViewById(R.id.textEng);
            polish = itemView.findViewById(R.id.textPl);
            score = itemView.findViewById(R.id.textScore);
            imageButton = itemView.findViewById(R.id.myImageButton);
        }
    }
}
