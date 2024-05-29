package com.boiko.backend.repository;

import com.boiko.backend.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    @Query("""
    select f from FileInfo f
    where f.S3Key = 'default_avatar.svg'
    """)
    FileInfo getDefaultAvatar();
}
