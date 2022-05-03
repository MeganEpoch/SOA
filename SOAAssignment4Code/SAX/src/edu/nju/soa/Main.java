package edu.nju.soa;

public class Main {
    public static void main(String[] args) {
        Main m=new Main();
        m.parse("XML3.xml");

    }

    public void parse(String filename){
        ScoreParseHandler scoreParseHandler =new ScoreParseHandler();
        scoreParseHandler.parseDocument(filename);
    }
}
