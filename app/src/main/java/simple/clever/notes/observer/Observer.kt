package simple.clever.notes.observer


import simple.clever.notes.data.CardData

interface Observer {
    fun updateCardData(cardData: CardData)
}
