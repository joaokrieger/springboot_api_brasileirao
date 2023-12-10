package br.com.cbf.brasileirao.dtos;

import lombok.Data;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private String abbreviation;
    private String state;
    private String stadium;
}
