package org.tracer.agentinjector.ui;


import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@State(
        name = "SqlTracerSettings", // 组件名称，必须唯一
        storages = @Storage(StoragePathMacros.WORKSPACE_FILE) // 存储到 .idea/workspace.xml
)
@Service(Service.Level.PROJECT)
public final class SqlPluginStore implements PersistentStateComponent<SqlPluginState.State> {
    private final SqlPluginState sqlPluginState = new SqlPluginState();

    public static SqlPluginStore getInstance(Project project) {
        return project.getService(SqlPluginStore.class);
    }

    public boolean isFeatureEnabled() {
        return sqlPluginState.outputToConsole;
    }

    public void setFeatureEnabled(boolean enabled) {
        sqlPluginState.outputToConsole = enabled;
    }

    @Override
    public @Nullable SqlPluginState.State getState() {
        return sqlPluginState.getState();
    }

    @Override
    public void loadState(@NotNull SqlPluginState.State state) {
        sqlPluginState.loadState(state);
    }
}
