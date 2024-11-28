package org.example.service;

import org.example.entity.PlayerIndex;

import org.example.repository.Impl.PlayerIndexRepositoryImpl;

import org.example.repository.PlayerIndexRepository;

import java.util.List;
import java.util.Optional;

public class PlayerIndexService {
    private PlayerIndexRepository playerIndexRepository;

    // Constructor that initializes the PlayerIndexRepository
    public PlayerIndexService() {
        // Use the PlayerIndexRepositoryImpl which now uses Hibernate internally
        this.playerIndexRepository = new PlayerIndexRepositoryImpl();
    }

    public PlayerIndex savePlayerIndex(PlayerIndex player) {
        // Calls the repository to save the player
        return playerIndexRepository.save(player);
    }

    public Optional<PlayerIndex> getPlayerIndexById(int playerId) {
        // Calls the repository to find a player by ID
        return playerIndexRepository.findById(playerId);
    }

    public List<PlayerIndex> getAllPlayerIndexs() {
        // Calls the repository to get all players
        return playerIndexRepository.findAll();
    }

    public boolean deletePlayerIndexById(int playerId) {
        // Calls the repository to delete a player by ID
        return playerIndexRepository.deleteById(playerId);
    }
}
