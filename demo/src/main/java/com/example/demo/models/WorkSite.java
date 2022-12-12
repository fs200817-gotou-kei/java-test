package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.demo.Constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// 現場情報を格納するクラス
// TODO: @の具体的な処理の中身を見たりしてもいいかも
// TODO: 大切なのはdbなどときっちり整合性が取れているか、例外など起きたりしないか
// TODO: size通り入るかギリギリの領域とかでテストする
// Entityとかアノテーションの効果→apiを作成するときなどにormの仕組みを使ってdbのテーブルと結びつけるための目印
@Entity
// hibernateのtableとpresistenceのtableの違い(presistenceのでいいらしい)
// → 基本的にpresistenceでよいみたい。(使用するものの意味を把握しておけばよい)
// → Table名を元に実際のテーブルと紐づけるための目印
// → 他にもschema指定ができたりとオプションがある
@Table(name = "worksites", schema = "worksites")
// TODO: getter, setter全部定義したことにしていいの？確か必要な奴だけにsettterとかgetter設定するのあったはず
@Data
public class WorkSite {

    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    // Idはプライマリキーの指定なのはわかるけどつけることで何が起こるのか
    @Id
    // GeneratedValueやGenerationTypeは他とどう違うのか、他のオプションは何があるか
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // idをLong型で定義する根拠は？intは？integerは？ →
    // idがbigint型で8byteの格納領域に対してlongも同じ量だけの格納領域がありかつ数字型で同じであるから
    // 大切なのは一致してないと片方で入っていたものがもう片方では容量オーダーなどになっててしまわないこと
    // TODO: mysqlのbigint(8)って本当に255でいいの？
    @Size(max = AppConstants.MAX_SIZE_ID)
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    @Size(max = AppConstants.MAX_SIZE_NAME)
    private String name;

    @Column(name = "type")
    @JsonProperty("type")
    @Size(max = AppConstants.MAX_SIZE_TYPE)
    private String type;

    @Column(name = "staff_name")
    @JsonProperty("staff_name")
    @Size(max = AppConstants.MAX_SIZE_STAFF_NAME)
    private String staffName;

    // TODO: blob型とbyte型の関係性
    // TODO: このかたで容量オーダーとかにならない？共通の型ってことになる？
    // TODO:
    // MEDIUMBLOBってちゃんと認識してくれている?(mediumは型としてあるのか)(columnDefinitionの種類みたいな感じで検索すれば出てきそう)
    // https://www.baeldung.com/hibernate-lobを見た感じ画像はこれでもOKそう
    // byte型である根拠は？
    // Lobとは？
    // → LobはデータベースがプロパティをLarge Objectとして格納する必要があることを指定している
    // TODO: Lobなしではだめなのか、columnDefinitionなしではだめなのか
    // TODO: Sizeいくつ？
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    @JsonProperty("photo")
    @Lob
    @Size(max = AppConstants.MAX_SIZE_PHOTO)
    private byte[] photo;

    // TODO: varchar(255)はStringに対応させていいのか？
    // stringは最大bayte数は一応あるけどその上限に達する前にヒープ領域が足りなくなるらしい
    // varchar(255)は文字数を表している
    // バイト数で表してない理由としてはおそらく文字コードに依存しないため
    // UTF-8でも255格納できるみたい
    // 255は1バイトで256だと2バイトらしい
    // 見た感じ254とか253とか減らしても結局変わらず1バイトの容量を確保するようだ
    // 計算としては1バイト=8bit=2の8乗=256
    // 256ではない理由としては最後の文末を表すCRを入れるから256 - 1 = 255になるようだ(人間に見えるのは255)
    @Column(name = "address")
    @JsonProperty("address")
    @Size(max = AppConstants.MAX_SIZE_ADDRESS)
    private String address;

    @Column(name = "status")
    @JsonProperty("status")
    @Size(max = AppConstants.MAX_SIZE_STATUS)
    private String status;

    // TODO: sqlとutilのDate型の違い
    // TODO: Date型の根拠は？他のDate系列の型じゃダメなの？
    // TODO: 時間形式が間違っている場合エラー出すようする
    @Column(name = "start_at")
    @JsonProperty("start_at")
    private Date startAt;

    @Column(name = "end_at")
    @JsonProperty("end_at")
    private Date endAt;

    // TODO: 元のdb定義はDatetime型だけどDate型でいいの？
    @Column(name = "created_at")
    @JsonProperty("created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    @LastModifiedDate
    private Date updatedAt;
}
