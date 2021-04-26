# Docker概述

## 什么是容器

容器就是在隔离的环境运行的一个进程，如果进程停止，容器就会推出。隔离的环境拥有自己的系统文件、IP地址和主机名等。

## 什么是Docker

Docker是一个开源项目，诞生于2013年初，最初是dotCloud公司内部的一个业余项目。

它基于Google公司推出的Go语言实现。项目后来加入了Linux基金会，遵从了Apache2.0协议，项目代码在GitHub上进行维护。

Docker自开源后受到广泛的关注和讨论，以至于dotCloud公司后来都改名为DockerInc。

Redhat已经在其RHEL6.5中集中支持Docker；Google也在其PaaS产品中广泛应用。

Docker项目的目标是实现轻量级的操作系统虚拟化解决方案。

Docker的基础是Linux容器（LXC）等技术。在LXC的基础上Docker进行了进一步的封装，让用户不需要去关心容器的管理，使得操作更为简便。用户操作Docker的容器就像操作一个快速轻量级的虚拟机一样简单。

Docker可以让开发者打包他们的应用以及依赖包到一个轻量级、可移植的容器中，然后发布到任何流行的Linux机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口（类似iPhone的app）,更重要的是容器性能开销极低。

## 为什么要使用Docker

### Docker容器虚拟化的好处

在云时代，开发者创建的应用必须要能很方便地在网络上传播，也就是说应用必须脱离底层物理硬件的限制；同时必须满足“任何时间任何地点”可获取可使用的特点。因此，开发者们需要一种新型的创建分布式应用程序的方式，快速分发部署，而这正是Docker所能够提供的最大优势。

Docker提供了一种更为聪明的方式，通过容器来打包应用、解耦应用和运行平台。这意味着迁移的时候，只需要在新的服务器上启动需要的容器就可以了，无论新旧服务器是否是同一类别的平台。这无疑帮助我们节约了大量的宝贵时间，并降低部署过程出现问题的风险。

### Docker在开发和运维中的优势

对于开发和运维人员来说，最梦寐以求的效果可能就是一次创建和配置，之后可以在任意地方、任意时间让应用正常运行，而Docker恰恰可以实现这一目标。具体来说，在开发和运维过程中，Docker具有以下几个方面的优势：

1. 更快的交付和部署：

   使用Docker，开发人员可以使用镜像来快速构建一套标准的开发环境；开发完之后，测试和运维人员可以直接使用完全相同的环境来部署代码。只要是开发测试过的代码，就可以确保在生产环境无缝运行。Docker可以快速创建和删除容器，实现快速迭代，节约开发、测试及部署的时间。

2. 更高效的利用资源：

   运行Docker容器不需要额外的虚拟化管理程序的支持，Docker是内核级的虚拟化，可以实现更高的性能，同时对资源的额外需求很低，与传统的虚拟机方式相比，Docker的性能要提高1~2个数量级。

3. 更轻松的迁移和扩展：

   Docker容器几乎可以在任意的平台上运行，包括物理机、虚拟机、公有云、私有云、个人电脑等等，同时支持主流的操作系统发行版本。这种兼容性能让用户可以在不同的平台之间轻松的迁移应用。

4. 更轻松的管理和更新：

   使用Dockerfile，只需要小小的配置修改，就可以替代以往大量的更新工作。所有的修改都以增量的方式被分发和更新，从而实现自动化并且高效的容器管理。

10. 解决异构环境

## Docker容器与传统虚拟化的区别

> 1. Docker以及其他容器技术，都属于操作系统虚拟化范畴，操作系统细腻化最大的特点就是不需要额外的supervisor支持。Docker虚拟化方式之所以有众多优势，跟操作系统虚拟化技术自身的设计和实现分不开。
> 2. 传统方式是在硬件层面实现虚拟化，需要有额外的虚拟机管理应用和虚拟机操作系统层。Docker容器时在操作系统层面实现虚拟化，直接复用本地主机的操作系统，因此更加轻量级。


### 区别
![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/kuangstudy5f991bce-d04c-475c-aa76-a092122181ac.jpg)
传统的虚拟机技术缺点：

1. 资源占用多
2. 冗余步骤多
3. 启动缓慢

Docker与虚拟机技术的不同：

1. 传统虚拟机，虚拟出一套硬件，运行一个完整的操作系统，然后在这个系统上安装和运行软件
2. 容器内的应用直接运行在宿主机的内容，容器是没有自己的内核的，也没有虚拟我们的硬件，所以就轻便了
3. 每个容器间是互相隔离，每个容器内都有一个属于自己的文件系统，互不影响

**容器优势：启动快，性能高，损耗少，轻量级**

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/kuangstudy49fcddfa-a36e-49cf-bce1-d29758862a60.jpg)

### Docker的优势

作为一种轻量级的虚拟化方式，Docker在运行应用上跟传统的虚拟机的方式相比具有如下显著优势：

1. Docker容器启动很快，启动和停止可以实现秒级，相比传统的虚拟机方式（分钟级）要快速很多。
2. Docker容器对系统资源需求很少，一台主机上可以同时运行数千个Docker容器。
3. Docker通过类似git设计理念的操作来方便用户获取、分发和更新应用镜像，存储复用，增量更新。
4. Docker通过Dockerfile支持灵活的自动化创建和部署机制，可以提高工作效率，并标准化流程。

## Docker的核心概念

### 镜像（image）

就相当于是一个root文件系统。比如官方镜像ubuntu:16.04就包含了完整的一套Ubuntu16.04最小系统的root文件系统。

docker镜像就好比是一个目标，可以通过这个目标来创建容器服务，tomcat镜像==>run==>容器（提供服务），通过这个镜像可以创建多个容器（最终服务运行或者项目运行就是在容器中的）。

### 容器（container）

镜像（Image）和容器（Container）的关系，就像是面向对象程序设计中的**类和实例一样**，镜像是静态的定义，容器是镜像运行时的实体。容器可以被创建、启动、停止、删除、暂停等。

### 仓库（repository）

用来保存镜像的仓库。当我们构建好自己的镜像之后，需要存放在仓库中，当我们需要启动一个镜像时，可以在仓库中下载下来。

仓库就是存放镜像的地方！
仓库分为公有仓库和私有仓库。(类似git)
Docker Hub是国外的，阿里云…都有容器服务器(配置镜像加速!)

## centos7安装Docker

```shell
# 卸载旧版本docker
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
# 安装所需要的工具包
yum install -y yum-utils

# 设置镜像仓库
yum-config-manager \
    --add-repo \
    https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    
# 更新yum软件包索引
yum makecache fast

# 安装docker相关的 docker-ce 社区版 而ee是企业版
yum install docker-ce docker-ce-cli containerd.io # 这里使用社区版即可

# 启动docker
systemctl start docker
# 使用docker version查看是否按照成功
docker version
# 测试
docker run hello-world
```

```shell
# 卸载docker
# 卸载依赖
yum remove docker-ce docker-ce-cli containerd.io
# 删除资源
rm -rf /var/lib/docker
# /var/lib/docker 是docker的默认工作路径！
```

```shell
# 配置阿里云镜像加速
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://d6jl5829.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

```shell
# 测试 hello-world
[root@localhost /]# docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
b8dfde127a29: Pull complete 
Digest: sha256:f2266cbfc127c960fd30e76b7c792dc23b588c0db76233517e1891a4e357d519
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
https://docs.docker.com/get-started/
[root@localhost /]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED       SIZE
hello-world   latest    d1165f221234   7 weeks ago   13.3kB
[root@localhost /]# docker ps -a
CONTAINER ID   IMAGE         COMMAND    CREATED          STATUS                      PORTS     NAMES
055e54f93b80   hello-world   "/hello"   44 seconds ago   Exited (0) 43 seconds ago             nostalgic_banzai
[root@localhost /]# 
```

### Docker run 流程图
![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjYzNzI0Ni5wbmc.png)

### 底层原理

Docker是怎么工作的？

Docker是一个Client-Server结构的系统，Docker的守护进程运行在主机上。通过Socket从客户端访问！

Docker-Server接收到Docker-Client的指令，就会执行这个命令！

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjk0OTU1OC5wbmc.png)

### 为什么Docker比VM快

1. Docker有着比虚拟机更少的抽象层。由于Docker不需要Hypervisor实现硬件资源虚拟化，运行Docker容器上的程序直接使用的都是实际物理机的硬件资源。因此在CPU、内存利用率上Docker将会在效率上有明显优势。
2. Docker利用的是宿主机的内核，而不需要Guest OS。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker-container-vs-virtual-machines.png)

*Guest OS：VM里的系统（OS）*

*Host OS：物理机里的系统（OS）*

因此，当新建一个 容器时，docker不需要和虚拟机一样重新加载一个操作系统内核。而避免引导、加载操作系统内核这个比较费时费资源的过程，当新建一个虚拟机时，虚拟机软件需要加载GuestOS，返个新建过程是**分钟级别**的。而docker由于直接利用宿主机的操作系统，则省略了这个复杂的过程，因此新建一个docker容器只需要**几秒钟**。

# Docker的常用命令

## 帮助命令

```shell
docker version    		#显示docker的版本信息。
docker info       	 	#显示docker的系统信息，包括镜像和容器的数量
docker [命令] --help  #帮助命令
```

## 镜像命令

```shell
docker images 	#查看所有本地主机上的镜像 可以使用docker image ls代替
docker search 	#搜索镜像
docker pull 		#下载镜像 docker image pull
docker rmi 			#删除镜像
```

### docker images

```shell
[root@localhost /]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED       SIZE
hello-world   latest    d1165f221234   7 weeks ago   13.3kB

# REPOSITORY 	镜像的仓库源
# TAG					镜像的标签（版本）	--latest：最新版本
# IMAGE ID		镜像的ID
# CREATED			镜像的创建时间
# SIZE				镜像的大小

# 可选操作
Options:
  -a, --all            Show all images (default hides intermediate images) #列出所有镜像详细信息
  -f, --filter filter  Filter output based on conditions provided # 过滤
  -q, --quiet          Only show image IDs # 只显示镜像的ID
 
[root@localhost /]# docker images -a # 列出所有镜像详细信息
REPOSITORY    TAG       IMAGE ID       CREATED       SIZE
hello-world   latest    d1165f221234   7 weeks ago   13.3kB
[root@localhost /]# docker images -aq # 列出所有镜像的id
d1165f221234
```

### docker search

```shell
# 搜索镜像 docker search 镜像名
[root@localhost /]# docker search mysql # 搜索mysql镜像

[root@localhost /]# docker search mysql -f=STARS=3000 #过滤，搜索出来的镜像收藏STARS数量大于3000的
NAME      DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
mysql     MySQL is a widely used, open-source relation…   10777     [OK]       
mariadb   MariaDB Server is a high performing open sou…   4058      [OK]

Options:
  -f, --filter filter   Filter output based on conditions provided
      --format string   Pretty-print search using a Go template
      --limit int       Max number of search results (default 25)
      --no-trunc        Don't truncate output
```

### docker pull

```shell
# 下载镜像 docker pull 镜像名[:tag]
[root@localhost /]# docker pull tomcat:8
8: Pulling from library/tomcat # 如果不写tag，默认下载latest 最新版
bd8f6a7501cc: Pull complete # 分层下载：docker image的核心 联合文件系统
44718e6d535d: Pull complete 
efe9738af0cb: Pull complete 
f37aabde37b8: Pull complete 
b87fc504233c: Pull complete 
cc62143cb8cc: Pull complete 
646a47c88e43: Pull complete 
d65bec3def24: Pull complete 
412254a7aeaf: Pull complete 
4e00d9861f01: Pull complete 
Digest: sha256:2a39537f6c3c53373fdd047b5b7893f29366926bbe6d4b0740ec58841755febf # 签名
Status: Downloaded newer image for tomcat:8 
docker.io/library/tomcat:8 # 真实地址

docker pull tomcat:8 等价于 docker pull docker.io/library/tomcat:8
```

### docker rmi

```shell
# 删除执行ID的镜像 docker rmi -f 镜像ID
[root@localhost /]# docker rmi -f d1165f221234

[root@localhost /]# docker rmi -f $(docker images -aq) # 删除全部镜像

类似的命令：docker stop $(docker ps -a -q) # 停止全部容器
```

## 容器命令

> 我们有了镜像才可以创建容器

```shell
# docker中下载centos
[root@localhost /]# docker pull centos
Using default tag: latest
latest: Pulling from library/centos
7a0437f04f83: Pull complete 
Digest: sha256:5528e8b1b1719d34604c87e11dcd1c0a20bedf46e83b5632cdeac91b8c04efc1
Status: Downloaded newer image for centos:latest
docker.io/library/centos:latest
```

```shell
docker run 镜像id 			#新建容器并启动
docker ps 						 #列出所有运行的容器 docker container list
docker rm 容器id 				#删除指定容器
docker start 容器id    	#启动容器
docker restart 容器id   #重启容器
docker stop 容器id    	#停止当前正在运行的容器
docker kill 容器id    	#强制停止当前容器
```

### docker run

```shell
docker run [可选参数] image | docker container run [可选参数] image 
# 参数说明
--name="name" # 容器名字 tomcat01 tomcat02 用来区分容器
-d						# 后台方式运行
-it						# 使用交互方式运行，进入容器查看内容
-p						# 指定容器的端口 -p 8080(宿主机端口):8080(容器内端口) 端口映射
	-p ip:宿主机端口:容器端口
	-p 宿主机端口:容器端口 常用
	-p 容器端口
	-P(大写) 随机指定端口
	
# 测试启动并进入容器，以bash方式
[root@localhost /]# docker run -it  centos /bin/bash
[root@a02bee18badc /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@a02bee18badc /]# cat /etc/os-release  
NAME="CentOS Linux"
VERSION="8"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="8"
PLATFORM_ID="platform:el8"
PRETTY_NAME="CentOS Linux 8"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:8"
HOME_URL="https://centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"
CENTOS_MANTISBT_PROJECT="CentOS-8"
CENTOS_MANTISBT_PROJECT_VERSION="8"
[root@a02bee18badc /]# exit # 从容器退出回到宿主机
exit
[root@localhost /]# 
```

### docker ps

```shell
# 列出所有运行的容器 docker ps
docker ps 命令          #列出当前正在运行的容器
  -a, --all          #列出当前正在运行的容器 + 带出历史运行过的容器
  -n=?, --last int   #列出最近创建的?个容器 ?为1则只列出最近创建的一个容器,为2则列出2个
  -q, --quiet        #只列出容器的编号
  
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@localhost /]# docker ps -a
CONTAINER ID   IMAGE          COMMAND       CREATED          STATUS                      PORTS     NAMES
a02bee18badc   centos         "/bin/bash"   3 minutes ago    Exited (0) 3 minutes ago              practical_goldstine
055e54f93b80   d1165f221234   "/hello"      40 minutes ago   Exited (0) 40 minutes ago             nostalgic_banzai
[root@localhost /]# docker ps -q
[root@localhost /]# docker ps -aq
a02bee18badc
055e54f93b80
```

### exit

```shell
# 退出容器
exit					# 容器直接退出
ctrl + p + q 	# 退出容器当不停止容器

# 测试 ctrl + p + q
[root@localhost /]# docker run -it centos /bin/bash
[root@8d4404599946 /]# # ctrl + p + q 退出容器
[root@localhost /]# docker ps 查看当前正在运行的容器
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
8d4404599946   centos    "/bin/bash"   8 seconds ago   Up 7 seconds             happy_swanson
[root@localhost /]# 
```

### docker rm

```shell
# 删除容器 docker rm 容器ID
docker rm 容器id                   	 #删除指定的容器，不能删除正在运行的容器，如果要强制删除 rm -f
docker rm -f $(docker ps -aq)       #删除所有的容器
docker ps -a -q|xargs docker rm  		#删除所有的容器

# 测试
[root@69d1f261f47d /]# 
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
69d1f261f47d   centos    "/bin/bash"   6 seconds ago   Up 5 seconds             strange_pare
[root@localhost /]# docker rm 69d1f261f47d
Error response from daemon: You cannot remove a running container 69d1f261f47db059b463c4f76a0e7666219b4c7b8d62264d1fcd7813debc0bd7. Stop the container before attempting removal or force remove
[root@localhost /]# docker rm -f 69d1f261f47d
69d1f261f47d
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
```

## 其他常见命令

### 后台启动命令

```shell
# 命令 docker run -d 镜像名
[root@localhost /]# docker run -d centos
05eaa53a21ebae9017859058f744b36dd2661b27eb6384eade6a060ec61927c4
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
# 问题docker ps. 发现centos 停止了
# 常见的坑，docker容器使用后台运行，就必须要有要一个前台进程，docker发现没有应用，就会自动停止
# nginx，容器启动后，发现自己没有提供服务，就会立刻停止，就是没有程序了
```

### 日志命令

```shell
[root@localhost /]# docker logs --help

Usage:  docker logs [OPTIONS] CONTAINER

Fetch the logs of a container

Options:
      --details        Show extra details provided to logs
  -f, --follow         Follow log output
      --since string   Show logs since timestamp (e.g. 2013-01-02T13:23:37Z) or relative (e.g. 42m for 42 minutes)
  -n, --tail string    Number of lines to show from the end of the logs (default "all")
  -t, --timestamps     Show timestamps
      --until string   Show logs before a timestamp (e.g. 2013-01-02T13:23:37Z) or relative (e.g. 42m for 42 minutes)

# 显示日志
-tf        #显示日志信息（一直更新）
--tail number #需要显示日志条数
docker logs -t --tail n 容器id #查看n行日志
docker logs -tf 容器id #跟着日志
```

### 查看容器中进程信息

```shell
# docker top 容器ID
[root@localhost /]# docker top eb1b4bec4501
UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
root                14881               14859               0                   19:20               pts/0               00:00:00            /bin/bash
```

### 查看容器元数据

```shell
# docker inspect 容器ID

# 测试
[root@localhost /]# docker inspect eb1b4bec4501
[
    {
        "Id": "eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237",
        "Created": "2021-04-26T11:20:39.886706496Z",
        "Path": "/bin/bash",
        "Args": [],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 14881,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2021-04-26T11:20:40.154136898Z",
            "FinishedAt": "0001-01-01T00:00:00Z"
        },
        "Image": "sha256:300e315adb2f96afe5f0b2780b87f28ae95231fe3bdd1e16b9ba606307728f55",
        "ResolvConfPath": "/var/lib/docker/containers/eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237/hostname",
        "HostsPath": "/var/lib/docker/containers/eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237/hosts",
        "LogPath": "/var/lib/docker/containers/eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237/eb1b4bec45011cd26cd3781cc59f0b7f54798b6c94fa44bba46a0ce7dfb35237-json.log",
        "Name": "/objective_brahmagupta",
        "RestartCount": 0,
        "Driver": "overlay2",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": null,
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "default",
            "PortBindings": {},
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "CapAdd": null,
            "CapDrop": null,
            "CgroupnsMode": "host",
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "private",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "ConsoleSize": [
                0,
                0
            ],
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": null,
            "BlkioDeviceWriteBps": null,
            "BlkioDeviceReadIOps": null,
            "BlkioDeviceWriteIOps": null,
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DeviceRequests": null,
            "KernelMemory": 0,
            "KernelMemoryTCP": 0,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": false,
            "PidsLimit": null,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0,
            "MaskedPaths": [
                "/proc/asound",
                "/proc/acpi",
                "/proc/kcore",
                "/proc/keys",
                "/proc/latency_stats",
                "/proc/timer_list",
                "/proc/timer_stats",
                "/proc/sched_debug",
                "/proc/scsi",
                "/sys/firmware"
            ],
            "ReadonlyPaths": [
                "/proc/bus",
                "/proc/fs",
                "/proc/irq",
                "/proc/sys",
                "/proc/sysrq-trigger"
            ]
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/584829cf1db28776b5ed5fa3b3a2d4550542afecf08107c4deb4809b78f5c9eb-init/diff:/var/lib/docker/overlay2/ea6b48e4291c936a70c897e76aef46010a4ec61bab0d1c31c583fba6734e7fd8/diff",
                "MergedDir": "/var/lib/docker/overlay2/584829cf1db28776b5ed5fa3b3a2d4550542afecf08107c4deb4809b78f5c9eb/merged",
                "UpperDir": "/var/lib/docker/overlay2/584829cf1db28776b5ed5fa3b3a2d4550542afecf08107c4deb4809b78f5c9eb/diff",
                "WorkDir": "/var/lib/docker/overlay2/584829cf1db28776b5ed5fa3b3a2d4550542afecf08107c4deb4809b78f5c9eb/work"
            },
            "Name": "overlay2"
        },
        "Mounts": [],
        "Config": {
            "Hostname": "eb1b4bec4501",
            "Domainname": "",
            "User": "",
            "AttachStdin": true,
            "AttachStdout": true,
            "AttachStderr": true,
            "Tty": true,
            "OpenStdin": true,
            "StdinOnce": true,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/bin/bash"
            ],
            "Image": "centos",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {
                "org.label-schema.build-date": "20201204",
                "org.label-schema.license": "GPLv2",
                "org.label-schema.name": "CentOS Base Image",
                "org.label-schema.schema-version": "1.0",
                "org.label-schema.vendor": "CentOS"
            }
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "34bdc76e533f65a011f6ad6e21faf5c8f8a21ce310c507e8d723e57cdd3a4aee",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/34bdc76e533f",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "a5b254ee6f6186e3a1fbc4b50a4ab273814d654fbbc783a76310d81ec89ebf37",
            "Gateway": "172.17.0.1",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "172.17.0.2",
            "IPPrefixLen": 16,
            "IPv6Gateway": "",
            "MacAddress": "02:42:ac:11:00:02",
            "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "d499f327340db93ac014d90ef0a2a9f7a867b841b35ce298dd97d97e4c0f6b07",
                    "EndpointID": "a5b254ee6f6186e3a1fbc4b50a4ab273814d654fbbc783a76310d81ec89ebf37",
                    "Gateway": "172.17.0.1",
                    "IPAddress": "172.17.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:11:00:02",
                    "DriverOpts": null
                }
            }
        }
    }
]
```

### 进入当前正在运行的容器

我们通常容器都是使用后台方式运行的，有时候需要进入容器修改一些配置的需求。

```shell
# 方式一
# docker exec -it 容器ID bashshell

# 测试
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
eb1b4bec4501   centos    "/bin/bash"   4 minutes ago   Up 4 minutes             objective_brahmagupta
[root@localhost /]# docker exec -it eb1b4bec4501 /bin/bash
[root@eb1b4bec4501 /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

# 方式二
# docker attach 容器ID

# 测试
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
eb1b4bec4501   centos    "/bin/bash"   5 minutes ago   Up 5 minutes             objective_brahmagupta
[root@localhost /]# docker attach eb1b4bec4501
[root@eb1b4bec4501 /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

区别
# docker exec #进入当前容器后开启一个新的终端，可以在里面操作。（常用）
# docker attach # 进入容器正在执行的终端
```

### 从容器内拷贝到主机上

```shell
# docker cp 容器ID:容器内路径 宿主机目的路径

# 测试
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
eb1b4bec4501   centos    "/bin/bash"   8 minutes ago   Up 8 minutes             objective_brahmagupta
# 进入容器
[root@localhost /]# docker exec -it eb1b4bec4501 /bin/bash
[root@eb1b4bec4501 /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@eb1b4bec4501 /]# cd home
# 新建文件
[root@eb1b4bec4501 home]# echo "hello" > test.java
[root@eb1b4bec4501 home]# ls
test.java
[root@eb1b4bec4501 home]# cat test.java 
hello
[root@eb1b4bec4501 home]# exit
exit
# 拷贝到宿主机
[root@localhost /]# docker cp eb1b4bec4501:/home/test.java /home
[root@localhost /]# ls
bin  boot  dev  etc  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@localhost /]# cd home
# 验证
[root@localhost home]# ls
test.java
[root@localhost home]# cat test.java
hello
```

## 命令大全

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker-commands.png)

```shell
attach      Attach local standard input, output, and error streams to a running container
#当前shell下 attach连接指定运行的镜像
build       Build an image from a Dockerfile # 通过Dockerfile定制镜像
commit      Create a new image from a container's changes #提交当前容器为新的镜像
cp          Copy files/folders between a container and the local filesystem #拷贝文件
create      Create a new container #创建一个新的容器
diff        Inspect changes to files or directories on a container's filesystem #查看docker容器的变化
events      Get real time events from the server # 从服务获取容器实时时间
exec        Run a command in a running container # 在运行中的容器上运行命令
export      Export a container's filesystem as a tar archive #导出容器文件系统作为一个tar归档文件[对应import]
history     Show the history of an image # 展示一个镜像形成历史
images      List images #列出系统当前的镜像
import      Import the contents from a tarball to create a filesystem image #从tar包中导入内容创建一个文件系统镜像
info        Display system-wide information # 显示全系统信息
inspect     Return low-level information on Docker objects #查看容器详细信息
kill        Kill one or more running containers # kill指定docker容器
load        Load an image from a tar archive or STDIN #从一个tar包或标准输入中加载一个镜像[对应save]
login       Log in to a Docker registry #
logout      Log out from a Docker registry
logs        Fetch the logs of a container
pause       Pause all processes within one or more containers
port        List port mappings or a specific mapping for the container
ps          List containers
pull        Pull an image or a repository from a registry
push        Push an image or a repository to a registry
rename      Rename a container
restart     Restart one or more containers
rm          Remove one or more containers
rmi         Remove one or more images
run         Run a command in a new container
save        Save one or more images to a tar archive (streamed to STDOUT by default)
search      Search the Docker Hub for images
start       Start one or more stopped containers
stats       Display a live stream of container(s) resource usage statistics
stop        Stop one or more running containers
tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
top         Display the running processes of a container
unpause     Unpause all processes within one or more containers
update      Update configuration of one or more containers
version     Show the Docker version information
wait        Block until one or more containers stop, then print their exit codes
```

## 安装Nginx

```shell
[root@localhost /]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
nginx        1.20.0    718f34297111   4 days ago     133MB
redis        6.2       739b59b96069   5 days ago     105MB
centos       latest    300e315adb2f   4 months ago   209MB
# 运行nginx镜像
# -d 后台方式
# --name 给该容器命名
# -p 指定端口
[root@localhost /]# docker run -d --name nginx01 -p 3344:80 nginx:1.20.0
04e1c8d40eb31d678a52caf5267cdc2877828285dd5c4413d6be176f2de3be82
[root@localhost /]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                   NAMES
04e1c8d40eb3   nginx:1.20.0   "/docker-entrypoint.…"   6 seconds ago   Up 5 seconds   0.0.0.0:3344->80/tcp, :::3344->80/tcp   nginx01
# 进入正在运行的nginx01容器
[root@localhost /]# docker exec -it nginx01 /bin/bash
root@04e1c8d40eb3:/# whereis nginx
nginx: /usr/sbin/nginx /usr/lib/nginx /etc/nginx /usr/share/nginx
root@04e1c8d40eb3:/# cd /etc/nginx/
root@04e1c8d40eb3:/etc/nginx# ls
conf.d  fastcgi_params  mime.types  modules  nginx.conf  scgi_params  uwsgi_params
root@04e1c8d40eb3:/etc/nginx# exit
exit
[root@localhost /]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                   NAMES
04e1c8d40eb3   nginx:1.20.0   "/docker-entrypoint.…"   2 minutes ago   Up 2 minutes   0.0.0.0:3344->80/tcp, :::3344->80/tcp   nginx01
# 停止容器
[root@localhost /]# docker stop 04e1c8d40eb3
04e1c8d40eb3
[root@localhost /]# 
```

宿主机端口和容器内部端口以及端口暴露：

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNDIxNTkxNTY1MC5wbmc.png)



# Docker镜像

> 镜像是一种轻量级、可执行的独立软件保，用来打包软件运行环境和基于运行环境开发的软件，他包含运行某个软件所需的所有内容，包括代码、运行时库、环境变量和配置文件。

所有应用，直接打包docker镜像，就可以直接跑起来！

**如何得到镜像**

- 从远程仓库下载
- 别人拷贝给你
- 自己制作一个镜像 DockerFile

## Docker镜像加载原理

### UnionFS（联合文件系统）

UnionFS（联合文件系统）：Union文件系统（UnionFS）是一种分层、轻量化并且高性能的文件系统，它支持对文件系统的修改作为一次提交来一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下（unite several directories into a single virtual filesystem）。Union文件系统是Docker镜像的基础。镜像可以通过分层来进行集成，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。

特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录。

### Docker镜像加载原理

Docker的镜像实际上由一层一层的文件系统组成，这种层级的文件系统UnionFS。

bootfs（boot file system）主要包含bootloader和Kernel，bootloader主要是引导加载Kernel，Linux刚启动时会加载bootfs文件系统，在Docker镜像的最底层是bootfs。这一层与我们典型的Linux/Unix系统是一样的，包含boot加载器和内核。当boot加载完成之后整个内核就都在内存中了，此时内存的使用权已由bootfs转交给内核，此时系统也会卸载bootfs。

rootfs（root file system），在bootfs之上。包含的就是典型的Linux系统中的/dev，/proc，/bin，/etc等标准目录和文件。rootfs就是各种不同的操作系统发行版，比如Ubuntu、centos等。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2MzA0OTk1OS5wbmc.png)



*平时我们安装进虚拟机的CentOS都是好几个G，为什么Docker这里才200M？*

对于精简的OS，rootfs可以很小，只需要包含最基本的命令，工具和程序库就可以了，因为最底层直接用Host的Kernel，自己只需要提供rootfs就可以了，由此可见对于不同的Linux发行版，bootfs基本是一致的，rootfs会有差别，因此不同的发行版可以共用bootfs。

## 分层理解

可以去下载一个镜像，注意观察下载的日志输出，可以看到是一层层的在下载

```shell
[root@localhost /]# docker pull tomcat
Using default tag: latest
latest: Pulling from library/tomcat
bd8f6a7501cc: Pull complete 
44718e6d535d: Pull complete 
efe9738af0cb: Pull complete 
f37aabde37b8: Pull complete 
b87fc504233c: Pull complete 
8bf93eef8c9e: Pull complete 
8d33b4df10ef: Pull complete 
2d77aa1a5442: Pull complete 
baa04cc4071d: Pull complete 
a3a752513415: Pull complete 
Digest: sha256:0509684774ac53d8b152f4a50dd92889218a8633c0feddb4b35d092e55cd215d
Status: Downloaded newer image for tomcat:latest
docker.io/library/tomcat:latest
```

**为什么Docker镜像要采用这种分层的结构呢？**

最大的好处，莫过于资源共享了！比如有多个镜像都从相同的Base镜像构建而来，那么宿主机只需在磁盘上保留一份Base镜像，同时内存中也只需要加载一份Base镜像，这样就可以为所有的容器服务了，而且镜像的每一层都可以被共享。

查看镜像分层的方式可以通过`docker image inspect` 命令

```shell
[root@localhost /]# docker image inspect redis:6.2
[
    {
        "Id": "sha256:739b59b96069d2edfa60f66b5efb4960e7129053ccbb2ac5fd1c9351e7731918",
        "RepoTags": [
            "redis:6.2"
        ],
        "RepoDigests": [
            "redis@sha256:e10f55f92478715698a2cef97c2bbdc48df2a05081edd884938903aa60df6396"
        ],
        "Parent": "",
        "Comment": "",
        "Created": "2021-04-20T23:46:01.04261128Z",
        "Container": "88b3715d12efac631774aebef922d5cb0abb62ca233e4c626ebd26c81e5a4408",
        "ContainerConfig": {
            "Hostname": "88b3715d12ef",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "6379/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "GOSU_VERSION=1.12",
                "REDIS_VERSION=6.2.2",
                "REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-6.2.2.tar.gz",
                "REDIS_DOWNLOAD_SHA=7a260bb74860f1b88c3d5942bf8ba60ca59f121c6dce42d3017bed6add0b9535"
            ],
            "Cmd": [
                "/bin/sh",
                "-c",
                "#(nop) ",
                "CMD [\"redis-server\"]"
            ],
            "Image": "sha256:ae6907a3adb277fada24702893484644ffc8de5cdedae7a9baa7d9fb5350b5d4",
            "Volumes": {
                "/data": {}
            },
            "WorkingDir": "/data",
            "Entrypoint": [
                "docker-entrypoint.sh"
            ],
            "OnBuild": null,
            "Labels": {}
        },
        "DockerVersion": "19.03.12",
        "Author": "",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "6379/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "GOSU_VERSION=1.12",
                "REDIS_VERSION=6.2.2",
                "REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-6.2.2.tar.gz",
                "REDIS_DOWNLOAD_SHA=7a260bb74860f1b88c3d5942bf8ba60ca59f121c6dce42d3017bed6add0b9535"
            ],
            "Cmd": [
                "redis-server"
            ],
            "Image": "sha256:ae6907a3adb277fada24702893484644ffc8de5cdedae7a9baa7d9fb5350b5d4",
            "Volumes": {
                "/data": {}
            },
            "WorkingDir": "/data",
            "Entrypoint": [
                "docker-entrypoint.sh"
            ],
            "OnBuild": null,
            "Labels": null
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 105375876,
        "VirtualSize": 105375876,
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/12f920d1175246765de6ff48d49c7288622d2a58239e973cb7243dd3eb56cab2/diff:/var/lib/docker/overlay2/5e70f049390e15bc90776a1c69726c17ebab09e529e481eb05bdafe478158b5f/diff:/var/lib/docker/overlay2/ed608557560e92d0f1d0cb08cf76a67b1345ed55f9d2b8aa2cce4542228967c5/diff:/var/lib/docker/overlay2/5ea3ed91e531c0628580f2728dec6a3067b8e8e4ef6c44f2087fbec419d2793e/diff:/var/lib/docker/overlay2/575a9869d5a7b38e40449e03eecec97e279d44732b4a39816a25b7873b9bf16c/diff",
                "MergedDir": "/var/lib/docker/overlay2/9b314ff4190847e7eaa7562d6e58f1cff64fccc7e4afa9e9adf9fee962a3a7c6/merged",
                "UpperDir": "/var/lib/docker/overlay2/9b314ff4190847e7eaa7562d6e58f1cff64fccc7e4afa9e9adf9fee962a3a7c6/diff",
                "WorkDir": "/var/lib/docker/overlay2/9b314ff4190847e7eaa7562d6e58f1cff64fccc7e4afa9e9adf9fee962a3a7c6/work"
            },
            "Name": "overlay2"
        },
        # 分层
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:7e718b9c0c8c2e6420fe9c4d1d551088e314fe923dce4b2caf75891d82fb227d",
                "sha256:89ce1a07a7e4574d724ea605b4877f8a73542cf6abd3c8cbbd2668d911fa5353",
                "sha256:9eef6e3cc2937e452b2325b227ca28120a70481be25404ed9aad27fa81219fd0",
                "sha256:6eeff2593e885a7801c56f15be93e3d9968a9d3afb03df7602a89357c2d28c4d",
                "sha256:0302cabf4dde0b7c18a0cc372cd7e1038656815bd9300aeb64d7033faf77700a",
                "sha256:35613d8e624ae292403c8e291353c3b654729c92963a7ae88be11684522a77db"
            ]
        },
        "Metadata": {
            "LastTagTime": "0001-01-01T00:00:00Z"
        }
    }
]
```

**理解**

所有的 Docker镜像都起始于一个基础镜像层，当进行修改或培加新的内容时，就会在当前镜像层之上，创建新的镜像层。

举一个简单的例子，假如基于 Ubuntu Linux16.04创建一个新的镜像，这就是新镜像的第一层；如果在该镜像中添加 Python包，就会在基础镜像层之上创建第二个镜像层；如果继续添加一个安全补丁，就会创健第三个镜像层，该镜像当前已经包含3个镜像层，如下图所示（这只是一个用于演示的很简单的例子）。

在添加额外的镜像层的同时，镜像始终保持是当前所有镜像的组合。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2NTIzNDI3NC5wbmc.png)

在添加额外的镜像层的同时，镜像始终保持是当前所有镜像的组合，理解这一点非常重要。下图中举了一个简单的例子，每个镜像层包含3个文件，而镜像包含了来自两个镜像层的6个文件。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2NDk1ODkzMi5wbmc.png)

上图中的镜像层跟之前图中的略有区別，主要目的是便于展示文件。
下图中展示了一个稍微复杂的三层镜像，在外部看来整个镜像只有6个文件，这是因为最上层中的文件7是文件5的一个更新版。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2NTE0ODAwMi5wbmc.png)

这种情況下，上层镜像层中的文件覆盖了底层镜像层中的文件。这样就使得文件的更新版本作为一个新镜像层添加到镜像当中。

Docker通过存储引擎（新版本采用快照机制）的方式来实现镜像层堆栈，并保证多镜像层对外展示为统一的文件系统，Linux上可用的存储引撃有AUFS、 Overlay2、 Device Mapper、Btrfs以及ZFS。顾名思义，每种存储引擎都基于 Linux中对应的文件系统或者块设备技术，井且每种存储引擎都有其独有的性能特点。

Docker在 Windows上仅支持 windowsfilter 一种存储引擎，该引擎基于NTFS文件系统之上实现了分层和CoW [1]。

下图展示了与系统显示相同的三层镜像。所有镜像层堆并合井，对外提供统一的视图。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2NTU1NzgwNy5wbmc.png)

### 特点

Docker 镜像都是只读的，当容器启动时，一个新的可写层加载到镜像的顶部。

这一层就是我们通常说的容器层，容器之下的都叫镜像层。

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker%E9%95%9C%E5%83%8F%E5%88%86%E5%B1%82.jpg)

## commit 镜像

```shell
# docker commit 提交容器成为一个新的副本
# 命令与git类似
docker commit -m="描述信息" -a="作者" 容器ID 目标镜像名:[版本TAG]

# 测试

[root@localhost /]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
tomcat       latest    c0e850d7b9bb   3 days ago     667MB
[root@localhost /]# docker run -d -p 8080:8080 tomcat
b2f65d8690350c9f5fbd7a53c009faaeaf3cd4b0bab38d79677bcfafa269f080
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND             CREATED          STATUS          PORTS                                       NAMES
b2f65d869035   tomcat    "catalina.sh run"   12 seconds ago   Up 12 seconds   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   upbeat_swanson
[root@localhost /]# docker exec -it b2f65d869035 /bin/bash
root@b2f65d869035:/usr/local/tomcat# ls
BUILDING.txt  CONTRIBUTING.md  LICENSE  NOTICE  README.md  RELEASE-NOTES  RUNNING.txt  bin  conf  lib  logs  native-jni-lib  temp  webapps  webapps.dist  work
[root@localhost /]# docker commit -m="newtomcat" -a="xxx<xxx@163.com>" b2f65d8690350c9f5fbd7a53c009faaeaf3cd4b0bab38d79677bcfafa269f080 tomcat01:1.0
sha256:b4258f7118529b9bec573828a28a2e24c47def3055ee2926705469d15147e7c5
[root@localhost /]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
tomcat01     1.0       b4258f711852   5 seconds ago   667MB # 通过commit生成的镜像
tomcat       latest    c0e850d7b9bb   3 days ago      667MB
```

*如果你想要保存当前容器的状态，就可以通过commit来提交，获得一个镜像，就好比我们我们使用虚拟机的快照。*

# 数据卷（volume）

## 什么是数据卷

首先需要明确Docker内的文件系统是如何工作的。Docker镜像被存储在一系列的只读层。当我们开启一个容器，Docker读取只读镜像并添加一个读写层在顶部。如果正在运行的容器修改了现有的文件，该文件将被拷贝出底层的只读层，到最顶层的读写层。在读写层中的旧版本文件隐藏于该文件下，但并没有被破坏（它仍然存在于镜像以下）。当Docker的容器被删除，然后重新启动镜像时，将开启一个没有任何更改的新的容器（之前的那些更改将会丢失）。此只读层及在顶部的读写层的组合被Docker称为Union File System（联合文件系统）。

为了能够保存（持久化）数据以及共享容器间的数据，Docker提出了Volumes的概念。volumes是目录（或文件），它们是外部默认的联合文件系统或者是存在于宿主文件系统正常的目录和文件。

## 为什么使用数据卷volume

 Docker的镜像是由一系列的只读层组合而来，当启动一个容器的时候，Docker加载镜像的所有只读层，并在最上层加入一个读写层。这个设计使得Docker可以提高镜像构建、存储和分发的效率，节省了时间和存储空间，然而也存在如下问题。

1. 容器中的文件在宿主机上存在的形式复杂，不能在宿主机上很方便的对容器中的文件进行访问。
2. 多个容器之间的数据无法共享。
3. 当删除容器时，容器产生的数据将丢失。

为了解决这些问题，Docker引入了数据卷（volume）机制。volume是存在一个或多个容器中的特定文件或文件夹，这个能够独立于联合文件系统的形式在宿主机中存在，并为数据的共享与持久提供一下便利。

1. volume在容器创建时就初始化，在容器运行时就可以使用其中的文件。
2. volume能在不同的容器之间共享和重用
3. 对volume中的数据的操作会马上生效
4. 对volume中数据操作不会影响到镜像本身
5. volume的生存周期独立于容器的生存周期，即使删除容器，volume仍然会存在，没有任何容器使用的volume也不会被Docker删除

## 使用数据卷

```shell
docker volume 命令
create      # 创建数据卷
inspect     # 显示数据卷的详细信息
ls          # 列出所有的数据卷
prune       # 删除所有未使用的volumes，并有-f选项
rm          # 删除一个或多个未使用的volumes，并有-f选项
```

### 方式一：直接使用命令挂载 -v

```shell
-v, --volume list		# Bind mount a volume

docker run -it -v 主机目录:容器内目录 -p 主机端口:容器内端口

[root@localhost /]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
tomcat       latest    c0e850d7b9bb   3 days ago     667MB
nginx        1.20.0    718f34297111   4 days ago     133MB
redis        6.2       739b59b96069   5 days ago     105MB
mysql        8.0.24    0627ec6901db   6 days ago     556MB
centos       latest    300e315adb2f   4 months ago   209MB
[root@localhost /]# docker run -it -v /home/ceshi:/home centos /bin/bash 
# /home/ceshi：主机home目录下的ceshi文件夹  映射：centos容器中的/home
# 这时候主机的/home/ceshi文件夹就和容器的/home文件夹关联了,二者可以实现文件或数据同步了

[root@localhost /]# docker inspect e4e0c9faef92
"Mounts": [ # 挂载 -v 卷
  {
    "Type": "bind",
    "Source": "/home/ceshi", # 主机内地址
    "Destination": "/home",  # docker容器内的地址
    "Mode": "",
    "RW": true,
    "Propagation": "rprivate"
  }
]

# 这个时候，docker centos容器内的/home/ceshi目录就与主机的/home目录挂载了，两者之间的文件时同步的
# 我们以后修改只需要在本地修改即可，容器内会自动同步。
```

## 实战：安装MySQL

*MySQL的数据持久化的问题*

```shell
[root@localhost /]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
mysql        8.0.24    0627ec6901db   6 days ago     556MB

# 运行容器,需要做数据挂载 
# 安装启动mysql，需要配置密码的，这是要注意点！
# 参考官网hub 

# -d			后台运行
# -p			端口映射
# -v			卷挂载
# -e			环境配置
# --name	容器名字
[root@localhost /]# docker run -d -p 3307:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:8.0.24
5330c843cdd1693b0f99b1c4044e1db04945165477b5f7b2e2acf53f3d151393
# 此时可以通过图形化工具连接该mysql
```

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/1619452364617.jpg)

当用navicat创建一个名为test的数据库时，容器也会创建

```shell
# docker容器
root@5330c843cdd1:/var/lib/mysql# ls
'#ib_16384_0.dblwr'   auto.cnf        binlog.index   client-cert.pem   ib_logfile0   ibtmp1      performance_schema   server-cert.pem   test # 容器内的
'#ib_16384_1.dblwr'   binlog.000001   ca-key.pem     client-key.pem    ib_logfile1   mysql       private_key.pem      server-key.pem    undo_001
'#innodb_temp'        binlog.000002   ca.pem         ib_buffer_pool    ibdata1       mysql.ibd   public_key.pem       sys               undo_002

# 主机
[root@localhost data]# ls
auto.cnf       client-cert.pem    ib_logfile0   performance_schema  test # 主机上的
binlog.000001  client-key.pem     ib_logfile1   private_key.pem     undo_001
binlog.000002  #ib_16384_0.dblwr  ibtmp1        public_key.pem      undo_002
binlog.index   #ib_16384_1.dblwr  #innodb_temp  server-cert.pem
ca-key.pem     ib_buffer_pool     mysql         server-key.pem
ca.pem         ibdata1            mysql.ibd     sys
```

现将包含mysql的容器删除

```shell
[root@localhost /]# docker rm -f mysql01
mysql01
[root@localhost /]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES

# 可以看到当mysql容器删除后，主机上的数据卷没有丢失，这就实现了容器数据的持久化功能。
[root@localhost data]# ls
auto.cnf       binlog.index  client-cert.pem    #ib_16384_1.dblwr  ib_logfile0  #innodb_temp  performance_schema  server-cert.pem  test
binlog.000001  ca-key.pem    client-key.pem     ib_buffer_pool     ib_logfile1  mysql         private_key.pem     server-key.pem   undo_001
binlog.000002  ca.pem        #ib_16384_0.dblwr  ibdata1            ibtmp1       mysql.ibd     public_key.pem      sys              undo_002
```

## 三种挂载方式

### 指定路径挂载

`-v /宿主机路径:容器内路径`

```shell
[root@localhost mysql]# docker run -d -p 3307:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:8.0.24

# 通过指定路径挂载，docker volume ls是无法查到目录的
[root@localhost mysql]# docker volume ls
DRIVER    VOLUME NAME
[root@localhost mysql]# docker inspect mysql01
"Mounts": [ # 数据卷挂载的信息
  {
    "Type": "bind",
    "Source": "/home/mysql/conf",
    "Destination": "/etc/mysql/conf.d",
    "Mode": "",
    "RW": true,
    "Propagation": "rprivate"
  },
  {
    "Type": "bind",
    "Source": "/home/mysql/data",
    "Destination": "/var/lib/mysql",
    "Mode": "",
    "RW": true,
    "Propagation": "rprivate"
  }
],
```

### 具名挂载

`-v 数据卷名:容器内路径`

```shell
[root@localhost /]# docker run -it -v mycentos:/home centos /bin/bash
[root@localhost _data]# docker volume ls
DRIVER    VOLUME NAME
local     mycentos # 多出了刚刚创建的数据卷名
[root@localhost _data]# docker volume inspect mycentos
[
    {
        "CreatedAt": "2021-04-27T00:17:00+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/mycentos/_data", # docker的默认目录
        "Name": "mycentos",
        "Options": null,
        "Scope": "local"
    }
]


# 测试
# centos容器内
[root@b5ce4c92ed3c /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@b5ce4c92ed3c /]# cd home
[root@b5ce4c92ed3c home]# ls
# 创建了一个文件
[root@b5ce4c92ed3c home]# touch test.java 
[root@b5ce4c92ed3c home]# ls
test.java
[root@b5ce4c92ed3c home]# [root@localhost /]# cd /var/lib/docker/volumes/
[root@localhost volumes]# ls
backingFsBlockDev  metadata.db  mycentos
[root@localhost volumes]# cd mycentos/
[root@localhost mycentos]# ls
_data
[root@localhost mycentos]# cd _data/
# 宿主机上挂载的目录已同步
[root@localhost _data]# ls
test.java
```

### 匿名挂载

`-v 容器内路径 `

```shell
[root@localhost _data]# docker run -it -v /home centos /bin/bash
[root@localhost _data]# docker volume ls
DRIVER    VOLUME NAME
local     f184cc539a4d195ab853f145c7c1584b82972dc28dc2ad96f75e35ede2c7fe72 # 容器内的卷名（匿名挂载）
local     mycentos
[root@localhost _data]# docker volume inspect f184cc539a4d195ab853f145c7c1584b82972dc28dc2ad96f75e35ede2c7fe72
[
    {
        "CreatedAt": "2021-04-27T00:22:03+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/f184cc539a4d195ab853f145c7c1584b82972dc28dc2ad96f75e35ede2c7fe72/_data", # 默认目录
        "Name": "f184cc539a4d195ab853f145c7c1584b82972dc28dc2ad96f75e35ede2c7fe72",
        "Options": null,
        "Scope": "local"
    }
]

# 测试
# centos容器内
[root@f36cf4cb7814 /]# ls     
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@f36cf4cb7814 /]# cd home
[root@f36cf4cb7814 home]# ls
# 创建一个文件
[root@f36cf4cb7814 home]# touch java.txt 
[root@f36cf4cb7814 home]# ls
java.txt

# 宿主机
[root@localhost /]# cd /var/lib/docker/volumes/f184cc539a4d195ab853f145c7c1584b82972dc28dc2ad96f75e35ede2c7fe72/_data
[root@localhost _data]# ls
java.txt
```

### 总结

**所有的docker容器内的卷，没有指定目录的情况下都是在`/var/lib/docker/volumes/自定义的卷名/_data`下，如果指定了目录，`docker volume ls` 是查看不到的。**

区分三种挂载方式

```shell
# 三种挂载： 匿名挂载、具名挂载、指定路径挂载
-v 容器内路径            		#匿名挂载
-v 卷名：容器内路径          	#具名挂载
-v /宿主机路径：容器内路径 		#指定路径挂载 docker volume ls 是查看不到的
```

**拓展：**

```shell
# 通过 -v 容器内路径： ro rw 改变读写权限
ro #readonly 只读
rw #readwrite 可读可写
$ docker run -d -P --name nginx05 -v juming:/etc/nginx:ro nginx
$ docker run -d -P --name nginx05 -v juming:/etc/nginx:rw nginx
# ro 只要看到ro就说明这个路径只能通过宿主机来操作，容器内部是无法操作！
```



## 数据卷容器

多个MySQL同步数据

命名的容器挂载数据卷！

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker%E6%95%B0%E6%8D%AE%E5%90%8C%E6%AD%A5.png)

```shell
# 创建第一个容器centos01
[root@localhost /]# docker run -v VOLUME01 -it --name centos01 centos
[root@601597e32b54 /]# ls
VOLUME01 # 匿名挂载的数据卷目录
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

# 创建第二个容器centos02，通过继承centos01
[root@localhost /]# docker run -it --name centos02 --volumes-from centos01 centos
[root@821c8c4effeb /]# ls
VOLUME01  bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

测试

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/1619455449901.jpg)

```shell
# 再创建一个centos03，同样继承centos01
[root@localhost /]# docker run -it --name centos03 --volumes-from centos01 centos
[root@b5a5456a0316 /]# ls
VOLUME01  bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@b5a5456a0316 /]# cd VOLUME01/
[root@b5a5456a0316 VOLUME01]# cat test.java
hello

# 删除centos01后，centos02和centos03的VOLUEM01目录中的文件依然存在，数据依然保留在centos02和centos03中没有被删除
```

实际上容器间的共享卷是通过拷贝来保存的，所以当删除了centos01后，centos02和centos03中的数据依然存在。

### 多个MySQL实现数据共享

```shell
$ docker run -d -p 3306:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7
$ docker run -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql02 --volumes-from mysql01  mysql:5.7
# 这个时候，可以实现两个容器数据同步！
```

**结论：**

**容器之间的配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止**。

**但是一旦你持久化到了本地，这个时候，本地的数据是不会删除的**！

# DockerFile



# Docker网络

