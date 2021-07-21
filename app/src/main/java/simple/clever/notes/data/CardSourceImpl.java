package simple.clever.notes.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import simple.clever.notes.R;

public class CardSourceImpl implements CardSource{

    private List<CardData> dataSource;
    private Resources resources;

    public CardSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public CardSource init(CardSourceResponse cardSourceResponse){
        String[] heads = resources.getStringArray(R.array.heading);
        for (int i = 0; i < heads.length; i++) {
            dataSource.add(new CardData(heads[i], Calendar.getInstance().getTime()));
        }
        if (cardSourceResponse != null){
            cardSourceResponse.initialized(this);
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

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(CardData cardData, int position) {
        dataSource.set(position, cardData);
    }


}
