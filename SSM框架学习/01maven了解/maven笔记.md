###安装与下载maven

	修改本地仓库
	<localRepository></localRepository>
	
	阿里云镜像1
		<mirror>  
		  <id>alimaven</id>  
		  <name>aliyun maven</name>  
		  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
		  <mirrorOf>central</mirrorOf>          
		</mirror>
		
	视频镜像
		<mirror>  
		  <id>nexus-aliyun</id>  
		  <name>Nexus aliyun</name>  
		  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
		  <mirrorOf>*</mirrorOf>          
		</mirror>
		
	这是啥？
		//-Dfile.encoding=UTF-8(Tomcat配置时出现的)有错误

		
