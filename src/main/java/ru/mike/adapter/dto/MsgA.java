package ru.mike.adapter.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MsgA {
    private String msg;
    private String lng;
    private Coordinates coordinates;
}
