package com.boiko.music_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "albums")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "albums_authors", joinColumns = @JoinColumn(name = "id_album"))
    private List<Long> authorsIDs;

    @OneToOne
    @JoinColumn(name = "picture")
    private FileInfo picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "songs_in_album",
            joinColumns = { @JoinColumn(name = "id_album") },
            inverseJoinColumns = { @JoinColumn(name = "id_song") })
    private List<Song> songs;

    private String name;
    private Timestamp timestampAdded;
    private long likes;
    private boolean isPublished;
    private long auditions;
}
