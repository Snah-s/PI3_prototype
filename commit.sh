#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
RESET='\033[0m'

# Emoji list
emojis=(
    "🎨  - Improve structure / format of the code."
    "⚡️  - Improve performance."
    "🔥  - Remove code or files."
    "🐛  - Fix a bug."
    "🚑️  - Critical hotfix."
    "✨  - Introduce new features."
    "📝  - Add or update documentation."
    "🚀  - Deploy stuff."
    "💄  - Add or update the UI and style files."
    "🎉  - Begin a project."
    "✅  - Add, update, or pass tests."
    "🔒️  - Fix security or privacy issues."
    "🔐  - Add or update secrets."
    "🔖  - Release / Version tags."
    "🚨  - Fix compiler / linter warnings."
    "🚧  - Work in progress."
    "💚  - Fix CI Build."
    "⬇️  - Downgrade dependencies."
    "⬆️  - Upgrade dependencies."
    "📌  - Pin dependencies to specific versions."
    "👷  - Add or update CI build system."
    "📈  - Add or update analytics or track code."
    "♻️  - Refactor code."
    "➕  - Add a dependency."
    "➖  - Remove a dependency."
    "🔧  - Add or update configuration files."
    "🔨  - Add or update development scripts."
    "🌐  - Internationalization and localization."
    "✏️  - Fix typos."
    "💩  - Write bad code that needs to be improved."
    "⏪️  - Revert changes."
    "🔀  - Merge branches."
    "📦️  - Add or update compiled files or packages."
    "👽️  - Update code due to external API changes."
    "🚚  - Move or rename resources (e.g.: files, paths, routes)."
    "📄  - Add or update license."
    "💥  - Introduce breaking changes."
    "🍱  - Add or update assets."
    "♿️  - Improve accessibility."
    "💡  - Add or update comments in source code."
    "🍻  - Write code drunkenly."
    "💬  - Add or update text and literals."
    "🗃️  - Perform database related changes."
    "🔊  - Add or update logs."
    "🔇  - Remove logs."
    "👥  - Add or update contributor(s)."
    "🚸  - Improve user experience / usability."
    "🏗️  - Make architectural changes."
    "📱  - Work on responsive design."
    "🤡  - Mock things."
    "🥚  - Add or update an easter egg."
    "🙈  - Add or update a .gitignore file."
    "📸  - Add or update snapshots."
    "⚗️  - Perform experiments."
    "🔍️  - Improve SEO."
    "🏷️  - Add or update types."
    "🌱  - Add or update seed files."
    "🚩  - Add, update, or remove feature flags."
    "🥅  - Catch errors."
    "💫  - Add or update animations and transitions."
    "🗑️  - Deprecate code that needs to be cleaned up."
    "🛂  - Work on code related to authorization, roles and permissions."
    "🩹  - Simple fix for a non-critical issue."
    "🧐  - Data exploration/inspection."
    "⚰️  - Remove dead code."
    "🧪  - Add a failing test."
    "👔  - Add or update business logic."
    "🩺  - Add or update healthcheck."
    "🧱  - Infrastructure related changes."
    "🧑‍💻  - Improve developer experience."
    "💸  - Add sponsorships or money related infrastructure."
    "🧵  - Add or update code related to multithreading or concurrency."
    "🦺  - Add or update code related to validation."
)

# Make emojis array into a dialog compatible array
options=()
for ((i=0; i<${#emojis[@]}; i++)); do
    emoji=$(echo "${emojis[$i]}" | awk '{print $1}')
    description=$(echo "${emojis[$i]}" | cut -d '-' -f 2-)
    options+=("$emoji" "$description" "OFF")
done

select_emoji() {
    emoji=$(dialog --stdout --clear --title "Commit with emoji" \
            --radiolist "Choose a emoji:" 25 80 20 "${options[@]}")
    clear
    if [ -z "$emoji" ]; then
        echo -e "${RED}Canceled"
        exit 1
    fi
}

select_emoji

commit_message=$(dialog --stdout --inputbox "Commit message:" 8 40)
clear
if [ -z "$commit_message" ]; then
    echo -e "${RED}Canceled"
    exit 1
fi

commit_message="${emoji}  ${commit_message}"

temp_file=$(mktemp)
echo -e "$commit_message\n" > "$temp_file"
commit_message=$(dialog --stdout --editbox "$temp_file" 25 80)
clear

# Realiza el commit con el mensaje editado
git add .
git commit -m "$commit_message"
git push origin main

# Imprimir mensaje de confirmación
echo -e "${GREEN}Commit successfully completed: ${commit_message}${RESET}"