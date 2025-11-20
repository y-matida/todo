package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// テスト目的：REST APIが正しくHTTPリクエストを受け取り、正しいレスポンスを返すかを確認する。

// @WebMvcTestを使用し、TaskControllerだけを対象としたWeb層の単体テスト
// ＠WebMvcTest：Web層（コントローラ）のみを対象に行う
// サーバを起動せずにMockMvcを使ってHTTPリクエストのシミュレーションが可能
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    // HTTPリクエストのシミュレーションに使用
    @Autowired
    private MockMvc mockMvc;

    // TaskControllerが依存するTaskRepositoryをMockitoでモック化し、実際のDBアクセスを回避する
    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // testGetTasks(): GETリクエストでタスクが返されるかを検証。JSONレスポンスの中身（title）も確認する。
    @Test
    void testGetTasks() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));
    }

    // testCreateTask(): POSTリクエストで新規タスクが作成されるかを確認。レスポンスのidとtitleを検証
    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        task.setTitle("New Task");

        Task saved = new Task();
        saved.setId(1L);
        saved.setTitle("New Task");

        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(saved);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Task"));
    }

    // testUpdateTask(): PUTリクエストで既存のタスクを更新し、変更が正しく反映されるかを確認する。
    @Test
    void testUpdateTask() throws Exception {
        Task existing = new Task();
        existing.setId(1L);
        existing.setTitle("Old Title");

        Task updated = new Task();
        updated.setTitle("Updated Title");
        updated.setCompleted(true);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));
        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(updated);

        mockMvc.perform(put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    // testDeleteTask(): DELETEリクエストでタスクが正常に削除されるかを確認する
    @Test
    void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskRepository).deleteById(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk());
    }
}
