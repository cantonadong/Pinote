# Delivery Log

## 2026-08-19 — design.png 最终单条 Pinote 版本

状态：已生成测试 APK。

- 应用包名已改为 `com.cantonadong.pinote`，启动图标使用根目录 `icon.png`。
- 按 `design.png` 重构为单页编辑器：居中品牌栏、标题与字数、普通/Todo 分段、固定底部保存与删除操作。
- Todo 支持行内新增、勾选/取消、左右跟手滑动删除、2 秒撤销；空行不可勾选。
- 保存后持久化并固定到系统通知栏；通知支持系统折叠/展开样式，Todo 使用 `☐/☑`，删除内容同步移除通知。
- 未保存返回显示放弃确认；删除显示二次确认；保存显示成功反馈。
- 已在 LDPlayer 安装运行，验证普通文本保存、通知内容同步、进程重启后恢复。
- `assembleDebug` 与 `lintDebug` 通过，Lint 为 0 error。

## 2026-08-18 — 节点 0：需求梳理与 Preview

状态：待用户验证

已完成：

- 将 `need.md` 全量拆解为业务流程、页面/交互、通知规则、视觉规范、技术架构、开发依赖和验收清单。
- 创建零依赖交互 Preview，覆盖 normal、loading、empty、error、edge case。
- 用户明确产品不需要 Dark Mode；已从产品范围、预览控制和验收项移除。
- Preview 支持 Todo 切换、左右拖动删除、2 秒 Snackbar 和原位撤销。
- 已读取正确的 `design.png`，按稿校准蓝色图标意象、方形 Checkbox、系统圆角通知、红色删除层和深色 Snackbar。
- 用户反馈首版仍偏功能线框；已重做高保真浅色稿，替换占位字符图标，重建材质、字号、间距、圆角、阴影、Checkbox、删除图标与 Snackbar 视觉层级。

环境信息：

- 工作目录初始仅有 `need.md` 与 `dev规范.txt`，不是 Git 仓库。
- Node.js 位于 `D:\Program\nodejs\node.exe`。
- Git 位于 `D:\Program\Git\mingw64\bin\git.exe`。
- Java、adb、Gradle 当前未进入 PATH；原生工程阶段需定位或配置 Android 工具链。
- 按用户要求，Android SDK 已迁移到 `D:\Android\sdk`，Gradle 8.11.1 安装到 `D:\Android\gradle\gradle-8.11.1`；用户级 `ANDROID_HOME`、`ANDROID_SDK_ROOT`、`GRADLE_HOME`、`JAVA_HOME` 与 PATH 已配置到 D 盘工具路径。

约束/决策：

- 按 UI Preview First Rule，本节点不写入真实持久化、通知、权限或其他业务逻辑。
- 未收到独立视觉稿文件，当前视觉依据 `need.md` 的文字规范实现；像素级 1:1 仍需用户提供/确认视觉参照。

待处理：

- 用户确认 Preview 的布局、颜色、间距、字号、滑动手感与 Snackbar。
- 确认后创建 Android 原生工程，并逐模块生成测试 APK 交付。

## 2026-08-18 — 节点 1：Android 可安装版本

状态：待用户真机验证

已完成：

- 创建 Android 原生 Java 工程，产品名与 APK 名统一为 `Pinote`。
- 实现单行标题、普通文本、Todo、即时本地保存、Todo 状态切换。
- 实现双向跟手滑动删除、红色删除层、2 秒 Snackbar 与原位撤销。
- 实现前台常驻通知、内容实时同步、全空取消、开机恢复和 Android 13+ 通知权限请求。
- 仅实现 Light Mode；按 `design2.png` 的 16dp/12dp/48dp/24dp 规格和指定色值实现。
- `assembleDebug` 构建通过，APK 元数据校验通过：minSdk 26、targetSdk 36。
- 根据用户澄清重构内容模型：普通内容与 Todo List 互斥；Todo List 内允许多条 Todo，每项独立切换、滑删与撤销。
- 主页面重新按 `design2.png` 组织为 56dp 顶栏、16dp 页面边距、8dp 白色内容容器、48dp Todo 行、24dp Checkbox 和指定字号/色值。

待验证：

- 不同 Android 版本与厂商系统对常驻通知、后台保活的实际策略。
- 真机滑动阈值、文字换行、输入法和 Snackbar 位置的视觉手感。

## 2026-08-18 — 节点 2：design3 多提醒重构

状态：待用户真机验证

- 以 `design3.png` 替换此前视觉基准，实现提醒列表页与新建/编辑页。
- 支持多提醒、普通内容/Todo List 互斥、最多一个 Pin、Pin 内容常驻通知。
- 实现列表卡片、FAB、分段 Tab、500 字普通内容、最多 50 条 Todo、底部提醒/置顶/删除操作。
- 双向滑删、2 秒撤销、通知点击打开 App 均保留。
- 已在 LDPlayer 真机渲染环境安装运行并截图检查，发现并替换全部主要 Unicode 占位操作图标为 Vector Drawable。
- 第三版 `Pinote.apk` 构建及模拟器覆盖安装通过。
- 完整回归补齐：标题/内容动态字数限制、搜索、列表菜单、正式 Vector Checkbox/App Icon、提醒状态反馈。
- 修复滑动容器触摸分发：改用 `onInterceptTouchEvent/onTouchEvent`，避免 ScrollView 抢占或滑动误入编辑；左右方向均通过模拟器验证。
- 修复 Snackbar 被 FAB 遮挡，撤销按钮可点击；右滑删除后在 2 秒内撤销可恢复原位置和完整数据。
- 修复删除当前 Pin 后前台通知残留：服务使用 CLEAR action 先移除 foreground 再停止；活动通知列表验证为空，历史归档不影响。
- 验证 Todo 新增、完成/取消、通知 `☑/☐` 展开；验证第二提醒 Pin 后旧 Pin 自动取消且通知实时替换。
- 验证搜索可同时匹配标题、普通正文和 Todo；数据在进程停止与重新启动后恢复。
- Android Lint：0 errors；`assembleDebug` 成功；APK v2 签名验证通过；模拟器覆盖安装成功。
