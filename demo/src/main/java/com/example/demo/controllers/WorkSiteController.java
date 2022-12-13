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

import com.example.demo.Constants.AppConstants;
import com.example.demo.models.WorkSite;

// 現場情報に関する問い合わせを管理するクラス
@RestController
@RequestMapping(AppConstants.WORK_SITE_URL)
@CrossOrigin(origins = AppConstants.WORK_SITE_CORS)
public class WorkSiteController<T> {

    // すべてのメソッドはクライアントへ接続状況と値を渡したいのでResponseEntityで渡すようにする
    // すべての現場情報を返す
    // TODO: 本当にTじゃなければいけない？
    // TODO: ResponseEntityで返すと返さずにそのまま値を返すとの違いは何か
    @GetMapping()
    public ResponseEntity<T> getAllWorkSites() {
        return null;
    }

    @GetMapping(AppConstants.WORK_SITE_REQUEST_BY_ID_URL)
    public ResponseEntity<T> getWorkSiteById(@PathVariable Long id) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<T> createWorkSite(@RequestBody WorkSite workSite) {
        return null;
    }

    @PutMapping(AppConstants.WORK_SITE_REQUEST_BY_ID_URL)
    public ResponseEntity<T> updateWorkSite(@RequestBody WorkSite workSite) {
        return null;
    }

    @DeleteMapping(AppConstants.WORK_SITE_REQUEST_BY_ID_URL)
    public ResponseEntity<T> deleteWorkSite(@PathVariable WorkSite workSite) {
        return null;
    }

    // TODO: Optinalで受け取るとnullとか値が入っていなくても受け取れる
}