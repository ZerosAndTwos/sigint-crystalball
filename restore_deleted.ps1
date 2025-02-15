$restoreFolder = "restored_files"
New-Item -ItemType Directory -Force -Path $restoreFolder | Out-Null

Write-Host "Searching for completely deleted files in git history..."

# Get all deleted files from git history
$deletedFiles = git log --diff-filter=D --summary | 
    Select-String -Pattern "delete mode \d+ (.*)" | 
    ForEach-Object { $_.Matches.Groups[1].Value } | 
    Sort-Object -Unique

foreach ($file in $deletedFiles) {
    # Find the last commit where the file existed
    $commitHash = git log --diff-filter=D --pretty=format:"%H" -n 1 -- $file
    
    if ($commitHash) {
        Write-Host "Attempting to restore: $file from commit $commitHash"
        try {
            # Create the directory structure
            $targetPath = Join-Path $restoreFolder $file
            $targetDir = Split-Path -Parent $targetPath
            if (!(Test-Path $targetDir)) {
                New-Item -ItemType Directory -Force -Path $targetDir | Out-Null
            }
            
            # Checkout the version before deletion
            git show "$commitHash^:$file" > $targetPath
            Write-Host "Successfully restored: $file" -ForegroundColor Green
        } catch {
            Write-Warning "Failed to restore $file : $($_.ToString())"
        }
    }
}

Write-Host "`nRestoration process complete. Check the '$restoreFolder' directory for restored files."