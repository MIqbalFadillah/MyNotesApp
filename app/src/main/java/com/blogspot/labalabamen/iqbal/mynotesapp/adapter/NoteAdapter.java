package com.blogspot.labalabamen.iqbal.mynotesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.labalabamen.iqbal.mynotesapp.CustomOnItemClickListener;
import com.blogspot.labalabamen.iqbal.mynotesapp.FormAddUpdateActivity;
import com.blogspot.labalabamen.iqbal.mynotesapp.R;
import com.blogspot.labalabamen.iqbal.mynotesapp.entity.Note;

import java.util.LinkedList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder> {
    private LinkedList<Note> listNotes;
    private Activity activity;

    public NoteAdapter(Activity activity){
        this.activity = activity;
    }

    public LinkedList<Note> getLinkedNotes(){
        return listNotes;
    }

    public void setListNotes(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
    }


    @Override
    public NoteViewholder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewholder(view);
    }

    public class NoteViewholder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;

        public NoteViewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_title);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_item_description);
            tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote = (CardView)itemView.findViewById(R.id.cv_item_note);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewholder holder, int position) {
        holder.tvTitle.setText(getLinkedNotes().get(position).getTitle());
        holder.tvDescription.setText(getLinkedNotes().get(position).getDescription());
        holder.tvDate.setText(getLinkedNotes().get(position).getDate());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getLinkedNotes().get(position));
                activity.startActivityForResult(intent,FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return getLinkedNotes().size();
    }



}
