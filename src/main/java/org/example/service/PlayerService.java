package org.example.service;

import org.example.entity.Player;
import org.example.repository.Impl.PlayerRepositoryImpl;
import org.example.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    private PlayerRepository playerRepository;

    // Constructor that initializes the PlayerRepository
    public PlayerService() {
        // Use the PlayerRepositoryImpl which now uses Hibernate internally
        this.playerRepository = new PlayerRepositoryImpl();
    }

    public Player savePlayer(Player player) {
        // Calls the repository to save the player
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(int playerId) {
        // Calls the repository to find a player by ID
        return playerRepository.findById(playerId);
    }

    public List<Player> getAllPlayers() {
        // Calls the repository to get all players
        return playerRepository.findAll();
    }

    public boolean deletePlayerById(int playerId) {
        // Calls the repository to delete a player by ID
        return playerRepository.deleteById(playerId);
    }
}
