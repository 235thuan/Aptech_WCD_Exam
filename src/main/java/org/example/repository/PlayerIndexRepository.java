package org.example.repository;

import org.example.entity.Player;
import org.example.entity.PlayerIndex;

import java.util.List;
import java.util.Optional;

public interface PlayerIndexRepository {
    PlayerIndex save(PlayerIndex playerIndex);
    Optional<PlayerIndex> findById(int playerIndexId);
    List<PlayerIndex> findAll();
    boolean deleteById(int playerIndexId);
}
