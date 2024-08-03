package tobyspring.hellospring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.hellospring.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {

	private final ExRateProvider target;

	private BigDecimal cachedRate;
	private LocalDateTime cacheExpireTime;

	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		if (cachedRate == null || cacheExpireTime.isBefore(LocalDateTime.now())) {
			cachedRate = target.getExRate(currency);
			cacheExpireTime = LocalDateTime.now().plusSeconds(3);

			System.out.println("Cache Updated");
		}

		return cachedRate;
	}

}
