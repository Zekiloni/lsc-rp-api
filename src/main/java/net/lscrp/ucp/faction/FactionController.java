package net.lscrp.ucp.faction;

import net.lscrp.ucp.server.api.FactionApi;
import net.lscrp.ucp.server.model.Faction;
import net.lscrp.ucp.server.model.FactionMember;
import net.lscrp.ucp.server.model.MemberRankUpdate;
import net.lscrp.ucp.server.model.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import static net.lscrp.ucp.faction.FactionUtil.throwFactionNotFoundException;
import static java.text.MessageFormat.format;

@Controller
@RequiredArgsConstructor
public class FactionController implements FactionApi {

    public static final String KICK_MEMBER_RESPONSE = "Izbacili ste {0} iz fakcije";

    private final FactionService factionService;

    private final FactionMapper factionMapper;

    @Override
    public ResponseEntity<FactionMember> patchFactionMemberRank(Integer characterId, MemberRankUpdate memberRankUpdate) {
        FactionMemberProjection updatedFactionMember = factionService.updateFactionMemberRank(characterId, memberRankUpdate.getRankName());
        return ResponseEntity.ok(factionMapper.mapTo(updatedFactionMember));
    }

    @Override
    public ResponseEntity<Faction> retrieveFaction(Integer factionId) {
        FactionEntity faction = factionService.getFactionById(factionId)
                .orElseThrow(throwFactionNotFoundException(factionId));
        return ResponseEntity.ok(factionMapper.mapTo(faction));
    }

    @Override
    public ResponseEntity<MessageResponse> kickFactionMember(Integer characterId) {
        String characterName = factionService.kickFactionMember(characterId).getCharacterName();
        return ResponseEntity.ok(new MessageResponse(format(KICK_MEMBER_RESPONSE, characterName)));
    }

    @Override
    public ResponseEntity<List<FactionMember>> listFactionMembers(Integer factionId) {
        return ResponseEntity.ok(factionMapper.mapTo(factionService.getFactionMembers(factionId)));
    }
}
