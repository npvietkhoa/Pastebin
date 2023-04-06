package com.pastebin;


public class JsonViewSchema {
    public static class postView{}

    public static class getView{}
    public static class fullView extends postView{};
}
