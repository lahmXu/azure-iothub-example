// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

// This application uses the Microsoft Azure Event Hubs Client for Java
// For samples see: https://github.com/Azure/azure-sdk-for-java/tree/master/sdk/eventhubs/azure-messaging-eventhubs/src/samples
// For documentation see: https://docs.microsoft.com/azure/event-hubs/

package com.example.azureiothubexample.consumer;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * receive events from Event Hubs sent from an IoT Hub device.
 */
@Slf4j
@Service
@Order(value = 2)
public class MessageReader implements CommandLineRunner {

    private static final String EH_COMPATIBLE_CONNECTION_STRING_FORMAT = "Endpoint=%s/;EntityPath=%s;SharedAccessKeyName=%s;SharedAccessKey=%s";

    /**
     * CommandLineRunner 启动后执行
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        AzureIotHubConfig config = new AzureIotHubConfig();
        // 手动设置参数
//        config.setEventHubsCompatibleEndpoint("");
//        config.setEventHubsCompatiblePath("");
//        config.setIotHubSasKey("");
//        config.setIotHubSasKeyName("");
//        config.setIotHubName("");
        start(config);
    }

    public void start(AzureIotHubConfig config) throws Exception {

        String eventHubCompatibleConnectionString = String.format(EH_COMPATIBLE_CONNECTION_STRING_FORMAT,
                config.getEventHubsCompatibleEndpoint(), config.getEventHubsCompatiblePath(), config.getIotHubSasKeyName(), config.getIotHubSasKey());

        EventHubClientBuilder eventHubClientBuilder = new EventHubClientBuilder()
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .connectionString(eventHubCompatibleConnectionString);
        try {
            EventHubConsumerAsyncClient eventHubConsumerAsyncClient = eventHubClientBuilder.buildAsyncConsumerClient();
            receiveFromAllPartitions(eventHubConsumerAsyncClient, config);

        } catch (Exception ex) {
            log.error("[Azure iotHub] build async client error.");
        }
    }

    private void receiveFromAllPartitions(EventHubConsumerAsyncClient eventHubConsumerAsyncClient, AzureIotHubConfig config) {
        eventHubConsumerAsyncClient
                .receive(false) // set this to false to read only the newly available events
                .subscribe(partitionEvent -> {
                    String receiveBodyStr = partitionEvent.getData().getBodyAsString();
                    log.info("[Azure iotHub] Received message: {}", receiveBodyStr);

                }, ex -> {
                    log.error("[Azure iotHub] Error handler message: {}", ex.getMessage());
                }, () -> {
                    log.info("[Azure iotHub] Completed receiving events");
                });
    }
}
