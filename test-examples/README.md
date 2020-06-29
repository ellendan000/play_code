# test-examples
该子模块是为了 —— 规范化微服务质量内建中的测试分层的概念，并且整合有利于测试指标提取与输出的测试工具链，以及可落地的测试启动脚本。

### 结构
* `main` sample产品代码.
* `unitTest` 单元测试，技术意义上真正的单元测试。
* `integTest` 集成测试，Spring切片测试。
* `apiTest` api测试，除了基础设施、外部服务依赖外的spring full application context测试。
* `contractTest` 契约测试。sourcesSet目前是test，由于Spring cloud contract plugin进行了一些运行时动态绑定，
很难另外独立命名contractTest sourcesSet并具备运行此插件的能力。
* `archUnitTest` 架构守护。主要针对产品代码中的架构、代码约定规范和层级结构等等，以unit test的形式进行查验。