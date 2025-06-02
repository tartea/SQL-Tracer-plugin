package org.tracer.agentinjector.ui;


import org.jetbrains.annotations.NotNull;

public class SqlPluginState {
    public boolean outputToConsole = false;
    public boolean enableUseAgent = false;

    // 可序列化的必须方法
    public static class State {
        public boolean outputToConsole = false;
        public boolean enableUseAgent = false;
    }

    public State getState() {
        State state = new State();
        state.outputToConsole = outputToConsole;
        state.enableUseAgent = enableUseAgent;
        return state;
    }

    public void loadState(@NotNull State state) {
        this.outputToConsole = state.outputToConsole;
        this.enableUseAgent = state.enableUseAgent;
    }
}