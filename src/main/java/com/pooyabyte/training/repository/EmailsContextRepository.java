package com.pooyabyte.training.repository;

import com.pooyabyte.training.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailsContextRepository extends JpaRepository<Notification,Integer> {
    Optional<Notification> findById(Integer id);
}
