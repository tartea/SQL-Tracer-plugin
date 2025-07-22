package org.tracer.agentinjector.ui;


import org.jetbrains.annotations.NotNull;

public class SqlPluginState {
    public boolean outputToConsole = false;
    public boolean enableUseAgent = false;
    public boolean fileOverlay = false;

    // 可序列化的必须方法
    public static class State {
        public boolean outputToConsole = false;
        public boolean enableUseAgent = false;
        public boolean fileOverlay = false;
    }

    public State getState() {
        State state = new State();
        state.outputToConsole = outputToConsole;
        state.enableUseAgent = enableUseAgent;
        state.fileOverlay = fileOverlay;
        return state;
    }

    public void loadState(@NotNull State state) {
        this.outputToConsole = state.outputToConsole;
        this.enableUseAgent = state.enableUseAgent;
        this.fileOverlay = state.fileOverlay;
    }
}