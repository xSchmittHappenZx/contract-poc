package xschmitthappenzx.contractpoc;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractPocApplicationIntTest {

    @Test
    public void test_should_return_all_coins(){

        String json = "[\"BTC\",\"ETH\"]";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8082/coin", String.class);

        BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(201);
        BDDAssertions.then(entity.getBody()).isEqualTo(json);
    }
}
