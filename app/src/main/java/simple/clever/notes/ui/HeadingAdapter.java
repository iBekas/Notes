package simple.clever.notes.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import simple.clever.notes.data.CardData;
import simple.clever.notes.data.CardSource;
import simple.clever.notes.data.OnItemClickListener;
import simple.clever.notes.data.OnItemLongClickListener;
import simple.clever.notes.R;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;
    private int position;
    private CardSource noteHead;

    public HeadingAdapter(CardSource noteHead) {
        this.noteHead = noteHead;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadingAdapter.ViewHolder holder, int position) {
    holder.setData(noteHead.getCardData(position));
    }

    @Override
    public int getItemCount() {
        return noteHead.size();
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void SetOnItemLongClickListener(OnItemLongClickListener itemLongClickListener){
        this.itemLongClickListener = itemLongClickListener;
    }

    public int getPosition() {
        return position;
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
                    position = getLayoutPosition();
                    itemLongClickListener.onItemClick(v,getAdapterPosition());
                    return true;
                }
                return false;
            });
        }

        public void setData(CardData cardData){
            textHead.setText(cardData.getHead());
            textTime.setText(cardData.getTimeOpen());
        }
    }
}
