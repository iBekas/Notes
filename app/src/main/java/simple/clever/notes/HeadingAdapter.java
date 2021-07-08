package simple.clever.notes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import simple.clever.notes.CardData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    private final static String TAG = "HeadingAdapter";
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

        public void setData(CardData cardData){
            textHead.setText(cardData.getHead());
            textTime.setText(cardData.getTimeOpen());
        }
    }
}
