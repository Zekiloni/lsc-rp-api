package com.crp.ucp.faction;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactionMemberProjection {
    private Integer characterId;
    private String characterName;
    private String rankName;
}
