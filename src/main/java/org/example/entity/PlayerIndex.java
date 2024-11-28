package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player_index")
@Getter
@Setter
public class PlayerIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player; // Foreign Key reference to `player`

    @ManyToOne
    @JoinColumn(name = "index_id", nullable = false)
    private Indexer indexer; // Foreign Key reference to `indexer`

    @Column(name = "value", nullable = false)
    private float value;
}
