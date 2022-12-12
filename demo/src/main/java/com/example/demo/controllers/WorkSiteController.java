package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/worksites")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class WorkSiteController<T> {

    // すべてのメソッドはクライアントへ接続状況と値を渡したいのでResponseEntityで渡すようにする
    public ResponseEntity<T> geAllWorkSites() {
        return null;
    }

    // TODO: Optinalで受け取るとnullとか値が入っていなくても受け取れる
    //
}