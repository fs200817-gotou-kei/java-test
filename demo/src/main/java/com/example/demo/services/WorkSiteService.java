package com.example.demo.services;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.models.WorkSite;
import com.example.demo.repositories.WorkSiteRepository;

// Serviceの@は前回とかなくても反応してた気するけどどういう意味がある？
@Service
public class WorkSiteService {

    // TODO: Autowiredのメリット
    // → 一々new書かなくてもいいのは楽
    @Autowired
    private WorkSiteRepository workSiteRepository;

    // TODO: spring bootの例外の流れとかが理解できない
    // https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DataAccessJpa.html
    // TODO: このクラスかどうかは検討が必要だがアプリ起動時にdbに接続できなかった場合の例外処理実装が必要
    // TODO: なんで<T>をメソッドやクラスに着ける必要があるのか
    // TODO: http通信のコードの規約調査
    // TODO: header付与しなかったときのheader値は何が設定されているか
    // TODO: 自分で例外をたくさん候補挙げてみる
    // →多分、何も設定されていないと思う。
    // responseEntityはhttpのステータスコード、header情報、本文の情報を設定できるオブジェクト
    // TODO: 本当は<T>にしたい
    public ResponseEntity<List<WorkSite>> getAllWorkSites() {

        // TODO: データがある場合は普通に対象データをResponseEntity型に格納しstatusCodeとheader情報を設定して返すだけ
        // TODO: JpaRepositoryがどういう型を返してくれるようになっているか調べる必要がある
        // → findAllに関してはList<T>が返ってくる
        // TODO: どうエラー種類などを検知するか
        // → try, catch？
        // → 前ってnotfoundexceptionはできてたけどどういう原理だっけ？
        // 検索＞何らかの例外＞エラー返ってくる＞エラー判定
        // TODO: ここでちゃんと例外別に分岐させてheader情報とか設定したりする必要がある

        // TODO: 戻り値がList<T>だから受け取り方としてはList<WorkSite>になってしまうのは仕方ない
        // TODO: データない場合nullが取れるのか
        // オーバーライドは意味なさそう
        // 空でもアプリ自体は落ちておらずokを設定したら正常に空で200が返っていた

        // TODO: どういう例外になる？
        // URL違うならこのメソッドの処理じゃない、id範囲外もここの処理じゃない
        // ①dbのアクセスが途中で切れたパターン
        // → ↓の②があれば特に運用するにあたって途中できれたかどうかは操作していてわかるからdbとの接続がないかどうかだけわかればよさそう
        // ②dbと最初から接続ができないパターン
        // 接続トライのタイムアウト時間決めたい
        // 途中でdocker切ってapiたたいたら→が出た java.net.ConnectException: Connection refused: no
        // further information
        // ③dbには接続できてデータ取れたけどクライアントとの接続が切れたパターン
        /// → 接続してデータ取得とreturnを分ければ行けそう
        // ④他の人も問い合わせしていてdbでtransaction張っていた場合

        // 下記は最初の実装段階で間違えなければ特にそのあとはPGとして残しておく必要がないから運用上いらいないからなし
        // ④dbがない場合
        // ⑤テーブルがない場合

        // jparepositoryから帰ってくるのがList<T>って決まっているから例外とか返してくれないのかな？
        // → ってよりはconnectExceptionが返ってこないってことらしい
        // → 要するに多分これじゃdockerきってのエラーはcatchできないっぽい

        // TODO: 結論：途中でdb接続切れてアクセスできなときは別の場所で例外処理する必要がありそう
        // TODO: これもキャッチしたほうがいいのか → 2022-12-15 08:41:05.557 WARN 8956 ---
        // [nio-8080-exec-2]
        // o.h.engine.jdbc.spi.SqlExceptionHelper : SQL Error: 0, SQLState: 08S01
        // 2022-12-15 08:41:05.557 ERROR 8956 --- [nio-8080-exec-2]
        // o.h.engine.jdbc.spi.SqlExceptionHelper : HikariPool-1 - Connection is not
        // available, request timed out after 30019ms.
        // 2022-12-15 08:41:05.562 ERROR 8956 --- [nio-8080-exec-2]
        // o.h.engine.jdbc.spi.SqlExceptionHelper : Communications link failure

        // 一旦これでちゃんとcatchできている
        // 200も返っている(ないと500返る)

        List<WorkSite> workSites = Collections.emptyList();
        try {
            workSites = this.workSiteRepository.findAll();
            return ResponseEntity.ok(workSites);
            // repositoryがcatchしてこっちまで戻ってきてないってことかな？
            // TODO: JDBCエラーに指定したらcatchできなかった
        } catch (Exception ex) {
            // TODO: logging
            System.out.println(ex.toString());
            System.out.println("OK");

            // TODO: header情報明示化する、statusCOdeとかエラーメッセージとかも付加する
            return ResponseEntity.ok(workSites);
            // return ResponseEntity.status(HttpStatus.BAD_GATEWAY);
        }

        // 通信さえできていれば空の状態でok設定しなくても普通に空で200返っている
        // statusによってstatusCodeは変えたいのでifで分岐してResponseEntityの中のStatusCodeとかbodyの値を変えたりしてあげる
        // frontへは普通に空の状態のオブジェクトとメッセージとステータスコードだけ乗せればいいと思う
        // → 空ってだけで別に割ることでも何でもない、ただデータないってだけ
    }

    public ResponseEntity<WorkSite> getWorkSiteById(Long id) {
        WorkSite workSite = new WorkSite();

        try {
            return ResponseEntity.ok(this.workSiteRepository.findById(id).orElseThrow(() -> new NotFoundException()));
        } catch (Exception ex) {
            System.out.println(ex.toString());
            // TODO: header情報明示化する、statusCOdeとかエラーメッセージとかも付加する
            return ResponseEntity.ok(workSite);
        }
    }
}
