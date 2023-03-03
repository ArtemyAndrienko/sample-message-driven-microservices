package pl.piomin.services.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.piomin.services.messaging.Order;
import pl.piomin.services.messaging.OrderStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	@Autowired
	TestRestTemplate template;

	@Test
	public void testOrder() throws InterruptedException {
		Order order = new Order();
		order.setStatus(OrderStatus.NEW);
		order.setCustomerId(1L);
		order.setProductIds(List.of(1L, 2L, 3L));
		order.setPrice(1000);
		order = template.postForObject("/", order, Order.class);
		assertNotNull(order);
		assertNotNull(order.getId());
		assertEquals(OrderStatus.PROCESSING, order.getStatus());
	}
	
}
