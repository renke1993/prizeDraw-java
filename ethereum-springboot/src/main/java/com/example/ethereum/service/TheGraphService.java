package com.example.ethereum.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheGraphService {

    private static final String GRAPH_ENDPOINT = "https://api.studio.thegraph.com/query/119597/simple-storage/version/latest";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ValueChangeEvent> getValueChangesInRange(BigInteger min, BigInteger max) throws IOException {
        String query = "{ valueChangeds(first: 5) { id newValue changedBy blockNumber } }";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                "{\"query\": \"" + query + "\"}"
        );

        Request request = new Request.Builder()
                .url(GRAPH_ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode valueChanges = rootNode.path("data").path("valueChangeds");

            List<ValueChangeEvent> events = new ArrayList<>();
            for (JsonNode node : valueChanges) {
                ValueChangeEvent event = new ValueChangeEvent();
                event.setId(node.path("id").asText());
                event.setValue(new BigInteger(node.path("newValue").asText()));
                event.setChangedBy(node.path("changedBy").asText());
                event.setBlockNumber(new BigInteger(node.path("blockNumber").asText()));
                events.add(event);
            }

            return events;
        }
    }

    // 内部类表示一个值变更事件
    public static class ValueChangeEvent {
        private String id;
        private BigInteger value;
        private String changedBy;
        private BigInteger blockNumber;
        private BigInteger blockTimestamp;

        // Getters and setters...
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public BigInteger getValue() { return value; }
        public void setValue(BigInteger value) { this.value = value; }

        public String getChangedBy() { return changedBy; }
        public void setChangedBy(String changedBy) { this.changedBy = changedBy; }

        public BigInteger getBlockNumber() { return blockNumber; }
        public void setBlockNumber(BigInteger blockNumber) { this.blockNumber = blockNumber; }

        public BigInteger getBlockTimestamp() { return blockTimestamp; }
        public void setBlockTimestamp(BigInteger blockTimestamp) { this.blockTimestamp = blockTimestamp; }
    }
}