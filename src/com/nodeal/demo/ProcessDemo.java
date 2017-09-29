package com.nodeal.demo;

import java.io.IOException;

public class ProcessDemo {
    public static void main(String... args) throws IOException {
        ProcessHandle processHandle = ProcessHandle.current();
        ProcessHandle.Info info = processHandle.info();

        System.out.printf("Current PID: %d\n", processHandle.pid());
        // Warning generated point. Optional.get() should check Optional.isPresent(), but our process is present.
        System.out.printf("Command who called me: %s\n", info.command().get());

        ProcessHandle.allProcesses().forEach(handle -> {
            if (handle.info().command().isPresent())
                System.out.printf("PID: %5d, Command: %s\n", handle.pid(), handle.info().command().get());
            else
                System.out.printf("PID: %5d (Command not present)\n", handle.pid());
        });

        try {
            processHandle.destroy();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("Yes, we cannot destroy self.");
        }
    }
}
