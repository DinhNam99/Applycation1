package com.dell.application1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.dell.application1.R;
import com.dell.application1.model.People;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    ArrayList<People> peopleList;
    Context context;
    LayoutInflater inflater;
    ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
    String anplabet;

    public Adapter(Context context, ArrayList<People> peopleList){
        this.context = context;
        this.peopleList = peopleList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.item_rc,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        People people = peopleList.get(position);
        holder.tvName.setText(people.getName()+"");
        holder.tvPhoneNumber.setText(people.getPhoneNumber()+"");
        holder.tvActive.setText(people.getActive()+"");
        anplabet = String.valueOf(people.getName().charAt(0));
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(anplabet, colorGenerator.getRandomColor());

        holder.avatar.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView tvName, tvPhoneNumber, tvActive;
        public Holder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            tvActive = itemView.findViewById(R.id.active);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
