package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
