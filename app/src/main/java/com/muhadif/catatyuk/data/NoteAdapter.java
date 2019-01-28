package com.muhadif.catatyuk.data;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhadif.catatyuk.FormAddUpdateActivity;
import com.muhadif.catatyuk.R;
import com.muhadif.catatyuk.entity.Note;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LinkedList<Note> listNotes;
    private Activity activity;

    public NoteAdapter(Activity activity, LinkedList linkedList) {
        this.activity = activity;

    }

    public LinkedList<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(LinkedList<Note> listNotes) {
        this.listNotes = listNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bindItem(activity, listNotes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        View iV;
        Activity activity;
        Note note;
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iV = itemView;
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_title);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_item_description);
            tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote = (CardView)itemView.findViewById(R.id.cv_item_note);

        }

        public void bindItem(final Activity activity, final Note note, final int position){
            tvTitle.setText(note.getTitle());
            tvDescription.setText(note.getDescription());
            tvDate.setText(note.getDate());
            iV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                    intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);

                    intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, note);
                    activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
                }
            });
//            iV.OnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
//                @Override
//                public void onItemClick(View view, int position) {

//                }
//            }));
        }
    }
}
