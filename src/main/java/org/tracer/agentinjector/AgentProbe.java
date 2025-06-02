package org.tracer.agentinjector;

import cn.hutool.core.util.StrUtil;
import com.intellij.execution.Executor;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.JavaProgramPatcher;
import com.intellij.psi.PsiClass;
import org.tracer.agentinjector.ui.SqlPluginStore;
import org.tracer.agentinjector.ui.SqlSettingsComponent;
import org.tracer.agentinjector.util.PluginUtil;

import java.util.Objects;

public class AgentProbe extends JavaProgramPatcher {

    @Override
    public void patchJavaParameters(Executor executor, RunProfile configuration, JavaParameters javaParameters) {

        RunConfiguration runConfiguration = (RunConfiguration) configuration;


        boolean load = false;
        if (runConfiguration instanceof ApplicationConfiguration) {

            // 决定是否加载
            boolean enableUseAgent = SqlPluginStore.getInstance(runConfiguration.getProject()).isEnableUseAgent();
            if (enableUseAgent) {
                ConfigurationType type = runConfiguration.getType();
                if (type instanceof ApplicationConfigurationType) {
                    // 判断文件中是否有指定内容
                    PsiClass mainClass = ((ApplicationConfiguration) runConfiguration).getMainClass();
                    if (Objects.nonNull(mainClass)) {

                        if (mainClass.hasAnnotation("org.springframework.boot.autoconfigure.SpringBootApplication")) {
                            String agentCoreJarPath = PluginUtil.getAgentCoreJarPath();

                            if (StrUtil.isBlank(agentCoreJarPath)) {
                                return;
                            }
                            // 拼接控制台打印
                            boolean currentState = SqlPluginStore.getInstance(runConfiguration.getProject()).isFeatureEnabled();
                            agentCoreJarPath = agentCoreJarPath + "=outputToConsole=" + currentState;
                            ParametersList vmParametersList = javaParameters.getVMParametersList();
                            vmParametersList.addParametersString("-javaagent:" + agentCoreJarPath);
                            load = true;
                        }
                    }
                }
            }
        }
        SqlSettingsComponent.updateGlobalStatusText(load ? "sql tracer加载成功" : "sql tracer未加载");
    }

}
