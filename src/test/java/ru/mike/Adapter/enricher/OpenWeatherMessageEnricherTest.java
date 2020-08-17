package ru.mike.Adapter.enricher;

import org.junit.Test;
import org.mockito.Mockito;
import ru.mike.adapter.client.OpenWeatherClient;
import ru.mike.adapter.dto.MsgA;
import ru.mike.adapter.dto.MsgB;
import ru.mike.adapter.dto.OpenWeatherResponse;

public class OpenWeatherMessageEnricherTest {
    private OpenWeatherClient openWeatherClient = Mockito.mock(OpenWeatherClient.class);

    @Test
    public MsgB enrich() {
        Mockito.when(openWeatherClient.getWeatherAt(Mockito.anyString(),
                Mockito.anyString())).thenReturn(Mockito.any(OpenWeatherResponse.class));
        return new MsgB();
    }
}
