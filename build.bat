@echo off
setlocal

cd /d "%~dp0"

where gradle.bat >nul 2>nul
if errorlevel 1 (
    echo Gradle was not found.
    echo Install Gradle 8.11.1 and add its bin directory to PATH, then try again.
    exit /b 1
)

echo Building Pinote APK...
call gradle.bat --no-daemon assembleDebug
if errorlevel 1 exit /b %errorlevel%

set "PINOTE_APK=%CD%\app\build\outputs\apk\debug\Pinote.apk"
if not exist "%PINOTE_APK%" (
    echo Build finished, but the APK was not found at:
    echo %PINOTE_APK%
    exit /b 1
)

echo.
echo APK created successfully:
echo %PINOTE_APK%
exit /b 0
