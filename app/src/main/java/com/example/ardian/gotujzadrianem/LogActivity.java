package com.example.ardian.gotujzadrianem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ardian.gotujzadrianem.Data.AccPassword;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Ardian on 2015-01-28.
 */
@EActivity(R.layout.log_activity) //R - przez R odwolujemy sie do res (resources)
public class LogActivity extends ActionBarActivity {
    public void loginSuccess(User uzytkownik){ // void nic nie zwraca tylko wywoluje
        ringProgressDialog.dismiss();
// Toast.makeText(this, uzytkownik.sessionId, Toast.LENGTH_SHORT).show(); // wyswietli, ze logowanie sie udalo
        AddRecipe_.intent(this).user(uzytkownik).start(); // user w klasie dodawanieprzepisu, uzytkownik funckji
    }
    public void showError (Exception error){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Błąd logowania", Toast.LENGTH_LONG).show();
        error.printStackTrace();
    }
    @ViewById
    EditText email;
    @ViewById
    EditText haslo;
    @Bean
    @NonConfigurationInstance // nie wywali apki po obroceniu ekranu
            LogActivityBackgroundTask logActivityBackgroundTask;
    ProgressDialog ringProgressDialog; // koleczko ladowania
    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Trwa logowanie, proszę czekać...");
        ringProgressDialog.setIndeterminate(true); // dopoki nie zgasimy funckja kolko ladowania bedzie sie krecilo
        if (user!=null) {AddRecipe_.intent(this).user(user).start();
            finish();} // finish() zabija aktywnosc logowania jak juz jestesmy zalogowani
    }
    @Click
    void buttonLogowaniaClicked(){
        AccPassword accPassword=new AccPassword(); // wysyla pusty 'pojemnik' na serwer po dane, przypisuje dane do EmailHaslo
        accPassword.email=email.getText().toString(); // zamienia tekst, ktory byl w polu email na String i wpisuje go do pola obiektu emailHaslo
        accPassword.password=haslo.getText().toString(); // obiekty, ktore wysylamy na serwer musza miec takie same nazwy jak na serwerze
        ringProgressDialog.show();
        logActivityBackgroundTask.login(accPassword); //przejscie z aktywnosci do background
    }
    @Extra
    User user;
    @Click
    void buttonRejestracjaClicked(){
        RegistrationActivity_.intent(this).start();
        finish();
    }
}