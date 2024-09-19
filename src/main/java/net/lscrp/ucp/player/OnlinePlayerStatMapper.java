package net.lscrp.ucp.player;

import net.lscrp.ucp.server.model.OnlinePlayerStat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OnlinePlayerStatMapper {

    OnlinePlayerStat mapTo(OnlinePlayerStatEntity onlinePlayerStat);

    List<OnlinePlayerStat> mapTo(List<OnlinePlayerStatEntity> onlinePlayerStat);
}
