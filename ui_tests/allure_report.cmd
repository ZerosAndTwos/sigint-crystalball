@echo off
for /f "delims=" %%a in ('wmic OS Get localdatetime  ^| find "."') do set dt=%%a

set dd=%dt:~6,2%
set mm=%dt:~4,2%
set yyyy=%dt:~0,4%

set HRS=%dt:~8,2%
set MIN=%dt:~10,2%

set report_folder=%yyyy%_%mm%_%dd%-%HRS%-%MIN%

allure generate target/allure-results -o reports/%report_folder%
pause