package org.example.service;

import org.example.entity.Indexer;
import org.example.repository.Impl.IndexRepositoryImpl;
import org.example.repository.IndexerRepository;

import java.util.List;
import java.util.Optional;

public class IndexService {
    private IndexerRepository indexerRepository;

    // Constructor that initializes the IndexerRepository
    public IndexService() {
        // Use the IndexRepositoryImpl which now uses Hibernate internally
        this.indexerRepository = new IndexRepositoryImpl();
    }

    public Indexer saveIndexer(Indexer indexer) {
        // Calls the repository to save the indexer
        return indexerRepository.save(indexer);
    }

    public Optional<Indexer> getIndexerById(int indexerId) {
        // Calls the repository to find a indexer by ID
        return indexerRepository.findById(indexerId);
    }

    public List<Indexer> getAllIndexers() {
        // Calls the repository to get all indexers
        return indexerRepository.findAll();
    }

    public boolean deleteIndexerById(int indexerId) {
        // Calls the repository to delete a indexer by ID
        return indexerRepository.deleteById(indexerId);
    }

}
