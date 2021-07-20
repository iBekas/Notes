package simple.clever.notes.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import simple.clever.notes.R;
import simple.clever.notes.data.CardData;
import simple.clever.notes.data.CardSource;
import simple.clever.notes.data.OnItemClickListener;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private final Fragment fragment;
    private int position;
    private CardSource noteHead;

    public HeadingAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setDataSource(CardSource noteHead){
        this.noteHead = noteHead;
        notifyDataSetChanged();
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
            registerContextMenu(itemView);

            textHead.setOnClickListener(v -> {
                if(itemClickListener != null) itemClickListener.onItemClick(v,getAdapterPosition());
                });

            textHead.setOnLongClickListener(v -> {
                    position = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                    return true;
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(v -> {
                    position = getLayoutPosition();
                    return false;
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData cardData){
            textHead.setText(cardData.getHead());
            textTime.setText(cardData.getTimeOpen());
        }
    }
}
