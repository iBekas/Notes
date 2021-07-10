package simple.clever.notes.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import simple.clever.notes.R;

public class CardSourceImpl implements CardSource{

    private List<CardData> dataSource;
    private Resources resources;

    public CardSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public CardSourceImpl init(){
        String[] heads = resources.getStringArray(R.array.heading);
        String[] timesOpen = resources.getStringArray(R.array.time_open);
        for (int i = 0; i < heads.length; i++) {
            dataSource.add(new CardData(heads[i], timesOpen[i]));
        }
        return this;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
