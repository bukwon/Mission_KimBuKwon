package com.ll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
class App {
    @Getter
    @Setter
    int id;
    @Getter
    @Setter
    String content;
    @Getter
    @Setter
    String author;

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}   // id, 명언, 작가 정보 입력 받기 및 출력 이용