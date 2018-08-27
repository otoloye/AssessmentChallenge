package otoloye.com.assessmentchallenge;

import android.app.Application;

import otoloye.com.assessmentchallenge.dagger.AppComponent;
import otoloye.com.assessmentchallenge.dagger.AppModule;
import otoloye.com.assessmentchallenge.dagger.DaggerAppComponent;

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule("https://newsapi.org/v2/"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
