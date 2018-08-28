package xschmitthappenzx.contractpoc;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8081)
public class ContractPocApplicationTests {

	@Test
	public void contextLoads() { }

	@Test
	public void test_should_return_all_coins(){

		String json = "[\"BTC\", \"ETH\"]";

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/coin"))
			.willReturn(WireMock.aResponse().withBody(json).withStatus(201)));

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8081/coin", String.class);

		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(201);
		BDDAssertions.then(entity.getBody()).isEqualTo(json);
	}

}
