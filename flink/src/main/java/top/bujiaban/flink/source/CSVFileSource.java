package top.bujiaban.flink.source;

import com.opencsv.CSVReader;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import top.bujiaban.flink.dto.Event;

import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class CSVFileSource implements SourceFunction<Event> {
    private String filePath;
    private Boolean toggleOn = false;

    public CSVFileSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run(SourceContext<Event> ctx) throws Exception {
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath)));
        try (CSVReader csvReader = new CSVReader(reader)) {
            csvReader.skip(1);
            toggleOn = true;

            String[] values = null;
            while ((values = csvReader.readNext()) != null && toggleOn) {
                Event event = Event.builder()
                        .eventId(values[0])
                        .eventType(values[1])
                        .eventData(values[2])
                        .eventStatus(Integer.parseInt(values[3]))
                        .createBy(values[4])
                        .creationDate(new Date(ZonedDateTime.parse(values[5]).toInstant().toEpochMilli()))
                        .lastUpdatedBy(values[6])
                        .lastUpdateDate(new Date(ZonedDateTime.parse(values[7]).toInstant().toEpochMilli()))
                        .lastUpdateVersion(Integer.parseInt(values[8]))
                        .build();
                ctx.collect(event);
            }
        }
    }

    @Override
    public void cancel() {
        toggleOn = false;
    }
}
