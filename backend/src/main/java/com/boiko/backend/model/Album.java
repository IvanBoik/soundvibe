package com.boiko.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "albums")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_authors",
            joinColumns = { @JoinColumn(name = "id_album") },
            inverseJoinColumns = { @JoinColumn(name = "id_author") })
    private List<Author> authors;

    @OneToOne
    @JoinColumn(name = "id_picture")
    private FileInfo picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "songs_in_albums",
            joinColumns = { @JoinColumn(name = "id_album") },
            inverseJoinColumns = { @JoinColumn(name = "id_song") })
    private List<Song> songs;

    private String name;
    private Timestamp timestampAdded;
    private long likes;
    private boolean isPublished;
    private long auditions;
}
