# Pinote
<p align="center">
<img width="300" height="660" alt="6a60e000835aea86ebb05e4248943243" src="https://github.com/user-attachments/assets/16a63b74-6260-431e-a2b8-24b984533a40" />
</p>
Pinote 是一个轻量、离线优先的 Android 常驻提醒工具。将一条文本或 Todo 清单固定到系统通知栏，让重要事项无需打开 App 也能随时看到。

Pinote is a lightweight, offline-first Android reminder that pins text or a Todo list to the notification shade.

## 功能特性

- 文本与 Todo 两种内容类型，切换时保留两侧尚未提交的内容
- Todo 自动创建下一输入行，支持勾选和双向滑动删除
- 输入内容后即可 Pin
- 常驻系统通知，展开后支持取消固定和删除
- 通知栏不显示标题，内容最多显示三行
- 清空内容后可在 2 秒内撤销
- 本地持久化，设备重启后恢复已固定提醒
- 自动跟随系统显示中文或英文
- 无账号、无云同步、无网络权限

## 系统要求

- Android 8.0（API 26）或更高版本
- Android 13 及以上需要授予通知权限

## 安装

从 [Releases](https://github.com/cantonadong/Pinote/releases) 下载最新的 `Pinote.apk` 并安装。

> Release 中提供的是调试签名测试包，适合功能验证。正式分发时建议配置独立的发布签名。

## 构建

项目使用 Android Gradle Plugin 8.7.3、Java 17，目标 SDK 为 36。

```bash
gradle assembleDebug
```

构建产物位于：

```text
app/build/outputs/apk/debug/Pinote.apk
```

## 权限说明

- `POST_NOTIFICATIONS`：显示固定提醒
- `FOREGROUND_SERVICE`：维持常驻通知
- `RECEIVE_BOOT_COMPLETED`：设备重启后恢复提醒

Pinote 不申请网络权限，所有提醒数据均保存在设备本地。

## 项目结构

```text
app/       Android 应用源码
docs/      产品与开发设计文档
preview/   独立 UI 交互预览
design.png 视觉与交互设计稿
icon.png   应用图标源文件
```

## License

尚未指定开源许可证。未经许可，不代表可以复制、修改或再分发本项目代码。

