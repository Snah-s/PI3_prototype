#!/bin/bash

echo "Commit message:"
read -r commit_msg

# Abre un editor de texto para editar el cuerpo del mensaje del commit
temp_file=$(mktemp)
echo -e "$commit_msg\n" > "$temp_file"
"${EDITOR:-nano}" "$temp_file"
commit_msg=$(cat "$temp_file")

# Realiza el commit con el mensaje editado
git add .
git commit -m "$commit_msg"
git push origin main
