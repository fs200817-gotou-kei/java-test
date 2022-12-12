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

import lombok.Data;

// TODO: Entityとかアノテーションの効果
@Entity
// TODO: hibernateのtableとpresistenceのtableの違い
@Table(name = "worksites")
// TODO: getter, setter全部定義したことにしていいの？確か必要な奴だけにsettterとかgetter設定するのあったはず
@Data
public class WorkSite {

    // Idはプライマリキーの指定なのはわかるけどつけることで何が起こるのか
    @Id
    // GeneratedValueやGenerationTypeは他とどう違うのか、他のオプションは何があるか
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    // TODO: idをLong型で定義する根拠は？intは？integerは？
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "staff_name")
    private String staffName;

    // TODO: Lob, Typeとは？
    // TODO: blob型とbyte型の関係性
    // byte型である根拠は？
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column(name = "photo")
    private byte[] photo;

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
    // TODO: createdAt, updatedAtの自動生成機能実装、理解
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
