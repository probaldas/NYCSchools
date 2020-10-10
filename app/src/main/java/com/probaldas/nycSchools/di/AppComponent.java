package com.probaldas.nycSchools.di;

import com.probaldas.nycSchools.NYCApplication;
import com.probaldas.nycSchools.repositories.ApiDataSourceFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(NYCApplication application);

    void inject(ApiDataSourceFactory apiDataSourceFactory);

}
