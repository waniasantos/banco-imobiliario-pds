package com.bancoimobiliario.observer;

public interface Subject {
    void adicionarObserver(Observer o);
    void removerObserver(Observer o);
    void notificarObservers();
}