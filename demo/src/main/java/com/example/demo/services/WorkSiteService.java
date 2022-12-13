package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.WorkSiteRepository;

// Serviceの@は前回とかなくても反応してた気するけどどういう意味がある？
@Service
public class WorkSiteService {

    // TODO: Autowiredのメリット
    // → 一々new書かなくてもいいのは楽
    @Autowired
    private WorkSiteRepository workSiteRepository;

    // TODO: なんで<T>をメソッドやクラスに着ける必要があるのか
    // TODO: http通信のコードの規約調査
    // TODO: header付与しなかったときのheader値は何が設定されているか
    // →多分、何も設定されていないと思う。
    // responseEntityはhttpのステータスコード、header情報、本文の情報を設定できるオブジェクト
    public <T> ResponseEntity<T> getAllWorkSites() {
        // TODO: データがある場合は普通に対象データをResponseEntity型に格納しstatusCodeとheader情報を設定して返すだけ
        // TODO: JpaRepositoryがどういう型を返してくれるようになっているか調べる必要がある
        // → findAllに関してはList<T>が返ってくる
        // TODO: どうエラー種類などを検知するか
        // → try, catch？
        // → 前ってnotfoundexceptionはできてたけどどういう原理だっけ？
        // 検索＞何らかの例外＞エラー返ってくる＞エラー判定
        // TODO: ここでちゃんと例外別に分岐させてheader情報とか設定したりする必要がある
        this.workSiteRepository.findAll();
    }
}
