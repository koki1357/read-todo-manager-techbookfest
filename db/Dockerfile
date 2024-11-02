FROM mysql:8.0

# ポートの設定
EXPOSE 3306

# MySQL設定ファイルをコピー
COPY ./my.cnf /etc/mysql/conf.d/my.cnf

CMD ["mysqld"]