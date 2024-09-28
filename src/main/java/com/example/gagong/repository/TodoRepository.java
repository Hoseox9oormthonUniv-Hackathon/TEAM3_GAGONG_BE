package com.example.gagong.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gagong.entity.InviteCode;
import com.example.gagong.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query("SELECT t FROM Todo t "
		+ "JOIN FETCH t.author a "
		+ "JOIN FETCH t.manager m "
		+ "WHERE t.inviteCode = :inviteCode "
		+ "AND t.completed = false "
		+ "AND DATE(t.createdAt) = :now "
		+ "ORDER BY t.createdAt DESC")
	List<Todo> findAllBeforeCurrentDate(@Param("now") LocalDate now, @Param("inviteCode") InviteCode inviteCode);

	@Query("SELECT t FROM Todo t "
		+ "JOIN FETCH t.author a "
		+ "JOIN FETCH t.manager m "
		+ "WHERE t.inviteCode = :inviteCode "
		+ "AND t.completed = false "
		+ "AND DATE(t.createdAt) = :now")
	List<Todo> findByInviteCodeToday(@Param("now") LocalDate now, @Param("inviteCode") InviteCode inviteCode);

	@Query("SELECT t FROM Todo t "
		+ "JOIN FETCH t.author a "
		+ "JOIN FETCH t.manager m "
		+ "WHERE t.inviteCode = :inviteCode "
		+ "AND t.completed = false "
		+ "AND DATE(t.createdAt) = :now "
		+ "ORDER BY t.createdAt DESC")
	List<Todo> mainTodoList(@Param("inviteCode") InviteCode inviteCode, @Param("now") LocalDate now, Pageable pageable);
}
