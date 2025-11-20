package com.example.todo;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// テスト目的：アプリケーション全体を通してのテスト。アプリを起動し、エンドポイントへのアクセスからDB処理までを含めた流れをテストする。
// Spring Bootを実際に起動して、TaskRepositoryを通じて永続層の動作を確認する

// テスト内容のポイント
// タスクを新規に作成し、保存できるかを確認
// そのタスクをfindAll()で取得し、保存されていること、値が正しいことを検証する
// アプリケーションのコンポーネント間連携（エンティティ → リポジトリ → データベース）の一連の流れを通じて検証する

// @SpringBoot: アプリケーション全体（Web、DB、DIなど）を起動し、実際の動作環境に近い形でテストを実行する。
// @Transactional: 各テストメソッドで行ったDB操作は、テスト終了後に自動でロールバックされる。これにより、ほかテストへの影響を防ぐ。
@SpringBootTest
@Transactional
public class TodoApplicationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testTaskFlow() {
        Task task = new Task();
        task.setTitle("Integration Test");

        Task saved = taskRepository.save(task);
        assertThat(saved.getId()).isNotNull();

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.get(0).getTitle()).isEqualTo("Integration Test");
    }
}