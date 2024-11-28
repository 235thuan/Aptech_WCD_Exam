package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@Setter
@Getter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "full_name", nullable = false, length = 128)
    private String fullName;

    @Column(name = "age", nullable = false, length = 10)
    private String age;

    @ManyToOne
    @JoinColumn(name = "index_id", nullable = false)
    private Indexer indexer; // Foreign Key to the Indexer table

    // One-to-many relationship with PlayerIndex
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<PlayerIndex> playerIndexes;
}

