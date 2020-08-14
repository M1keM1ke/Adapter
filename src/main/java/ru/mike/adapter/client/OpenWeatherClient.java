package ru.mike.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.mike.adapter.dto.OpenWeatherResponse;


@FeignClient(
        name = "openweather-client",
        url = "${feign.openweather.url:}",
        path = "${feign.openweather.path:}"
)

public interface OpenWeatherClient {
    @GetMapping(value = "/weather?lat={latitude}&lon={longtitude}&appid=667878cc40014e1d5b76fc83617cd944")
    public OpenWeatherResponse getWeatherAt(
            @PathVariable("latitude") String latitude,
            @PathVariable("longtitude") String longtitude
    );
}
