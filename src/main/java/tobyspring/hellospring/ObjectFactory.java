package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(this.exRateProvider());
    }

    private ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

}
