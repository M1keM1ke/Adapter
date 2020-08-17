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
    //paste your token here
    @GetMapping(value = "/weather?lat={latitude}&lon={longtitude}&appid={weatherToken}")
    OpenWeatherResponse getWeatherAt(
            @PathVariable("latitude") String latitude,
            @PathVariable("longtitude") String longtitude
    );
}
