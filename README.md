my-java-basics

# 概要

Javaの基本機能や標準/非標準APIを使用したサンプルコードです。

# 構成

[`src/samples/`](src/samples/) 配下に各サンプルコードを格納しています。

## [`stream_api`](src/samples/stream_api/)

StreamAPIのサンプルコードです。

使用メソッド:

- 中間処理
  - `filter()`,`map()`,`sorted()`,`limit()`,`distinct()`

- 終端処理
  - `forEach()`,`collect()`,`anyMatch()`,`sum()`,`max()`,`min()`,`findFirst()`,`count()`

## [`generics_and_enum`](src/samples/generics_and_enum/)

ジェネリクスと列挙型を使用したサンプルクラス（金庫クラス（StrongBoxクラス））を定義しています。

金庫クラスの主な仕様:

- 金庫クラスは任意の型のインスタンスを1つ保持でき、格納するインスタンスの型は金庫クラスのインスタンス生成時に実型引数で指定します。
- コンストラクタで「鍵の種類」（ダイヤル式・指紋認証など）を指定し、鍵の種類ごとに定められた必要試行回数に達するまで金庫に格納したインスタンスは取得不可とします。

列挙型では `toString()` のオーバーライドと独自のフィールド及びメソッドの定義を行っています。  
金庫クラスは JUnit でテストを行なっています（StringBoxTestクラス）。

## [`method_reference`](src/samples/method_reference/)

関数型インターフェースと関数オブジェクト（メソッド参照）のサンプルコードです。

## [`lambda_expression`](src/samples/lambda_expression/)

ラムダ式のサンプルコードです。

標準関数インターフェースの `IntPredicate` と `BiFunction` を使用し関数を即時実装します。  
匿名クラスでの記述例も載せてあります。

## [`system_property`](src/samples/system_property/)

JVMやOSの情報を保持するシステムプロパティのサンプルコードです。

## [`locale`](src/samples/locale/)

場所（国）や言語の情報を表す `Locale` クラスのサンプルコードです。

ロケールの言語によって現在日時の表示内容を切り替えます。  
本サンプルでは「JP-ja」と「US-en」を使用します。

## [`jvm_memory`](src/samples/jvm_memory/)

`Runtime` クラスのサンプルコードです。

JVMの現在のメモリ状態を表示します。

## [`reflection`](src/samples/reflection/)

リフレクションAPIのサンプルコードです。

`String` クラスの型情報と、すべてのフィールド名・メソッド名を表示します。

## [`commons_lang`](src/samples/commons_lang/)

Javaの基本機能を強化する Apache Commons の [commons-lang](https://commons.apache.org/proper/commons-lang/) ライブラリを使用したサンプルコードです。

`EqualsBuilder` で `Object#equals()` のオーバーライドを、`ToStringBuilder` で `Object#toString()` のオーバーライドを手軽に実装します。

ライブラリを使用しない典型的な実装サンプルも載せてあります。

## [`logger`](src/samples/logger/)

ロガーライブラリのサンプルコードです。

`Log4j2` 単体でのログ出力と、`SLF4J`（ログファサード） + `Logback` によるログ出力のサンプルコードです。

## [`file_io`](src/samples/file_io/)

ファイル入出力（`java.io`パッケージ）に関するサンプルコードです。

読み込んだファイルをGZIP圧縮するプログラムを以下のAPIを使用して作成します。

使用API：
- `FileInputStream`（バイナリファイル読み込み用ストリーム）
- `BufferedInputStream`（パフォーマンス向上のためのバッファリングフィルタ）
- `FileOutputStream`（バイナリファイル書き込み用ストリーム）
- `BufferedOutputStream`（パフォーマンス向上のためのバッファリングフィルタ）
- `GZIPOutputStream`（GZIP圧縮用ストリームフィルタ）

## [`property_file`](src/samples/property_file/)

プロパティファイル（.properties）に関するサンプルコードです。

`Properties`クラスでプロパティファイルを読み書きするサンプルと、  
`ResourceBundle`クラスでプロパティファイルを読み込み、メッセージを多言語対応するサンプルです。

## [`json`](src/samples/json/)

`Jackson`を用いてJSONファイルを読み書きするサンプルコードです。

Javaオブジェクト → JSON（シリアライズ）、及び  
JSON → Javaオブジェクト（デシリアライズ）を行います。

## [`object_serialization`](src/samples/object_serialization/)

オブジェクトの直列化に関するサンプルコードです。

オブジェクトをシリアライズしファイルに保存します。  
また、シリアライズしたファイルをオブジェクトに復元（デシリアライズ）します。

## [`stream`](src/samples/stream/)

ストリームに関するサンプルコードです。

文字列（String）をバイトストリームや文字ストリームに変換します。

使用API：
- `ByteArrayInputStream`（`InputStream`の具象クラス。バイトストリーム）
- `InputStreamReader`（`Reader`の具象クラス。バイトストリームと文字ストリームの橋渡し役）
- `StringReader`（`Reader`の具象クラス。文字ストリーム）

## [`url`](src/samples/url/)

`URL`クラスを用いてWebサイトのHTMLを取得するサンプルコードです。

## [`web_api_call`](src/samples/web_api_call/)

`HttpClient`でWebAPIにアクセスするサンプルコードです。

[Qiita API v2](https://qiita.com/api/v2/docs) の `https://qiita.com/api/v2/items` エンドポイントより最新の投稿記事を取得します。  
WebAPIから取得したJSONは `Jackson` で処理します。

実行結果：  
<img src="https://user-images.githubusercontent.com/59589496/122574699-e3274d00-d08a-11eb-9d14-261bfacd0c9f.png" width="90%">

## [`jdbc`](src/samples/jdbc/)

JDBCでDBアクセスするサンプルコードです。

DBMSは H2 Database を使用します。

実行結果：  
<img src="https://user-images.githubusercontent.com/59589496/122884171-d51e4880-d378-11eb-9aae-ddb3770d41fc.png" width="90%">

## [`assertion`](src/samples/assertion/)

アサーション (`assert`文) のサンプルコードです。

プログラムに`assert`文を仕込むことで、プログラムやシステムが異常な状態で稼働し続ける事を防ぎます。  
(`assert`文の評価が false の場合、`AssertionError`が発生し JVM は強制終了する)

アサーションを有効にするには、java コマンドに `-ea` オプションを付けます。

```
$ java -ea Main
```


