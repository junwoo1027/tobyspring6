package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ExRateProviderStub exRateProviderStub;

    @Test
    void prepare() throws IOException {
        Payment result = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(result.getOrderId()).isEqualTo(1L);
        assertThat(result.getCurrency()).isEqualTo("USD");
        assertThat(result.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(result.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
        assertThat(result.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(result.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

        exRateProviderStub.setExRate(BigDecimal.valueOf(500));

        Payment result2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(result2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(result2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }
}