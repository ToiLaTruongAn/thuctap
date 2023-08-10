package com.project.urban.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.project.urban.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("from Event e where not(e.end < :from or e.start > :to)")
	public List<Event> findBetween(@Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end);

	@Query(value = "SELECT e.id, e.bussiness_trip, e.company, e.event_end, e.event_start, e.text, e.Task_id, e.User_id, u.MaNV, t.name "
			+ "FROM Event e " + "LEFT JOIN users u ON e.User_id = u.id " + "LEFT JOIN tasks t ON e.Task_id = t.id "
			+ "WHERE u.MaNV = :MaNV ", nativeQuery = true)
	List<Event> findByMaNV(@Param("MaNV") String MaNV);

	@Query(value = "SELECT e.id, e.bussiness_trip, e.company, e.event_end, e.event_start, e.text, e.Task_id, e.User_id, u.MaNV, t.name "
			+ "FROM Event e " + "LEFT JOIN users u ON e.User_id = u.id " + "LEFT JOIN tasks t ON e.Task_id = t.id "
			+ "WHERE u.name = :name ", nativeQuery = true)
	public List<Event> findByName(@Param("name") String name);

	@Query(value = "SELECT e.id, e.bussiness_trip, e.company, e.event_end, e.event_start, e.text, e.Task_id, e.User_id, u.MaNV, t.name "
			+ "FROM Event e " + "LEFT JOIN users u ON e.User_id = u.id " + "LEFT JOIN tasks t ON e.Task_id = t.id "
			+ "WHERE e.company = :name ", nativeQuery = true)
	public List<Event> findByCompany(@Param("name") String name);

	@Query(value = "SELECT e.id, e.bussiness_trip, e.company, e.event_end, e.event_start, e.text, e.Task_id, e.User_id, u.MaNV, t.name "
			+ "FROM Event e " + "LEFT JOIN users u ON e.User_id = u.id " + "LEFT JOIN tasks t ON e.Task_id = t.id "
			+ "WHERE t.name = :name ", nativeQuery = true)
	public List<Event> findByTask(@Param("name") String name);
}
