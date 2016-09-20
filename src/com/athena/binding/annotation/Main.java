package com.athena.binding.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        billingService.doIt();
    }
    
    private static interface CreditCardProcessor {
        void doIt();
    }
    
    private static class BillingModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(CreditCardProcessor.class)
                .annotatedWith(Paypal.class)
                .to(PaypalCreditCardProcessor.class);
        }
    }
    
    private static class BillingService {
        
        private final CreditCardProcessor processor;
        
        @Inject
        public BillingService(@Paypal CreditCardProcessor processor) {
            this.processor = processor;
        }
        
        public void doIt() {
            processor.doIt();
        }
    }
    
    private static class PaypalCreditCardProcessor implements CreditCardProcessor {
        public void doIt() {
            System.out.println("Paypal");
        }
    }
    
    private static class GoogleCheckoutCreditCardProcessor implements CreditCardProcessor {
        public void doIt() {
            System.out.println("GoogleCheckout");
        }
    }
    
    @BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
    public @interface Paypal {}
}
