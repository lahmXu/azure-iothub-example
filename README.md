# azure-iothub-example
azure-iothub-example with SpringBoot

## 执行方法
1. 在MessageReader中的run方法指定参数，具体描述见AzureIotHubConfig
2. 启动AzureIothubExampleApplication

## 注意的点：
1. spring会自动将eventHubConsumerAsyncClient异步接收，无须额外将线程挂起
2. SpringBoot 版本要2.2.0.RELEASE以上，之前用的2.1.7.RELEASE各种莫名其妙的问题
