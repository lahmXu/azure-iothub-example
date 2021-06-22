package com.example.azureiothubexample.consumer;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AzureIotHubConfig {
    /**
     * iotHub name
     */
    @NotNull
    private String iotHubName;
    /**
     * 内置终节点 Endpoint
     */
    @NotNull
    private String eventHubsCompatibleEndpoint;

    /**
     * 内置终结点 EntityPath
     */
    @NotNull
    private String eventHubsCompatiblePath;

    /**
     * 内置终结点 SharedAccessKey
     */
    @NotNull
    private String iotHubSasKey;

    /**
     * 内置终结点 SharedAccessKeyName
     */
    @NotNull
    private String iotHubSasKeyName;

    /**
     * iotHub 服务连接字符串
     */
    @NotNull
    private String iotHubConnStr;
}
