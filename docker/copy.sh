#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20231130.sql ./mysql/db
cp ../sql/ry_config_20231204.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../barbershop-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy barbershop-gateway "
cp ../barbershop-gateway/target/barbershop-gateway.jar ./ruoyi/gateway/jar

echo "begin copy barbershop-auth "
cp ../barbershop-auth/target/barbershop-auth.jar ./ruoyi/auth/jar

echo "begin copy barbershop-visual "
cp ../barbershop-visual/barbershop-monitor/target/barbershop-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy barbershop-modules-system "
cp ../barbershop-modules/barbershop-system/target/barbershop-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy barbershop-modules-file "
cp ../barbershop-modules/barbershop-file/target/barbershop-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy barbershop-modules-job "
cp ../barbershop-modules/barbershop-job/target/barbershop-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy barbershop-modules-gen "
cp ../barbershop-modules/barbershop-gen/target/barbershop-modules-gen.jar ./ruoyi/modules/gen/jar

