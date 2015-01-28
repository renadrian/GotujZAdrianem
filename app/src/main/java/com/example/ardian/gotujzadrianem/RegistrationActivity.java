package com.example.ardian.gotujzadrianem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ardian.gotujzadrianem.Data.Registration;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Ardian on 2015-01-28.
 */
@EActivity(R.layout.registration)
public class RegistrationActivity extends ActionBarActivity {
    public void registerSuccess(User uzytkownik){ // void nic nie zwraca tylko wywoluje
        ringProgressDialog.dismiss();
        AddRecipe_.intent(this).user(uzytkownik).start();
        finish(); // user w klasie dodawanieprzepisu, uzytkownik funckji
    }
    public void registerError (Exception error){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Błąd przy rejestracji użytkownika", Toast.LENGTH_LONG).show();
        error.printStackTrace();
    }
    @Bean
    @NonConfigurationInstance // nie wywali apki po obroceniu ekranu
            RegistrationBackgroundTask registrationBackgroundTask; // tworzy sie nowy obiekt
    ProgressDialog ringProgressDialog; // nadanie zmiennej do obiektu
    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Trwa rejestracja nowego użytkownika, proszę czekać...");
        ringProgressDialog.setIndeterminate(true); // dopoki nie zgasimy funckja kolko ladowania bedzie sie krecilo
// if (user!=null) {DodawaniePrzepisu_.intent(this).user(user).start();
// finish();} // finish() zabija aktywnosc logowania jak juz jestesmy zalogowani
    }
    @ViewById
    EditText imie;
    @ViewById
    EditText nazwisko;
    @ViewById
    EditText email;
    @ViewById
    EditText haslo;
    @Click
    void buttonZarejestrujClicked(){
        Registration registration = new Registration();
        registration.first_name = imie.getText().toString();
        registration.last_name = nazwisko.getText().toString();
        registration.email = email.getText().toString();
        registration.new_password = haslo.getText().toString();
        ringProgressDialog.show();
        registrationBackgroundTask.registration(registration);
    }
}