package com.athena.binding.provides;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;

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
        }
        
        @Provides
        @Named("paypal")
        CreditCardProcessor provideCreditCardProcessor() {
            return new PaypalCreditCardProcessor();
        }
    }
    
    private static class BillingService {
        
        private final CreditCardProcessor processor;
        
        @Inject
        public BillingService(@Named("paypal") CreditCardProcessor processor) {
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
}
