package otoloye.com.assessmentchallenge.dagger;

import javax.inject.Singleton;

import dagger.Component;
import otoloye.com.assessmentchallenge.controller.ArticleDetailActivity;
import otoloye.com.assessmentchallenge.controller.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
     void inject(ArticleDetailActivity detailActivity);
}
