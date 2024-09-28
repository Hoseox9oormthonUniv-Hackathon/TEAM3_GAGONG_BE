package com.example.gagong.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gagong.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query("SELECT t FROM Todo t "
		+ "JOIN FETCH t.author a "
		+ "JOIN FETCH t.manager m "
		+ "WHERE t.inviteCode.id = :inviteCodeId "
		+ "AND t.completed = false "
		+ "AND t.createdAt < :date "
		+ "ORDER BY t.createdAt DESC")
	List<Todo> findAllBeforeCurrentDate(@Param("inviteCodeId") Long inviteCodeId, @Param("date") LocalDate date);

	@Query("SELECT t FROM Todo t "
		+ "JOIN FETCH t.author a "
		+ "JOIN FETCH t.manager m "
		+ "WHERE t.inviteCode.id = :inviteCodeId "
		+ "AND t.completed = false "
		+ "AND t.createdAt = :date")
	List<Todo> findByInviteCodeToday(@Param("inviteCodeId") Long inviteCodeId, @Param("date") LocalDate date);

	Page<Todo> findByInviteCode_IdAndCompletedFalseAndCreatedAt(Long inviteCodeId, LocalDate createdAt,
		Pageable pageable);

	List<Todo> findByInviteCode_IdAndCompletedFalseAndCreatedAt(Long inviteCodeId, LocalDate createdAt);

}
