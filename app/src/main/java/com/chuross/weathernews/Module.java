package com.chuross.weathernews;

import com.chuross.weathernews.util.DateProvider;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(DateProvider.class).toInstance(new DateProvider());
    }
}
