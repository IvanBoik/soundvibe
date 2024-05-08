package com.boiko.music_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "playlists")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_author")
    private Long authorID;

    @OneToOne
    @JoinColumn(name = "picture")
    private FileInfo picture;

    private String name;
    private boolean isPublic;
    private Timestamp timestampCreated;
}
