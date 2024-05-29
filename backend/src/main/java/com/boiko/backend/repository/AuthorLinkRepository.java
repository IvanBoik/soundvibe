package com.boiko.backend.repository;

import com.boiko.backend.model.AuthorLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorLinkRepository extends JpaRepository<AuthorLink, Long> {
}
