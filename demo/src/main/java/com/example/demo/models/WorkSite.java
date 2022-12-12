package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

// TODO: @の具体的な処理の中身を見たりしてもいいかも
// Entityとかアノテーションの効果→apiを作成するときなどにormの仕組みを使ってdbのテーブルと結びつけるための目印
@Entity
// TODO: hibernateのtableとpresistenceのtableの違い(presistenceのでいいらしい)
@Table(name = "worksites")
// TODO: getter, setter全部定義したことにしていいの？確か必要な奴だけにsettterとかgetter設定するのあったはず
@Data
public class WorkSite {

    // Idはプライマリキーの指定なのはわかるけどつけることで何が起こるのか
    @Id
    // GeneratedValueやGenerationTypeは他とどう違うのか、他のオプションは何があるか
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    // idをLong型で定義する根拠は？intは？integerは？ →
    // idがbigint型で8byteの格納領域に対してlongも同じ量だけの格納領域がありかつ数字型で同じであるから
    // 大切なのは一致してないと片方で入っていたものがもう片方では容量オーダーなどになっててしまわないこと
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "staff_name")
    private String staffName;

    // TODO: Lob, Typeとは？
    // TODO: blob型とbyte型の関係性
    // TODO: このかたで容量オーダーとかにならない？共通の型ってことになる？
    // TODO:
    // MEDIUMBLOBってちゃんと認識してくれている?(mediumは型としてあるのか)(columnDefinitionの種類みたいな感じで検索すれば出てきそう)
    // https://www.baeldung.com/hibernate-lobを見た感じ画像はこれでもOKそう
    // byte型である根拠は？
    // LobはデータベースがプロパティをLarge Objectとして格納する必要があることを指定している
    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    // TODO: varchar(255)はStringに対応させていいのか？
    // stringは最大bayte数は一応あるけどその上限に達する前にヒープ領域が足りなくなるらしい
    @Column(name = "adress")
    private String adress;

    @Column(name = "status")
    private String status;

    // TODO: sqlとutilのDate型の違い
    // TODO: Date型の根拠は？他のDate系列の型じゃダメなの？
    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    // TODO: 元のdb定義はDatetime型だけどDate型でいいの？
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

}
