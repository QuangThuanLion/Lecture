package com.example.kafkaexample;

import com.example.kafkaexample.ksqlConfig.ConfigQuery;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, ProductRequest> kafkaTemplateProduct;

    private final ConfigQuery configQuery;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate,
                             KafkaTemplate<String, ProductRequest> kafkaTemplateProduct,
                             ConfigQuery configQuery) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateProduct = kafkaTemplateProduct;
        this.configQuery = configQuery;
    }

    @PostMapping
    public String publish(@RequestBody Map<String, String> message) {
        kafkaTemplate.send("USERPROFILE", message.get("message"));
        return "message can be push on kafka successfully !";
    }

    @PostMapping("/products")
    public String publishToKafka(@RequestBody ProductRequest request) {
//        Gson gson = new Gson();
//        String product = gson.toJson(request, String.class);
//        kafkaTemplate.send("PRODUCT_TOPIC", product);
        kafkaTemplateProduct.send("PRODUCT_TOPIC", request);
        return "Add product success to kafka";
    }

    @GetMapping("/ksql")
    public List<ProductResponse> publish() throws InterruptedException, ExecutionException {
        List<ProductResponse> result = configQuery.configKsql();
        return result;
    }

    @GetMapping(value = "/data/flux", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> streamDataFlux() throws ExecutionException, InterruptedException {
        List<ProductResponse> result = configQuery.configKsql();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/data/stream/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<ProductResponse>> streamDataFluxFlux() throws ExecutionException, InterruptedException {
        List<ProductResponse> result = configQuery.configKsql();
        return (Flux<List<ProductResponse>>) result;
    }

    @GetMapping("/ksql/queryId")
    public String queryId (@RequestParam(value = "id") Integer id) throws ExecutionException, InterruptedException {
        String result = configQuery.clientQueryId(id);
        return result;
    }
}
