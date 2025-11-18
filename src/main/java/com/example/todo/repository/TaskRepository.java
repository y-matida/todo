package com.example.todo.repository;

import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepositoryを継承することで、以下のメソッドが自動で使えるようになる。
// findAll(): 全件取得
// findById(id): IDで1件取得
// save(entity) : 新規保存/更新
// deleteById(id) : IDで削除
public interface TaskRepository extends JpaRepository<Task,Long> {
} 
