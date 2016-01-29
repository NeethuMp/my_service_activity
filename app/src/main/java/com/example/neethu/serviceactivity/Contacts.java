package com.example.neethu.serviceactivity;

/**
 * Created by neethu on 29/1/16.
 */
public class Contacts {
    public interface ACTION {
        public static String MAIN_ACTION = "com.example.neethu.serviceactivity.action.main";
        public static String PREV_ACTION = "com.example.neethu.serviceactivity.action.prev";
        public static String PLAY_ACTION = "com.example.neethu.serviceactivity.action.play";
        public static String NEXT_ACTION = "com.example.neethu.serviceactivity.action.play";
        public static String STARTFOREGROUND_ACTION = "com.example.neethu.serviceactivity.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.example.neethu.serviceactivity.action.stopforeground";

    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
