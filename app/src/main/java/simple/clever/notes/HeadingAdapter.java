package simple.clever.notes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    private String[] noteHead;

    public HeadingAdapter(String[] noteHead) {
        this.noteHead = noteHead;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadingAdapter.ViewHolder holder, int position) {
    holder.getTextHead().setText(noteHead[position]);
    }

    @Override
    public int getItemCount() {
        return noteHead.length;
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void SetOnItemLongClickListener(OnItemLongClickListener itemLongClickListener){
        this.itemLongClickListener = itemLongClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textHead;
        private TextView textTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textHead = itemView.findViewById(R.id.note_name);
            textTime = itemView.findViewById(R.id.time);

            textHead.setOnClickListener(v -> {
                if(itemClickListener != null) itemClickListener.onItemClick(v,getAdapterPosition());
                });

            textHead.setOnLongClickListener(v -> {
                if(itemLongClickListener != null) {
                    itemLongClickListener.onItemClick(v,getAdapterPosition());
                    return true;
                }
                return false;
            });
        }

        public TextView getTextHead() {
            return textHead;
        }

        public TextView getTextTime() {
            return textTime;
        }
    }
}
