package com.crp.ucp.faction;

import com.crp.ucp.server.model.Faction;
import com.crp.ucp.server.model.FactionMember;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FactionMapper {

    Faction mapTo(FactionEntity faction);
    
    FactionMember mapTo(FactionMemberProjection factionMember);

    List<FactionMember> mapTo(List<FactionMemberProjection> factionMember);
}
