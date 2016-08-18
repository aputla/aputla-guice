package com.athena.binding.linked;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Example01 {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new FormatterModule());
        
        Formatter formatter = injector.getInstance(Formatter.class);
        System.out.println(formatter.format("hello world"));
    }
    
    private static class FormatterModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(Formatter.class).to(HtmlFormatter.class);
        }
    }

    private static interface Formatter {
        String format(String input);
    }    
    
    private static class HtmlFormatter implements Formatter {
        public String format(String input) {
            return "<html>" + input + "</html>";    
        }
    }
}
