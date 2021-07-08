package simple.clever.notes;

public interface CardSource {
    CardData getCardData(int position);
    int size();
}
