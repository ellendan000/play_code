package top.bujiaban.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import top.bujiaban.flink.dto.Event;
import top.bujiaban.flink.dto.JoinedEvent;
import top.bujiaban.flink.source.CSVFileSource;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public class EventConsumedPortal {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.setString("state.backend", "filesystem");
        conf.setString("state.savepoints.dir", "file:///tmp/savepoints");
        conf.setString("state.checkpoints.dir", "file:///tmp/checkpoints");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        env.enableCheckpointing(10000L);
        CheckpointConfig config = env.getCheckpointConfig();
        config.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(2);
        DataStream<Event> producer = env.addSource(new CSVFileSource("event_producer.csv"));
        DataStream<Event> consumer = env.addSource(new CSVFileSource("event_consumer.csv"));

        DataStream<Event> producerWithTimestampsAndWatermarks = producer
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<Event>forBoundedOutOfOrderness(Duration.ofSeconds(2))
                        .withTimestampAssigner((element, recordTimestamp) -> element.getCreationDate().getTime()))
                .setParallelism(1)
                .uid("producer");
        DataStream<Event> consumerWithTimestampsAndWatermarks = consumer
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<Event>forBoundedOutOfOrderness(Duration.ofSeconds(2))
                        .withTimestampAssigner((element, recordTimestamp) -> element.getCreationDate().getTime()))
                .setParallelism(1)
                .uid("consumer");

        producerWithTimestampsAndWatermarks.coGroup(consumerWithTimestampsAndWatermarks)
                .where(Event::getEventId)
                .equalTo(Event::getEventId)
                .window(TumblingEventTimeWindows.of(Time.seconds(1)))
//                .trigger(EventTimeTrigger.create())
//                .allowedLateness(Time.milliseconds(0))
                .apply(new CoGroupFunction<Event, Event, Object>() {

                    @Override
                    public void coGroup(Iterable<Event> first, Iterable<Event> second, Collector<Object> out) throws Exception {
                        if (StreamSupport.stream(first.spliterator(), false).count() < 1) {
                            return;
                        }

                        List<JoinedEvent> joinedEventList = StreamSupport.stream(first.spliterator(), false).map(event -> {
                            Iterator<Event> secondIterator = second.iterator();
                            return JoinedEvent.builder()
                                    .eventId(event.getEventId())
                                    .eventType(event.getEventType())
                                    .eventData(event.getEventData())
                                    .creationDate(event.getCreationDate())
                                    .isConsumed(secondIterator.hasNext())
                                    .consumedDate(secondIterator.hasNext() ? secondIterator.next().getCreationDate() : null)
                                    .build();
                        }).collect(Collectors.toList());
                        out.collect(joinedEventList);
                    }
                })
                .print()
        .setParallelism(1);

        env.execute("producer consumer event matching");
    }

}
