@echo off
echo ============================================
echo Git Manual Installation Guide
echo ============================================
echo.
echo If the automatic installer doesn't work, follow these steps:
echo.
echo 1. Open your web browser
echo 2. Go to: https://git-scm.com/download/win
echo 3. Download will start automatically
echo 4. Run the installer
echo 5. Click "Next" through all options (defaults are fine)
echo 6. Restart Eclipse after installation
echo.
echo ============================================
echo Attempting automatic download...
echo ============================================
echo.

REM Try to download Git installer using PowerShell
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://github.com/git-for-windows/git/releases/download/v2.43.0.windows.1/Git-2.43.0-64-bit.exe' -OutFile '%TEMP%\GitInstaller.exe'}"

if exist "%TEMP%\GitInstaller.exe" (
    echo.
    echo Download complete! Starting installer...
    echo.
    start /wait %TEMP%\GitInstaller.exe
    del "%TEMP%\GitInstaller.exe"
    echo.
    echo Installation complete!
    echo Please RESTART Eclipse for Git to work.
) else (
    echo.
    echo Automatic download failed.
    echo Please visit https://git-scm.com/download/win manually.
)

echo.
pause
