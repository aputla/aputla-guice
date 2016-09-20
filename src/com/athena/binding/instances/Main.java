package com.athena.binding.instances;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DisplayModule());
        
        Displayer displayer = injector.getInstance(Displayer.class);
        displayer.display();
    }
    
    private static class DisplayModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(String.class)
                .annotatedWith(Names.named("hello world"))
                .toInstance("hello world is the string");
        }
    }
    
    private static class Displayer {
        
        private final String message;
        
        @Inject
        public Displayer(@Named("hello world") String message) {
            this.message = message;
        }
        
        public void display() {
            System.out.println(message);
        }
    }
}
