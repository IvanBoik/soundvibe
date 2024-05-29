package com.boiko.backend.repository;

import com.boiko.backend.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "select * from songs where is_published = true",
    countQuery = "select count(*) from songs where is_published = true",
    nativeQuery = true)
    Page<Song> findAllPublished(Pageable pageable);

    Optional<Song> findPublishedById(Long id);

    @Modifying
    @Transactional
    @Query(value = """
    update Song set auditions = auditions+1 where id=:id
    """)
    void incrementAuditions(Long id);
}
