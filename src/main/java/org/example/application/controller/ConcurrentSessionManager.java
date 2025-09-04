package org.example.application.controller;

import org.example.application.ui.ConsoleUI;
import org.example.domain.service.IBankService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSessionManager {
    private final ExecutorService executorService;
    private final IBankService bankService;
    private final ConsoleUI consoleUI;

    public ConcurrentSessionManager(int nThreads, IBankService bankService, ConsoleUI consoleUI) {
        this.executorService = Executors.newFixedThreadPool(nThreads);
        this.bankService = bankService;
        this.consoleUI = consoleUI;
    }

    public synchronized void startSessions(int nSessions) {
        for (int i = 0; i < nSessions; i++) {
            ATMController controller = new ATMController(bankService, consoleUI);
            executorService.submit(new SessionTask(controller));
        }
    }

    public synchronized void shutdown() {
        executorService.shutdown();
    }
}
