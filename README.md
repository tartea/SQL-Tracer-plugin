# 🧩 SQL Tracer – 在控制台打印并记录 SQL 日志的 IDEA 插件

> 在 Spring Boot 应用启动时注入 Java Agent，实时打印 SQL 到控制台，并将 SQL 日志写入本地文件，便于调试和分析数据库操作。

---

## 🔍 功能简介

- ✅ 在 Spring Boot 启动时自动注入 Java Agent；
- ✅ 实时拦截并打印所有 SQL（包括参数）；
- ✅ 将 SQL 写入本地日志文件；
- ✅ 支持查看 SQL 耗时；
- ✅ 不侵入代码，无需修改项目依赖；

---

## 🚀 安装方式

### 方式一：手动安装 `.zip` 插件包

1. 下载插件 `.zip` 文件
2. 打开 IntelliJ IDEA → `Settings (Preferences)` → `Plugins`
3. 点击右上角 `⚙️` → `Install Plugin from Disk...`
4. 选择下载的 `.zip` 文件并安装

---

## 🛠️ 使用方法

1. 创建或打开一个 Spring Boot 项目；
2. 配置好 Run Configuration（主类需带有 `@SpringBootApplication`）；
3. 正常运行或调试项目；
4. 控制台输出中即可看到拦截的 SQL；
5. SQL 日志会写入本地文件，默认路径为：当前项目根目录下的sql-trace.log
---

## ⚙️ 配置选项（可通过 ToolWindow 设置）

插件提供一个设置页面（位于下面 ToolWindow）：

| 配置项         | 说明                    |
|-------------|-----------------------|
| 开启sql tracer | 开关控制插件是否执行            |
| 是否启用 SQL 打印 | 开关控制是否开启 SQL 是否打印到控制台 |

---

## 🧠 技术实现说明

本插件基于如下核心技术实现：

- 💡 **Java Agent**：通过字节码增强技术（ javassist ）拦截 JDBC 数据库操作；
- 🧱 **IntelliJ IDEA 插件机制**：利用 `JavaProgramPatcher` 注入 `-javaagent`；
- 📁 **日志持久化**：使用标准 Java IO 写入日志文件；
- 📈 **性能考虑**：仅在开发阶段启用，不影响生产环境；

---

## 📌 常见问题

### Q: 插件会影响程序性能吗？
A: 插件默认只在开发模式下生效，建议在调试时开启。生产环境中不建议使用。

### Q: 如何关闭 SQL 打印？
A: 在插件的 ToolWindow 中关闭“控制台打印日志”开关即可。

### Q: 日志文件太大怎么办？
A: 重新启动项目，每一次启动项目会将原来的日志文件按照时间重命名。

---

## 📄 许可证

MIT License

---

## 🧪 兼容性支持

| IDE | 版本要求    |
|-----|---------|
| IntelliJ IDEA | 2023.1+ |


