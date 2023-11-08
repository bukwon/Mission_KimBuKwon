package com.ll;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class function {

    public int pasteId;     // 수정 및 삭제 메뉴에 id만 따로 분할하기 위해 사용
    private List<App> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String fileName = "data.json";  // json 파일명
    int insertId = 0;

    void run() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            String menu = sc.nextLine();
            switch (divideWord(menu)) {
                case 1:
                    return;
                case 2:
                    addList();
                    break;
                case 3:
                    checkList(fileName);
                    break;
                case 4:
                    removeList();
                    break;
                case 5:
                    pasteList();
                    break;
                default:
                    System.out.println("올바른 명령어 부탁드립니다.");
            }
        }
    }

    int checkMenu(String menu) {
        int menuNum = 0;
        if (menu.equals("종료")) menuNum = 1;
        else if (menu.equals("등록")) menuNum = 2;
        else if (menu.equals("목록")) menuNum = 3;
        else if (menu.equals("삭제")) menuNum = 4;
        else if (menu.equals("수정")) menuNum = 5;
        return menuNum;
    }

    void addList() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        insertId++;
        System.out.println(insertId + "번 명언이 등록되었습니다.");
        App myApp = new App(insertId, content, author);
        list.add(myApp);
        saveListToFile(fileName);       // json 등록을 위한 메서드
    }

    void saveListToFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }                           // json 입력 과정
    }

    public void checkList(String filePath) {
        System.out.println("번호" + "\t" + "/" + "\t" + "작가" + "\t\t" + "/" + "\t" + "명언");
        System.out.println("------------------------");
        readListFromFile(filePath);     // json 읽기 전용
        for (App app : list) {
            System.out.println(app.id + "\t" + app.author + "\t" + app.content);
        }
    }

    void readListFromFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            Gson gson = new Gson();
            list = gson.fromJson(fileReader, new TypeToken<List<App>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }               // json 파일에서 읽어들이는 과정
    }

    void removeList() {         // 삭제 메서드
        boolean found = false;

        for (App app : list) {
            if (app.id == pasteId) {
                list.remove(app);
                found = true;       // 찾았거나 잘못 입력했거나 구분을 위해 boolean 설정
                break;
            }
        }

        if (found) {
            System.out.println(pasteId + "번 명언이 삭제되었습니다.");
            insertId--;
            for (int i = (pasteId - 1); i < list.size(); i++) {
                list.get(i).id--;
            }
            saveListToFile(fileName);           // 삭제한 파일 및 번호 변경을 위해 json 파일 수정
        } else {
            System.out.println("해당 번호의 명언을 찾을 수 없습니다.");
        }
    }

    void pasteList() {          // 수정 메서드
        boolean found = false;      // 삭제에 존재하는 found 변수와 기능 똑같음

        for (App app : list) {
            if (app.id == pasteId) {
                System.out.println("명언(기존) : " + app.content);
                System.out.print("명언 : ");
                app.content = sc.nextLine();
                System.out.println("작가(기존) : " + app.author);
                System.out.print("작가 : ");
                app.author = sc.nextLine();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println(pasteId + "번 명언이 수정되었습니다.");
            saveListToFile(fileName);                   // 수정된 내용 json파일에 저장
        } else {
            System.out.println("해당 번호의 명언을 찾을 수 없습니다.");
        }
    }

    int divideWord(String menu) {
        int divideWord = 0;
        if (menu.contains("?")) {                           // 수정 및 삭제 입력 시 입장
            String[] s1 = menu.split("\\?");        // 1차 분할 (메뉴 기능 & id)
            String[] s2 = s1[1].split("=");         // 2차 분할 (id & id 번호)
            this.pasteId = Integer.parseInt(s2[1]);      // String형 숫자를 정수로 변환
            divideWord = checkMenu(s1[0]);
        } else divideWord = checkMenu(menu);            // 수정 및 삭제 외 입장
        return divideWord;
    }           // 메뉴 문자 나누기
}
