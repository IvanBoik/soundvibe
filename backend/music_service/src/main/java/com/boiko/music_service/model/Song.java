package com.boiko.music_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "songs")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "songs_authors", joinColumns = @JoinColumn(name = "id_song"))
    @Column(name = "id_author")
    private List<Long> authorsIDs;

    @OneToOne
    @JoinColumn(name = "audio")
    private FileInfo audio;

    @OneToOne
    @JoinColumn(name = "picture")
    private FileInfo picture;

    private String name;
    private long duration;
    private Timestamp timestampAdded;
    private long likes;
    private boolean isPublished;
    private long auditions;
}
