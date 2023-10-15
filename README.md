AXON PRACTICE 2
===============
### 목표
- 멀티 프로젝트 구성
- Axon TEP에 대한 이해
- correlation id, causation id에 대한 이해

### 로컬 개발 환경 실행
```shell
docker-compose up -d
```

```shell
./gradlew build
docker build -t query-app ./query
docker run -p 8081:8081 query-app
```
```shell
docker run --network esdemo3_default --env-file query-app1.env -p 8081:8081 query-app
```


### Event Store
[event-bus-and-event-store](https://docs.axoniq.io/reference-guide/axon-framework/events/event-bus-and-event-store)

> Event Bus : 이벤트를 구독하고 있는 event handler로 dispatch 하는 메커니즘

#### Axon이 제공하는 4가지 Event Bus 구현체
3가지 구현체 모두 2가지 event processor 제공.
```
1. AxonServerEventStore (default)
2. EmbeddedEventStore : non-axon-server option을 위한 다양한 EventStorageEngine 제공
3. SimpleEventBus (권장 x) 
```
#### 2가지 타입의 EventProcessor
1. subscribing
   - `SubscribableMessageSource`에 등록해서 메세지 제공 받음. (예) EventBus. AMQP extension / Kafka extension)
   - 이벤트 발행하는 스레드와 Subscribing handler 호출하는 스레드가 동일.
   - **replaying x**, **parallel 실행 x**
   - 이벤트 발행 (이벤트 스토어에 저장) 과 read model 업데이트가 동시에 일어나야 할 경우는 이게 더 나은 선택지일 수도 있음.
2. streaming
   - `StreamableMessageSource`에서 메세지 제공 받음. (예) EventStore. Axon Server / RDBMS)
   - Tracing Token을 사용해서 스트림의 이벤트 핸들링할 start position으로 사용.
   - 이벤트 발행하는 스레드랑 event process 스레드 분리.
   - Tracing Token 초기화해서 처음 이벤트 부터 다시 replay 가능. 스레드가 분리되어 있어 깔끔한 병령 실행도 가능.
   - 더 유연한 시나리오 지원 (decoupling)
   - 2가지 구현체
     - TEP <- **default**
     - PSEP

[subscribing](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors/subscribing)  
[streaming](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors/streaming)
