package com.example.gagong.dto.response;

public record TodoOne(
	Long id,
	String title,
	String author,
	String manager

) {
	public static TodoOne of(Long id, String title, String author, String manager) {
		return new TodoOne(id, title, author, manager);
	}
}
