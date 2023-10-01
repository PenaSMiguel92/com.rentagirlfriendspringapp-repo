package com.rentagirlfriend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rentagirlfriend.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("FROM Message WHERE received_by = :received_byVar")
    public List<Message> getReceivedMessages(@Param("received_byVar") long received_by);

}
