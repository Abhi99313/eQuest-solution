package com.example.mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<Users> userList;
    private final Map<Integer, Boolean> expandMap;

    public UserAdapter(List<Users> userList) {
        this.userList = userList;
        this.expandMap = new HashMap<>();
        initExpandMap();
    }

    private void initExpandMap() {
        for (int i = 0; i < userList.size(); i++) {
            expandMap.put(i, false);
        }
    }

    public void updateData(List<Users> newData) {
        userList.clear();
        userList.addAll(newData);
        initExpandMap();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView nameTextView;
        TextView emailTextView;
        TextView genderTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView2);
            nameTextView = itemView.findViewById(R.id.nameTextView2);
            emailTextView = itemView.findViewById(R.id.tvEmail);
            genderTextView = itemView.findViewById(R.id.tvGender);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        boolean expanded = Boolean.FALSE.equals(expandMap.get(position));
                        expandMap.put(position, expanded);
                        notifyItemChanged(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users user = userList.get(position);
        String avatarUrl = user.getAvatarUrl();

        if (avatarUrl != null && !avatarUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.user_img)
                    .error(R.drawable.error_img)
                    .into(holder.avatarImageView);
        } else {

            holder.avatarImageView.setImageResource(R.drawable.user_img);
        }

        String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        holder.nameTextView.setText(fullName);

        boolean expanded = Boolean.TRUE.equals(expandMap.get(position));
        holder.emailTextView.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.genderTextView.setVisibility(expanded ? View.VISIBLE : View.GONE);

        if (expanded) {
            holder.emailTextView.setText("Email: " + user.getEmail());
            holder.genderTextView.setText("Gender: " + user.getGender());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
