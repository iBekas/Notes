package simple.clever.notes.data

interface CardSource {
    fun init(cardSourceResponse: CardSourceResponse): CardSource
    fun getCardData(position: Int): CardData
    fun size(): Int
    fun addCardData(cardData: CardData)
    fun deleteCardData(position: Int)
    fun updateCardData(cardData: CardData,position: Int)


}
