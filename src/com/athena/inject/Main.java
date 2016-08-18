package com.athena.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DisplayModule());
        
        Displayer displayer = injector.getInstance(Displayer.class);
        displayer.display();
    }
    
    private static class DisplayModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(Formatter.class).to(HtmlFormatter.class);
        }
    }
    
    private static class Displayer {
        
        private final Formatter formatter;
        
        @Inject
        public Displayer(Formatter formatter) {
            this.formatter = formatter;
        }
        
        public void display() {
            formatter.display();
        }
    }

    private static interface Formatter {
        void display();
    }    
    
    private static class HtmlFormatter implements Formatter {
        public void display() {
            System.out.println("hello world");   
        }
    }
}
