package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(this.cachedExRateProvider());
    }

	@Bean
	public ExRateProvider cachedExRateProvider() {
		return new CachedExRateProvider(this.exRateProvider());
	}

	@Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

}
