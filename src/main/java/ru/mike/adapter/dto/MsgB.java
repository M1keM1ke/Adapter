package ru.mike.adapter.dto;

import lombok.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MsgB {
    private String txt;
    private Date createdDt;
    private int currentTemp;
}