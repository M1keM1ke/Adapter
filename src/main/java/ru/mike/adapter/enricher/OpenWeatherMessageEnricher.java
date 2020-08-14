package ru.mike.adapter.enricher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.adapter.client.OpenWeatherClient;
import ru.mike.adapter.dto.Coordinates;
import ru.mike.adapter.dto.OpenWeatherResponse;
import ru.mike.adapter.dto.MsgA;
import ru.mike.adapter.dto.MsgB;
import ru.mike.adapter.listener.OrderMessageListener;

import java.util.Date;

@Component
public class OpenWeatherMessageEnricher implements MessageEnricher {
    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Autowired
    OrderMessageListener orderMessageListener;

    @Override
    public String getId() {
        return "gismeteo";
    }

    @Override
    public MsgB enrich(MsgA msgA) {

        Coordinates coordinates = msgA.getCoordinates();
        OpenWeatherResponse response = openWeatherClient.getWeatherAt(
                coordinates.getLatitude(),
                coordinates.getLongitude()
        );

        return MsgB.builder()
                .txt(msgA.getMsg())
                .createdDt(new Date())
                .currentTemp(response.getTemp())
                .build();
    }
}
