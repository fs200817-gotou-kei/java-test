package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.WorkSite;

// 現場情報に関する問い合わせを管理するクラス
@RestController
@RequestMapping("/api/v1/worksites")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class WorkSiteController<T> {

    // すべてのメソッドはクライアントへ接続状況と値を渡したいのでResponseEntityで渡すようにする
    // すべての現場情報を返す
    @GetMapping("")
    public ResponseEntity<T> getAllWorkSites() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getWorkSiteById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<T> createWorkSite(@RequestBody WorkSite workSite) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateWorkSite(@RequestBody WorkSite workSite) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> deleteWorkSite(@PathVariable WorkSite workSite) {
        return null;
    }

    // TODO: Optinalで受け取るとnullとか値が入っていなくても受け取れる
}