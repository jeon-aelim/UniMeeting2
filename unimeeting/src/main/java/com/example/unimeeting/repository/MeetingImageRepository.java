package com.example.unimeeting.repository;

import com.example.unimeeting.domain.MeetingImage;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface MeetingImageRepository extends JpaRepository<MeetingImage, Integer> {

}
