package simple.clever.notes.data;

public interface CardSource {
    CardData getCardData(int position);
    int size();
    void addCardData(CardData cardData);
    void deleteCardData(int position);
    void updateCardData(CardData cardData, int position);


}
