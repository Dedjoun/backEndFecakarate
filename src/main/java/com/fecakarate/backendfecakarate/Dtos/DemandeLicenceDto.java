package com.fecakarate.backendfecakarate.Dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class DemandeLicenceDto {
    private Long clUbId;
    private List<String> matricules;
}
