package simple.clever.notes.observer

import java.util.ArrayList

import simple.clever.notes.data.CardData


class Publisher {
    private val observers: ArrayList<Observer> = ArrayList()   // Все обозреватели

    // Подписать
    fun subscribe(observer: Observer) {
        observers.add(observer)
    }

    // Отписать
    private fun unsubscribe(observer: Observer) {
        observers.remove(observer)
    }

    // Разослать событие
    fun notifySingle(cardData: CardData) {
        for (observer in observers) {
            observer.updateCardData(cardData)
            unsubscribe(observer)
        }
    }

}
