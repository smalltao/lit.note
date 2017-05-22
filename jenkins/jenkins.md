# 项目自动化构建 test1
1. 项目名称 ReventonYoudao Tag
2. 项目描述 创建数据中心之有道的tag

# 参数化构建过程 
1. String Parameter
    
        名字 username
        默认值 
        描述 svn 用户名
    
2. Password Parameter

        Name password
        Default Value
        Description svn 密码

3. Choice 
            
        Name tag_type
        Choices req jin zhong
        Description req -> 例行 jin -> 紧急上线 zhong -> 重大上线

# 源码管理
        
       none
       
# 构建环境
        
        Mark passwords (and enable global passwords)
        Password Parameters, or any other type of build parameters selected for masking in Hudson's/Jenkins' main configuration screen (Manage Hudson > Configure System), will be automatically masked.
    
# 构建
    
     dateStr=`date + "%Y%m%d"`
     trunkURL=""
     packageURL=""
     tagName="youdao.${dateStr}"
     tagURL=""
     tagCount=`svn list "$tagURL" --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive | grep "${tagName}" | wc -l`
     tagVersion=`echo $(( $tagCount+97 )) | awk '{printf("%c", $1)}'`
     finalTagURL="${tagURL}/${tagName}.${tag_type}.${tagVersion}"
     echo "generating tag $finalTagURL"
     echo "Build the maven ..."
     trunkDir=/search/odin/resin/reventondc_youdao/trunk
     packageDir=/search/odin/resin/reventondc_youdao/web_packages
     if [ -d $trunkDir ]; then
     	cd ${trunkDir}
         svn up --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive --force
     else
     	mkdir -p ${trunkDir}
         cd ${trunkDir}
         svn co "${trunkURL}" . --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive
     fi
     if [ -d $packageDir ]; then
     	cd ${packageDir}
         svn up --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive --force
     else
     	mkdir -p ${packageDir}
         cd ${packageDir}
         svn co "${packageURL}" . --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive
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
     	svn commit ${mFiles} -m "Submit by ${BUILD_NUMBER}" --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive
     fi
     svn cp "${packageURL}" "${finalTagURL}" -m "Create tag ${tagName}.${tag_type}.${tagVersion} by $SVN_USERNAME" --username $SVN_USERNAME --password $SVN_PASSWORD --non-interactive 


    
    

       
       
        

    

