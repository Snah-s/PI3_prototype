#!/bin/bash

echo commit message:
read -r commit_msg

git add .
git commit -m "$commit_msg"
git push origin main