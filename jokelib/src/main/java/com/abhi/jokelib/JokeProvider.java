package com.abhi.jokelib;

import java.util.Random;

public class JokeProvider {

    private static String jokes[] = new String[]{
            "Husband brings the child home from kindergarten and asks his wife, He has been crying the whole way home. Is he sick or something? No, replies the wife, he was just trying to tell you he is not our Frankie.\n" +
                    "\n"
            , "Two police officers crash their car into a tree. After a moment of silence, one of them says, Wow, this is the fastest we ever got to the accident site." +
            "\n" ,
            "When I see lovers' names carved in a tree, I don't think it's sweet. I just think it's surprising how many people bring a knife on a date.\n" +
                    "\n" ,
            "My wife and I had very happy twenty years. After that we met.\n" +
                    "\n"

    };
    private JokeProvider(){

    }

    public static String getJoke(){
        int i = new Random().nextInt(3);
        return jokes[i];
    }
}

