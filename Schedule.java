Phase 1: Initialization and First Commit
1. git init
2. echo "hi how r u">new.txt
3. git add .
4. git commit -m "my new commit"
Phase 2: Branch Creation and Fast-Forward Merge
1. git checkout -b branch1
2. echo "today is wednesday">new2.txt
3. git add .
4. git commit -m "my another commit"
5. git switch master
6. git merge branch1
Phase 3: Remote Setup and Conflict Preparation
1. git clone <repository_url>
2. cd prgm22
3. git checkout -b branch1
4. echo "have a grat day">abc.txt
5. git add .
6. git commit -m "changes for conflict"
7. git switch main
8. echo "have a wonderful day">abc.txt
9. git add .
10. git commit -m "conflict"
Phase 4: Conflict Handling and Comparison
1. git merge branch1 (Result: CONFLICT)
2. git reset --merge
3. git push origin main
4. git switch branch1
5. git push origin branch1
6. git diff main
