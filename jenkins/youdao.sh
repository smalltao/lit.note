#!/usr/bin/env bash
dateStr=`date +"%Y%m%d"`
trunkURL="com.http://localhost:8080/trunk/webapp/reventondc_youdao"
packageURL="com.http://localhost:8080/trunk/webapp_package/reventondc_youdao"
tagName="youdao.${dateStr}"
tagURL="com.http://localhost:8080/tags/webapp/reventondc"
tagCount=`svn list "$tagURL" --username "$username" --password "$password" --non-interactive | grep "${tagName}" | wc -l`
tagVersion=`echo $(( $tagCount+97 )) | awk '{printf("%c", $1)}'`
finalTagURL="${tagURL}/${tagName}.${tag_type}.${tagVersion}"
echo "generating tag $finalTagURL"
echo "Build the maven ..."
trunkDir=/search/odin/resin/reventondc_youdao/trunk
packageDir=/search/odin/resin/reventondc_youdao/web_packages
if [ -d $trunkDir ]; then
	cd ${trunkDir}
    svn up --username "$username" --password "$password" --non-interactive --force
else
	mkdir -p ${trunkDir}
    cd ${trunkDir}
    svn co "${trunkURL}" . --username "$username" --password "$password" --non-interactive
fi
if [ -d $packageDir ]; then
	cd ${packageDir}
    svn up --username "$username" --password "$password" --non-interactive --force
else
	mkdir -p ${packageDir}
    cd ${packageDir}
    svn co "${packageURL}" . --username "$username" --password "$password" --non-interactive
fi
cd ${trunkDir}
mvn clean package -P online
cp -rf target/reventon/* ${packageDir}
cd ${packageDir}
addFiles=`svn st | grep -r "^\?" | awk '{print $2}' | xargs`
if [ -n "$addFiles" ]; then
	svn add $addFiles --depth infinity --auto-props
fi
svn st
mFiles=`svn st | awk '{print $2}' | xargs`
if [ -n "$mFiles" ]; then
	echo "commit $mFiles"
	svn commit ${mFiles} -m "Submit by ${BUILD_NUMBER}" --username "$username" --password "$password" --non-interactive
fi
svn cp "${packageURL}" "${finalTagURL}" -m "Create tag ${tagName}.${tag_type}.${tagVersion} by ${username}" --username "$username" --password "$password" --non-interactive
