package com.lockarhythm.cmdline;

import java.util.Scanner;
import java.util.Arrays;

import com.lockarhythm.responders.QueryRespondable;
import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.echo.EchoResponder;
import com.lockarhythm.responders.exit.ExitResponder;

public class Duke {
    static String logo =   " \t____        _        \n"
                           + "\t|  _ \\ _   _| | _____ \n"
                           + "\t| | | | | | | |/ / _ \\\n"
                           + "\t| |_| | |_| |   <  __/\n"
                           + "\t|____/ \\__,_|_|\\_\\___|\n";

    static QueryRespondable[] responders = {
        new ExitResponder(),
        new EchoResponder(),
    };

    private static void print(String ...strings) {
        System.out.println("\t____________________________________________________________");
        for (String s : strings) {
            System.out.println("\t"+s);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static Response getResponse(String query) {
        Response res = null;
        for (QueryRespondable responder : responders) {
            res = responder.respondTo(query);
            if (res != null) {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        print("Hello I'm\n" + logo, "What can I do for you?");

        line = in.nextLine();
        Response res = getResponse(line);
        while(!res.shouldExit()) {
            print(res.getText());
            line = in.nextLine();
            res = getResponse(line);
        }

        print(res.getText());
    }
}
