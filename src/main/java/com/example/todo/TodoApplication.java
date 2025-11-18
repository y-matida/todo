package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplicationは以下の３つのアノテーションをまとめている。
// @Configration（設定クラス）
// @EnableAutoCOnfiguration （自動構成をONに）
// @ComponentScan（同じパッケージ以下をスキャン）

@SpringBootApplication
public class TodoApplication {

	//main()メソッドはJavaアプリの起点となる。SpringApprication.run()を呼び出すことでSpring Bootアプリを起動する
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
