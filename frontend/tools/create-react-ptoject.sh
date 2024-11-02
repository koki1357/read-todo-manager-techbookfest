#!/bin/bash

# プロジェクト名の設定
PROJECT_NAME="frontend"

# Reactプロジェクトの作成
#npx create-react-app $PROJECT_NAME --template typescript

# 移動
cd $PROJECT_NAME

# 必要なディレクトリを作成
mkdir -p public/images public/fonts
mkdir -p src/components/atoms
mkdir -p src/components/molecules
mkdir -p src/components/organisms
mkdir -p src/components/templates
mkdir -p src/components/pages/Home
mkdir -p src/components/pages/About
mkdir -p src/common
mkdir -p src/services/api
mkdir -p src/store/actions src/store/reducers src/store/types
mkdir -p src/styles
mkdir -p src/assets/images src/assets/fonts
mkdir -p src/routes
mkdir -p src/tests/components src/tests/pages
mkdir -p src/useCases/hooks
mkdir -p src/types

# 必要なファイルを作成
touch public/index.html public/favicon.ico
touch src/components/atoms/Button.tsx src/components/atoms/Input.tsx
touch src/components/molecules/TodoItem.tsx
touch src/components/organisms/TodoList.tsx
touch src/components/pages/Home/Home.tsx
touch src/components/pages/About/About.tsx
touch src/common/constants.ts
touch src/services/api/todoService.ts
touch src/store/store.ts
touch src/styles/App.css
touch src/routes/AppRoutes.tsx
touch src/tests/components/TodoItem.test.tsx
touch src/tests/pages/Home.test.tsx
touch src/useCases/hooks/hooks.ts
touch src/App.tsx src/index.tsx src/react-app-env.d.ts
#touch tsconfig.json webpack.config.js .eslintrc.js .prettierrc
touch webpack.config.js .eslintrc.js .prettierrc

echo "プロジェクト構築完了"