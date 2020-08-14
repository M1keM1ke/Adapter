package ru.mike.adapter.enricher;

import org.springframework.stereotype.Component;
import ru.mike.adapter.dto.MsgA;
import ru.mike.adapter.dto.MsgB;


public interface MessageEnricher {
    public String getId();

    public MsgB enrich(MsgA msgA);
}
