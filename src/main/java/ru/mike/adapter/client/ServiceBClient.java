package ru.mike.adapter.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "service-b-client",
        url = "${feign.serviceB.url:}"
)
public interface ServiceBClient {

}
