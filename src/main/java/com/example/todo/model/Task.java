package com.example.todo.model;

import jakarta.persistence.*;
import lombok.Data;

// @Entity：このクラスがJPAのエンティティ（DBテーブルにマッピングされる）であることを示す。
// @Data：Lombokのアノテーション。getter/setter、toString()、equals()、hashCode()などを自動生成する。
// フィールドと意味
// id : 主キー、自動で増えるID
// title：タスクのタイトル、入力必須
// completed：タスクが完了済みかどうか、初期値はfalse
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean completed = false;
}
