package com.example.todo.repository;

import com.example.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// テスト目的：Spring Data JPAのTaskRepositoryが正しくデータベースと連携しているかを検証する。
// 実際のH2データベース上でTaskRepositoryが想定通りに動作するかを確認する。

// assertThat()：値の比較や空チェックを直感的に行うための表現力の高いアサーション

// JPAに関するコンポーネント（EntityManager、Repository）のみを読み込み、DBに関するテストを高速・簡潔に行うためのアノテーション
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    // testSaveAndFind(): タスクを保存後、findAll()で取得し、保存されていることを確認する。
    @Test
    void testSaveAndFind() {
        Task task = new Task();
        task.setTitle("Repository Test");

        taskRepository.save(task);

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).getTitle()).isEqualTo("Repository Test");
    }

    // taskDelete(): 保存したタスクをdeleteById()で削除し、findById()で取得できないことを確認する。
    @Test
    void testDelete() {
        Task task = new Task();
        task.setTitle("To be deleted");

        Task saved = taskRepository.save(task);
        taskRepository.deleteById(saved.getId());

        assertThat(taskRepository.findById(saved.getId())).isEmpty();
    }
}
