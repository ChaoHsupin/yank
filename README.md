
## 安装
1. 解压文件包
2. 将文件包根目录配置到环境变量
## 快速上手
### 添加密钥
1. 命令: yank add 
2. 会提示输入：标题、用户名、密码、备注
- 首次使用该密钥可能会要求设置初始PIN码（用作后续查找密钥）
### 查找密钥
1. 命令: yank show
- 会提示输入PIN码
- 以模糊匹配方式按标题查找，会将所有匹配结果完整展示出来
2. 快捷命令： (yank show .) 显示所有密钥
3. 快捷匹配： (yank show [字符]) 快捷模糊匹配   
### 安全
1. 此命令小工具未接入互联网，大家可根据自身使用情况修改源码以接入互联网保存密钥信息
2. PIN码及文本密钥加密后持久化在安装目录，加密方式：防君子不能防小人。大家可根据要求替换安全策略
### 源码打包
1. 源码打包成jar文件
2. 并在同级放入脚本（Windows：bat文件，Linux：sh文件）