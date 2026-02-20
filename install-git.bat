@echo off
echo ============================================
echo Git Installation Script for Windows
echo ============================================
echo.

REM Check if Git is already installed
git --version >nul 2>&1
if %errorlevel% == 0 (
    echo Git is already installed!
    git --version
    echo.
    echo If you want to update Git, continue anyway.
    pause
)

echo.
echo Installing Git using Windows Package Manager (winget)...
echo This may take a few minutes...
echo.

REM Install Git using winget
winget install --id Git.Git -e --source winget

echo.
echo ============================================
echo Installation Complete!
echo ============================================
echo.
echo Please CLOSE and REOPEN your terminal/Eclipse for Git to work properly.
echo.
echo To verify installation, run: git --version
echo.
pause
