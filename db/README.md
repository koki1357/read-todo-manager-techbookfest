# DB起動方法
cd db
docker compose up -d

# DB停止方法
docker compose down -d

# データの削除方法
rm -rf db/db-volume
