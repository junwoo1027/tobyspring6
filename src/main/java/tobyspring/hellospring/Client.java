package tobyspring.hellospring;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.context.annotation.ComponentScan;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);

        System.out.println("----------------------------------\n");

        TimeUnit.SECONDS.sleep(3);

        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment2);
    }

}
