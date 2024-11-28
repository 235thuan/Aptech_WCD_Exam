package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "indexer")
@Getter
@Setter
public class Indexer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private int indexId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "valueMin", nullable = false)
    private float valueMin;

    @Column(name = "valueMax", nullable = false)
    private float valueMax;
}
