package com.crp.ucp.faction;

import com.crp.ucp.server.api.FactionApi;
import com.crp.ucp.server.model.Faction;
import com.crp.ucp.server.model.FactionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.crp.ucp.faction.FactionUtil.throwFactionNotFoundException;

@Controller
@RequiredArgsConstructor
public class FactionController implements FactionApi {

    private final FactionService factionService;

    private final FactionMapper factionMapper;

    @Override
    public ResponseEntity<Faction> retrieveFaction(Integer factionId) {
        FactionEntity faction = factionService.getFactionById(factionId)
                .orElseThrow(throwFactionNotFoundException(factionId));
        return ResponseEntity.ok(factionMapper.mapTo(faction));
    }

    @Override
    public ResponseEntity<List<FactionMember>> listFactionMembers(Integer factionId) {
        return ResponseEntity.ok(factionMapper.mapTo(factionService.getFactionMembers(factionId)));
    }
}
