package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController：JSON形式でレスポンスを返すController
// @RequestMapping（"/tasks"）：APIのルートパスを指定
// @CrossOrigin：CORS(クロスオリジン通信)を許可（JSからアクセス可）

// 各メソッドの役割：これらはすべて、fetch()などのJavaScriptから呼び出すAPIエンドポイントになる
// getTasks()：GET：タスク一覧取得
// createTask()：POST：新しいタスクの作成
// updateTask()：PUT：タスクの更新
// deleteTask()：DELETE：タスクの削除

@CrossOrigin(origins = "*") //フロントエンドとの接続許可
@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    //@Autowired：依存性注入（DI）、クラスが必要とする依存オブジェクトを自動的に注入する
    @Autowired
    private TaskRepository taskRepository;

    //一覧取得
    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    //新規作成
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    //更新
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updated) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(updated.getTitle());
        task.setCompleted(updated.isCompleted());
        return taskRepository.save(task);
    }

    //削除
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
