SpringBootを用いたTODOリストアプリケーション 概要
フロントエンドはHTMLベースで作成し、データベース登録などの処理を
バックエンドのJava Spring Frameworkを用いて処理する

フロントエンド側（todo_app_front フォルダ）
index.html  メイン画面
style.css   スタイルファイル
main.js     ブラウザでの処理を担当、REST_APIを用いてサーバと通信

基本操作
1.メイン画面を表示
2.登録するタスクを入力し、「追加ボタン」をクリック
3.登録したタスクが表示される。画面を更新してもデータはクリアされない
4.登録したタスクを削除する場合は、リスト内の「削除ボタン」をクリックする

JavaScript の動作
通信を行うサーバのアドレスはAPI_URLに格納
タスクを画面に表示する関数
1.fetchメソッドを使用して、APIからデータを取得
2.HTML要素を操作し、既存のリストを一度クリア
3.サーバから取得したタスクデータを1つずつ処理。<li>要素を作成し、タスクのタイトルと削除ボタンを追加
　作成した<li>をリストに追加し表示する。

新しいタスクの追加
1.入力欄の新しいタスクのタイトルを取得
2.取得したデータを、POSTメソッドを使用してAPIに送信。データ形式はJSON
3.UIを更新する

タスクの削除
1.タスクIDをURLに含めて、DELETEメソッドでサーバにリクエストを送信
2.UIを更新し、削除されたことを確認する。

バックエンド側 （todo プロジェクトフォルダ）
TodoApprication.java    アプリケーションの起点、実行ファイル
Task.java               タスクデータの「定義」
TaskRepository.java     データベースアクセスのインターフェース
TaskController.java     HTTPリクエストを処理するAPIの入り口
apprication.properties  DBやJPAの「設定ファイル」

TodoApprication.java  機能ファイル
SpringBootの起動ファイル。
このファイルを実行しJavaアプリケーションを動作させる。

Task.java モデル・エンティティ
このクラスはデータベースのtasksテーブルに対応する「エンティティ」となる

TaskRepository.java リポジトリ
JPAを使ったデータ操作用インターフェース
JpaRepository<T,ID>を継承することで、データベース操作用のメソッドが使用できるようになる。
SQLを書かずにデータベースを操作することができる。

TaskController.java
HTTPリクエストに応じた実際の処理を操作するファイル
各HTTPメソッドに対応する処理を記述


