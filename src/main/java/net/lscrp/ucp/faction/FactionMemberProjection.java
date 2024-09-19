package net.lscrp.ucp.faction;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactionMemberProjection {
    private Integer characterId;
    private String characterName;
    private Integer characterSkin;
    private String accountUsername;
    private String rankName;
    private Boolean isLeader;
    private Boolean isInGame;
    private double averageActivity;
}
