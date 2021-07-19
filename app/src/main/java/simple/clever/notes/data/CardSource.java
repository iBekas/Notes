package simple.clever.notes.data;

public interface CardSource {
    CardSource init(CardSourceResponse cardSourceResponse);
    CardData getCardData(int position);
    int size();
    void addCardData(CardData cardData);
    void deleteCardData(int position);
    void updateCardData(CardData cardData, int position);


}
