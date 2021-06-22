# azure-iothub-example
azure-iothub-example with SpringBoot

## 执行方法
1. 在MessageReader中的run方法指定参数，具体描述见AzureIotHubConfig
2. 启动AzureIothubExampleApplication

## 注意的点：
1. spring会自动将eventHubConsumerAsyncClient异步接收，无须额外将线程挂起
2. SpringBoot 版本要2.2.0.RELEASE以上，之前用的2.1.7.RELEASE各种莫名其妙的问题

---
---
---

# 附：设备对接参考链接
### 设备端上报数据到Iot Hub（通过mqtt协议上报）

- 使用MQTT协议上报数据：

  https://docs.microsoft.com/zh-cn/azure/iot-hub/iot-hub-devguide-no-sdk

  https://docs.microsoft.com/zh-cn/azure/iot-hub/iot-hub-mqtt-support

- 设备需要SAS令牌，这个需要通过工具生成



### 服务端从IotHub读取和下发数据（基于sdk开发）

- 微软开发文档（这个是java，其他语言可以参照文档）：

  https://docs.microsoft.com/zh-cn/azure/iot-hub/iot-hub-java-java-c2d

- 模拟设备端代码：
  https://github.com/Azure-Samples/azure-iot-samples-java/tree/master/iot-hub/Quickstarts/simulated-device

- 服务端读取代码：

  https://github.com/Azure-Samples/azure-iot-samples-java/tree/master/iot-hub/Quickstarts/read-d2c-messages



