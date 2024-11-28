package org.example.repository;

import org.example.entity.Indexer;


import java.util.List;
import java.util.Optional;

public interface IndexerRepository {
    Indexer save(Indexer indexer);
    Optional<Indexer> findById(int indexerId);
    List<Indexer> findAll();
    boolean deleteById(int indexerId);
}
