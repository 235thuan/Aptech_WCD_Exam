package org.example.repository;


import org.example.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> findById(int playerId);
    List<Player> findAll();
    boolean deleteById(int playerId);
}
