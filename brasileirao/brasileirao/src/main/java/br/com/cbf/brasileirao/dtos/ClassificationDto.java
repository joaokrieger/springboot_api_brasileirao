package br.com.cbf.brasileirao.dtos;

import lombok.Data;

@Data
public class ClassificationDto {
    private String team;
    private Long teamId;
    private Integer position;
    private Integer points;
    private Integer games;
    private Integer victories;
    private Integer draws;
    private Integer defeats;
    private Integer goalsScored;
    private Integer goalsConceded;
}
