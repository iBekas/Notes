package simple.clever.notes.observer;


import simple.clever.notes.data.CardData;

public interface Observer {
    void updateCardData(CardData cardData);
}
