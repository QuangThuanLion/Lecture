package com.example.kafkaexample.ksqlConfig;

import com.example.kafkaexample.ProductResponse;
import io.confluent.ksql.api.client.BatchedQueryResult;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.KsqlObject;
import io.confluent.ksql.api.client.Row;
import io.confluent.ksql.api.client.StreamedQueryResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Component;

@Component
public class ConfigQuery {
    private Client client;

    public static String KSQLDB_SERVER_HOST = "localhost";
    public static int KSQLDB_SERVER_HOST_PORT = 8088;

    public ConfigQuery() {
        ClientOptions clientOptions = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT);
        this.client = Client.create(clientOptions);
    }

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<ProductResponse> configKsql() throws ExecutionException, InterruptedException {
        // Send requests with the client by following the other examples
        client.streamQuery("SELECT * FROM PRODUCT_TABLE_NEW;")
                .thenAccept(streamedQueryResult -> {
                    System.out.println("Query has started. Query ID: " + streamedQueryResult.queryID());

                    RowSubscriber rowSubscriber = new RowSubscriber();
                    streamedQueryResult.subscribe(rowSubscriber);

                    for (int i = 0; i < 10; i ++) {
                        Row row = streamedQueryResult.poll();
                        if (row != null) {
                            System.out.println("Received a row !");
                            System.out.println("Row: "  + row.values());
                        } else {
                            System.out.println("Query has ended");
                        }
                    }

                }).exceptionally(ex -> {
                    System.out.println("Request failed: " + ex);
                    return null;
                });

        //Receive query results in a single batch (executeQuery())
        String pullQuery = "SELECT * FROM PRODUCT_TABLE_NEW;";
        BatchedQueryResult batchedQueryResult = client.executeQuery(pullQuery);

        List<Row> resultRows = batchedQueryResult.get();
        System.out.println("Received result. Numb rows: " + resultRows.size());
        List<ProductResponse> responseDTOS = new ArrayList<>();
        for (Row row : resultRows) {
            System.out.println("Row: " + row.values());
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(row.getString("ID"));
            productResponse.setName(row.getString("NAME"));
            productResponse.setPrice(row.getDouble("PRICE"));
            productResponse.setDescription(row.getString("DESCRIPTION"));
            productResponse.setStatus(row.getBoolean("STATUS"));

            responseDTOS.add(productResponse);
        }

        // Terminate any open connections and close the client
//        client.close();
        return responseDTOS;
    }

    /**
     * @param requestDTO
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void insertQuery(RequestDTO requestDTO) throws ExecutionException, InterruptedException {
        KsqlObject ksqlObject = new KsqlObject()
                .put("PROFILEID", requestDTO.getProfileId())
                .put("LATITUDE", requestDTO.getLatitude())
                .put("LONGITUDE", requestDTO.getLongiTude());

        client.insertInto("RIDERLOCATIONS", ksqlObject).get();
    }

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String clientQueryId(Integer id) throws ExecutionException, InterruptedException {
        ClientOptions clientOptions = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT);
        Client client = Client.create(clientOptions);

        //Receive query results use streamQuery
        String pullQuery = "SELECT * FROM PRODUCT_TABLE_NEW LIMIT 3;";
//        String pullQuery = "SELECT * FROM PRODUCT_TABLE_NEW WHERE ID = '"+id+"';";
        StreamedQueryResult streamedQueryResult = client.streamQuery(pullQuery).get();

        String queryId = streamedQueryResult.queryID();
        client.terminatePushQuery(queryId).get();

//        Receive query result use executeQuery
//        BatchedQueryResult batchedQueryResult = client.executeQuery(pullQuery);
//        String queryIdExecuteQuery = batchedQueryResult.queryID().get();
//        client.terminatePushQuery(queryId).get();

        // Terminate any open connections and close the client
//        client.close();
        return queryId;
    }

    public void createStream(String queryString) {

    }
}
