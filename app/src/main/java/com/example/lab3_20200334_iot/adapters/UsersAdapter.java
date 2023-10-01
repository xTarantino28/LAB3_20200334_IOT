package com.example.lab3_20200334_iot.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab3_20200334_iot.R;
import com.example.lab3_20200334_iot.pojos.Result;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
    public UsersAdapter(List<Result> listUsers, Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }

    public void setListUsers(List<Result> listUsers) {
        this.listUsers = listUsers;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Result> getListUsers() {
        return listUsers;
    }

    private List<Result> listUsers;
    private Context context;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{

        Result user;

        public UserViewHolder(@NonNull View itemView,OnItemClickListener listener) {

            super(itemView);
            ImageView close = itemView.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new UserViewHolder(view,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Result u = listUsers.get(position);
        holder.user = u;

        TextView textViewNombre = holder.itemView.findViewById(R.id.nombre);
        String nombre = u.getName().getTitle()+ " " + u.getName().getFirst() + " " + u.getName().getLast();
        textViewNombre.setText(nombre);

        TextView genero = holder.itemView.findViewById(R.id.genero);
        genero.setText("Genero: " +  u.getGender());

        TextView ciudad = holder.itemView.findViewById(R.id.ciudad);
        ciudad.setText("Ciudad: " + u.getLocation().getCity());

        TextView pais = holder.itemView.findViewById(R.id.pais);
        pais.setText("Pais: " + u.getLocation().getCountry());

        TextView email = holder.itemView.findViewById(R.id.email);
        email.setText("Email: " +u.getEmail());

        TextView phone = holder.itemView.findViewById(R.id.phone);
        phone.setText("Phone: " +u.getPhone());

        String urlAvatar = u.getPicture().getThumbnail();
        ImageView fotoPerfil = holder.itemView.findViewById(R.id.fotoPerfil);
        Glide.with(context).load(urlAvatar).into(fotoPerfil);

        ImageView close = holder.itemView.findViewById(R.id.close);
        close.setImageResource(R.drawable.baseline_close_24);
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }





}
