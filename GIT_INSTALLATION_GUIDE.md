# Git Installation Instructions

## If your friend doesn't have Git installed:

### Option 1: Automatic Installation (Recommended - Windows 10/11)
1. Download the `install-git.bat` file
2. Right-click on it and select "Run as administrator"
3. Wait for installation to complete
4. **Close and restart Eclipse**
5. Verify by typing in terminal: `git --version`

### Option 2: Alternative Installer (If Option 1 fails)
1. Download the `install-git-manual.bat` file
2. Right-click on it and select "Run as administrator"
3. Follow the installation wizard
4. **Close and restart Eclipse**

### Option 3: Manual Installation (Most reliable)
1. Go to: https://git-scm.com/download/win
2. Download will start automatically
3. Run the installer
4. Click "Next" through all options (defaults are fine)
5. **Close and restart Eclipse**

### Option 4: One-Line Command (Windows 10/11 with winget)
Open Command Prompt or PowerShell **as Administrator** and run:
```cmd
winget install --id Git.Git -e --source winget
```

---

## After Git is installed:

Your friend can then clone the repository:

```cmd
git clone https://github.com/Fedo682/OrangHRM-Automation.git
```

Then import into Eclipse:
1. File → Import → Existing Maven Projects
2. Browse to the cloned folder
3. Click Finish

---

## Alternative: Send Project as ZIP (Without Git)

If Git installation is problematic, you can:

1. Download the project as ZIP from GitHub
2. Send the ZIP file directly to your friend
3. Your friend extracts it and imports into Eclipse as Maven project

**GitHub ZIP Download:**
https://github.com/Fedo682/OrangHRM-Automation/archive/refs/heads/main.zip
