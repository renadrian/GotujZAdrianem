package com.example.ardian.gotujzadrianem;

import com.example.ardian.gotujzadrianem.Data.AccPassword;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Ardian on 2015-01-28.
 */
@EBean
public class LogActivityBackgroundTask {
    @RootContext
    LogActivity activity; // aktywnosc, w ktorej bedziemy wykorzystywac logowanie
    @RestService
    RecipeRestClient restClient;
    @Background
    void login(AccPassword accPassword) { // wywolujemy logowanie podajac dane, pierwsze podaje sie typ, drugie nazwe (dowolna)
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook"); // wymaga naglowka i nazwa aplikacji
            User user = restClient.login(accPassword); // wywoluje restClient, ktory bezposrednio laczy sie z serwerem; serwer wczesniej pobiera id i sessionid, tworzy nowy obiekt User, przypisuje mu te dane, zwraca do restClient
            publishResult(user);
        } catch (Exception error) { // zabezpiecza przed bledem ze strony serwera
            publishError(error);
        }
    }
    @UiThread
        // przejscie z resta do backgroundtask
    void publishResult(User user) { // przekaze obiekt z background do aktywnosci, majac w aktywnosci mozemy przekazac dalej jako intencja do wyswietlenia
        activity.loginSuccess(user);
    }
    @UiThread
    void publishError(Exception error) {
        activity.showError(error);
    }
}