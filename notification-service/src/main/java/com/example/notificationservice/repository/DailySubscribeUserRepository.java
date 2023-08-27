package com.example.notificationservice.repository;

import com.example.notificationservice.model.entity.DailySubscribeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySubscribeUserRepository extends JpaRepository<DailySubscribeUser,Long> {
}
