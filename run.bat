@echo off
chcp 65001 > nul
cd /d "%~dp0"
javac -encoding UTF-8 -d out -sourcepath src src\com\example\coursework\Main.java || exit /b 1
start "" javaw -cp out com.example.coursework.Main
