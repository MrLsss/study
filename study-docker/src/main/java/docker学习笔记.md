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

> Dockerfile 是一个用来构建镜像的文本文件

Dockerfile是一个包含用于组合映像的命令的文本文档。可以使用在命令行中调用任何命令。 Docker通过读取`Dockerfile`中的指令自动生成映像。

`docker build`命令用于从Dockerfile构建映像。可以在`docker build`命令中使用`-f`标志指向文件系统中任何位置的Dockerfile。

例如：`docker build -f /path/to/a/Dockerfile`

## Dockerfile的基本结构

Dockerfile 一般分为四部分：基础镜像信息、维护者信息、镜像操作指令和容器启动时执行指令。

## 构建步骤

1. 编写一个Dockerfile文件
2. docker build构建成为一个镜像
3. docker run运行镜像
4. docker push 发布镜像（DockerHub、阿里云仓库）

**注意：**

- 每个保留关键字(指令）都是必须是大写字母
- 执行从上到下顺序
- #表示注释
- 每一个指令都会创建提交一个新的镜像曾，并提交！

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjEzMTc1Njk5Ny5wbmc.png)

## Dockerfile指令

```shell
FROM                	# from:基础镜像，一切从这里开始构建
MAINTAINER            # maintainer:镜像是谁写的， 姓名+邮箱
RUN                   # run:镜像构建的时候需要运行的命令
ADD                   # add:步骤，tomcat镜像，这个tomcat压缩包！添加内容 添加同目录
WORKDIR               # workdir:镜像的工作目录
VOLUME                # volume:挂载的目录
EXPOSE                # expose:保留端口配置
CMD                   # cmd:指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT            # entrypoint:指定这个容器启动的时候要运行的命令，可以追加命令
ONBUILD               # onbuild:当构建一个被继承DockerFile这个时候就会运行onbuild的指令，触发指令
COPY                	# copy:类似ADD，将我们文件拷贝到镜像中
ENV                   # env:构建的时候设置环境变量！
```

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/20200524154609624.png)

### FROM

> 指定基础镜像，必须为第一个命令

```shell
格式：
FROM <image>
FROM <image>:<tag>
FROM <image>@<digest>

示例：
FROM mysql:5.6

注：
tag或digest是可选的，如果不使用这两个值时，会使用latest版本的基础镜像
```

### MAINTAINER

> 维护者信息

```shell
格式：
MAINTAINER <name>

示例：
MAINTAINER ZhangSan
MAINTAINER ZhangSan<ZhangSan@163.com>
```

### RUN

> 构建镜像时执行的命令

```shell
# RUN 用于在镜像容器中执行命令，其有以下两种命令执行方式。

# shell执行
格式：
RUN <command>

示例：
RUN apk update

# exec执行
格式：
RUN ["executable", "param1", "param2"]

示例：
RUN ["/etc/execfile", "arg1", "arg2"]

注：
RUN指令创建的中间镜像会被缓存，并会在下次构建中使用。如果不想使用这些缓存镜像，可以在构建时指定--no-cache参数，如：docker build --no-cache
```

### ADD

> 将本地文件添加到容器中，tar类型文件会自动解压（网络压缩资源不会被解压），可以访问网络资源，类似wget。

```shell
格式：
ADD <src>...<dest>
ADD ["<src>",... "dest"] # 用于包含空格的路径

示例：
ADD hom* /mydir/				# 添加所有以“hom”开头的文件
ADD home?.txt /mydir/		# ？替代一个单字符，例如：“home.txt”
ADD test relativeDir/		# 添加“test”到 `WORKDIR`/relativeDir/
ADD test /absoluteDir/	# 添加“test”到/absoluteDir/
```

### COPY

> 功能类似ADD，但是不会自动解压文件，也不能访问网络资源。

```shell
格式：
COPY [--chown=<user>:<group>] <源路径1>...  <目标路径>
COPY [--chown=<user>:<group>] ["<源路径1>",...  "<目标路径>"]

[--chown=<user>:<group>]：可选参数，用户改变复制到容器内文件的拥有者和属组。
<源路径>：源文件或者源目录，这里可以是通配符表达式，其通配符规则要满足 Go 的 filepath.Match 规则
<目标路径>：容器内的指定路径，该路径不用事先建好，路径不存在的话，会自动创建。

示例：
COPY hom* /mydir/
COPY hom?.txt /mydir/
```

### CMD

> 构建容器后调用，也就是在容器启动时才进行调用。

```shell
格式：
CMD ["executable", "param1", "param2"]	# 执行可执行文件(推荐)
CMD ["param1", "param2"]								# 该写法是为 ENTRYPOINT 指令指定的程序提供默认参数
CMD command param1 param2								# 在运行的过程中，会自动转换成第一种格式运行

示例：
CMD echo "this is a test." | wc -
CMD ["/usr/bin/wc", "--help"]

注：
CMD不同于RUN，CMD用于指定在容器启动时所要执行的命令(docker run)，而RUN用于指定镜像构建时所要执行的命令(docker build)。
如果 Dockerfile 中如果存在多个 CMD 指令，仅最后一个生效。
```

### ENTRYPOINT

> 配置容器，使其可执行化。配合CMD可只使用参数。
>
> 类似于 CMD 指令，但其不会被 docker run 的命令行参数指定的指令所覆盖，而且这些命令行参数会被当作参数送给 ENTRYPOINT 指令指定的程序。

如果运行 docker run 时使用了 --entrypoint 选项，将覆盖 CMD 指令指定的程序。

```shell
格式：
ENTRYPOINT ["<executeable>","<param1>","<param2>",...]
ENTRYPOINT command param1 param2 (shell内部命令)

示例：
FROM nginx

ENTRYPOINT ["nginx", "-c"] # 定参
CMD ["/etc/nginx/nginx.conf"] # 变参 

注：
ENTRYPOINT与CMD非常类似，不同的是通过docker run执行的命令不会覆盖ENTRYPOINT，而docker run命令中指定的任何参数，都会被当做参数再次传递给ENTRYPOINT。
Dockerfile中只允许有一个ENTRYPOINT命令，多指定时会覆盖前面的设置，而只执行最后的ENTRYPOINT指令。
```

```shell
# 1.不传参运行
docker run nginx:test
# 容器内会默认运行以下命令，启动主进程。
nginx -c /etc/nginx/nginx.conf

# 2.传参运行
docker run nginx:test -c /etc/nginx/new.conf
# 容器内会默认运行以下命令，启动主进程(/etc/nginx/new.conf:假设容器内已有此文件)
nginx -c /etc/nginx/new.conf
```

### LABEL

> 用于为镜像添加元数据

```shell
格式：
LABEL <key>=<value> <key>=<value> ...

示例：
LABEL version="1.0" description="测试描述" by="作者"

注：
使用LABEL指定元数据时，一条LABEL可以指定一个或多个元数据，指定多条元数据时，不同元数据之间通过空格分隔。
推荐将所有的元数据通过一条LABEL指令指定，一面生成过多的中间镜像。
```

### ENV

> 设置环境变量，定义了环境变量，那么在后续的指令中，就可以使用这个环境变量。

```shell
格式：
ENV <key> <value>	#<key>之后的所有内容均会被视为其<value>的组成部分，因此，一次只能设置一个变量
ENV <key1>=<value1> <key2>=<value2> ... #可以设置多个变量，每个变量为一个"<key>=<value>"的键值对，如果<key>中包含空格，可以使用\来进行转义，也可以通过""来进行标示；另外，反斜线也可以用于续行

示例：
ENV MYPATH /usr/local # 配置环境变量的目录

示例：设置 NODE_VERSION = 7.2.0 ， 在后续的指令中可以通过 $NODE_VERSION 引用：
ENV NODE_VERSION 7.2.0

RUN curl -SLO "https://nodejs.org/dist/v$NODE_VERSION/node-v$NODE_VERSION-linux-x64.tar.xz" \
  && curl -SLO "https://nodejs.org/dist/v$NODE_VERSION/SHASUMS256.txt.asc"
```

### EXPOSE

> 指定与外界交互的端口

```shell
格式：
EXPOSE <port> [<port>...]

示例：
EXPOSE 80 443
EXPOSE 8080
EXPOSE 11211/tcp 11211/udp

注：
EXPOSE并不会让容器的端口访问到主机，要使其可访问，需要在docker run运行容器时通过-p来发布这些端口，或通过-P参数来发布EXPOSE导出的所有端口。
```

### VOLUME

> 定义匿名数据卷。在启动容器时忘记挂载数据卷，会自动挂载到匿名卷。

- 避免重要的数据，因容器重启而丢失，这是非常致命的。
- 避免容器不断变大。

```shell
格式：
VOLUME ["<路径1>", "<路径2>"...]
VOLUME <路径>

示例：
VOLUME ["/data"]
VOLUME ["/var/www", "/var/log/apache2", "/etc/apache2"

注：
在启动容器 docker run 的时候，我们可以通过 -v 参数修改挂载点。
一个卷可以存在于一个或多个容器的指定目录，该目录可以绕过联合文件系统，并具有以下功能：
1.卷可以容器间共享和重用
2.容器并不一定要和其它容器共享卷
3.修改卷后会立即生效
4.对卷的修改不会对镜像产生影响
5.卷会一直存在，直到没有任何容器在使用它
```

### WORKDIR

> 指定工作目录。用 WORKDIR 指定的工作目录，会在构建镜像的每一层中都存在。（WORKDIR 指定的工作目录，必须是提前创建好的）。
>
> docker build 构建镜像过程中的，每一个 RUN 命令都是新建的一层。只有通过 WORKDIR 创建的目录才会一直存在。

```shell
格式：
WORKDIR <工作目录路径>

示例：
WORKDIR /a		# 这时工作目录为：/a
WORKDIR b			# 这时工作目录为：/a/b
WORKDIR c			# 这时工作目录为：/a/b/c

注：
通过WORKDIR设置工作目录后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT、ADD、COPY等命令都会在该目录下执行。在使用docker run运行容器时，可以通过-w参数覆盖构建时所设置的工作目录。
```

### USER

> 用于指定执行后续命令的用户和用户组，这边只是切换后续命令执行的用户（用户和用户组必须提前已经存在）。

```shell
格式：
USER <用户名>[:<用户组>]

示例：
USER www

注：
使用USER指定用户后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT都将使用该用户。镜像构建完成后，通过docker run运行容器时，可以通过-u参数来覆盖所指定的用户。
```

### ARG

> 构建参数，与 ENV 作用一至。不过作用域不一样。ARG 设置的环境变量仅对 Dockerfile 内有效，也就是说只有 docker build 的过程中有效，构建好的镜像内不存在此环境变量。

```shell
格式：
ARG <name>[=<default vlaue>]

示例：
ARG site
ARG build_user=www

注：
构建命令 docker build 中可以用 --build-arg <参数名>=<值> 来覆盖。
```

### ONBUILD

> 用于设置镜像触发器

```shell
格式：
ONBUILD [INSTRUCTION]

示例：
ONBUILD ADD . /app/src
ONBUILD RUN /usr/local/bin/python-build --dir /app/src

注：
当所构建的镜像被用做其它镜像的基础镜像，该镜像中的触发器将会被钥触发
```

## 创建一个自定义的centos

```shell
# 编写Dockerfile
vim Dockerfile

FROM centos # 指定基础镜像
MAINTAINER xxx<xxx@163.com> # 作者信息

ENV MYPATH /usr/local # 定义环境变量
WORKDIR $MYPATH # 指定工作目录，用$的方式取ENV

# 构建容器时，所需要执行的命令
RUN yum install -y vim 
RUN yum install -y net-tools

EXPOSE 80 # 暴露的端口

# 容器启动时 docker run，需要执行的命令
CMD echo $MYPATH
CMD echo "-----end-----"
CMD /bin/bash

# 测试
[root@localhost centos]# docker build -t mycentos:1.0 . # 最后的 . 代表本次执行的上下文路径
# 如果文件名不是Dockerfile，可以通过-f来指定
[root@localhost centos]# docker build -t -f mydockerfile mycentos:1.0 . # mydockerfile在当前路径
...
Successfully built 416e8baf3232
Successfully tagged mycentos:1.0
[root@localhost centos]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
mycentos     1.0       416e8baf3232   9 seconds ago   291MB
centos       latest    300e315adb2f   4 months ago    209MB
[root@localhost centos]# docker run -it mycentos:1.0
[root@ca6ce3f28716 local]# pwd # 进入容器，直接就到了工作路径 /usr/local
/usr/local
[root@ca6ce3f28716 local]# ifconfig # ifconfig命令可用
eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 172.17.0.2  netmask 255.255.0.0  broadcast 172.17.255.255
        ether 02:42:ac:11:00:02  txqueuelen 0  (Ethernet)
        RX packets 6  bytes 516 (516.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
# 变更历史
[root@localhost centos]# docker history 416e8baf3232
IMAGE          CREATED         CREATED BY                                      SIZE      COMMENT
416e8baf3232   7 minutes ago   /bin/sh -c #(nop)  CMD ["/bin/sh" "-c" "/bin…   0B        
5d04af994bf0   7 minutes ago   /bin/sh -c #(nop)  CMD ["/bin/sh" "-c" "echo…   0B        
533ffc5c6547   7 minutes ago   /bin/sh -c #(nop)  CMD ["/bin/sh" "-c" "echo…   0B        
341c7fdb2b0f   7 minutes ago   /bin/sh -c #(nop)  EXPOSE 80                    0B        
73213213bd76   7 minutes ago   /bin/sh -c yum install -y net-tools             23.3MB    
5113342e320b   7 minutes ago   /bin/sh -c yum install -y vim                   58MB      
ba6dfd9d5835   7 minutes ago   /bin/sh -c #(nop) WORKDIR /usr/local            0B        
2afc1ab02156   7 minutes ago   /bin/sh -c #(nop)  ENV MYPATH=/usr/local        0B        
825dcad5be17   7 minutes ago   /bin/sh -c #(nop)  MAINTAINER liu<lius420@16…   0B        
300e315adb2f   4 months ago    /bin/sh -c #(nop)  CMD ["/bin/bash"]            0B        
<missing>      4 months ago    /bin/sh -c #(nop)  LABEL org.label-schema.sc…   0B        
<missing>      4 months ago    /bin/sh -c #(nop) ADD file:bd7a2aed6ede423b7…   209MB
```

## 测试CMD

```shell
# 编写Dockerfile
[root@localhost centos]# cat Dockerfile 
FROM centos
MAINTAINER xxx<xxx@163.com>

CMD ["ls", "-a"] # 容器启动后将执行 ls -a

# 构建
[root@localhost centos]# docker build -t mycentos:1.0 .
Sending build context to Docker daemon  2.048kB
Step 1/3 : FROM centos
 ---> 300e315adb2f
Step 2/3 : MAINTAINER liu<lius420@163.com>
 ---> Running in dcc229df5f2f
Removing intermediate container dcc229df5f2f
 ---> 726b95b44367
Step 3/3 : CMD ["ls", "-a"]
 ---> Running in da3a819a04c9
Removing intermediate container da3a819a04c9
 ---> ed9e42faee3f
Successfully built ed9e42faee3f
Successfully tagged mycentos:1.0

# 运行
[root@localhost centos]# docker run mycentos:1.0
.
..
.dockerenv
bin
dev
etc
home
lib
lib64
lost+found
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var
# 可以看到，运行之后就执行了 ls -a命令

# 如果想追加一个命令  -l 成为ls -al：展示列表详细数据
[root@localhost centos]# docker run mycentos:1.0 -l
docker: Error response from daemon: OCI runtime create failed: container_linux.go:367: starting container process caused: exec: "-l": executable file not found in $PATH: unknown.
ERRO[0000] error waiting for container: context canceled
# cmd的情况下 -l 替换了CMD["ls","-l"] 而 -l  不是命令所以报错
```

## 测试ENTRYPOINT

```shell
# 编写Dockerfile
[root@localhost centos]# cat Dockerfile 
FROM centos
MAINTAINER liu<lius420@163.com>

ENTRYPOINT ["ls", "-a"]

# 构建镜像
[root@localhost centos]# docker build -t mycentos:1.0 .

# 运行镜像
[root@localhost centos]# docker run mycentos:1.0
.
..
.dockerenv
bin
dev
etc
home
lib
lib64
lost+found
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var
# 我们的命令，是直接拼接在我们得ENTRYPOINT命令后面的
[root@localhost centos]# docker run mycentos:1.0 -l
total 0
drwxr-xr-x.   1 root root   6 Apr 27 07:17 .
drwxr-xr-x.   1 root root   6 Apr 27 07:17 ..
-rwxr-xr-x.   1 root root   0 Apr 27 07:17 .dockerenv
lrwxrwxrwx.   1 root root   7 Nov  3 15:22 bin -> usr/bin
drwxr-xr-x.   5 root root 340 Apr 27 07:17 dev
drwxr-xr-x.   1 root root  66 Apr 27 07:17 etc
drwxr-xr-x.   2 root root   6 Nov  3 15:22 home
lrwxrwxrwx.   1 root root   7 Nov  3 15:22 lib -> usr/lib
lrwxrwxrwx.   1 root root   9 Nov  3 15:22 lib64 -> usr/lib64
drwx------.   2 root root   6 Dec  4 17:37 lost+found
drwxr-xr-x.   2 root root   6 Nov  3 15:22 media
drwxr-xr-x.   2 root root   6 Nov  3 15:22 mnt
drwxr-xr-x.   2 root root   6 Nov  3 15:22 opt
dr-xr-xr-x. 121 root root   0 Apr 27 07:17 proc
dr-xr-x---.   2 root root 162 Dec  4 17:37 root
drwxr-xr-x.  11 root root 163 Dec  4 17:37 run
lrwxrwxrwx.   1 root root   8 Nov  3 15:22 sbin -> usr/sbin
drwxr-xr-x.   2 root root   6 Nov  3 15:22 srv
dr-xr-xr-x.  13 root root   0 Apr 27 06:52 sys
drwxrwxrwt.   7 root root 145 Dec  4 17:37 tmp
drwxr-xr-x.  12 root root 144 Dec  4 17:37 usr
drwxr-xr-x.  20 root root 262 Dec  4 17:37 var
```

## 编写Tomcat镜像

首先准备好tomcat和jdk到当前目录

```shell
[root@localhost tomcat]# cat Dockerfile 
FROM centos			# 基础镜像centos
MAINTAINER xxx<xxx@163.com> # 作者

COPY README /usr/local/README # 负责README文件
ADD jdk-8u191-linux-x64.tar.gz /usr/local # 添加jdk，ADD命令会自动解压
ADD apache-tomcat-9.0.45.tar.gz /usr/local # 添加tomcat

RUN yum install -y vim # 安装vim
ENV MYPATH /usr/local # 环境变量设置工作目录
WORKDIR $MYPATH # 设置工作目录

ENV JAVA_HOME /usr/local/jdk1.8.0_191 # 环境变量：JAVA_HOME
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar 

ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.45 # 环境变量：tomcat
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.45

# 设置PATH环境变量 : 是分隔符
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin 

EXPOSE 8080 # 设置暴露的端口

CMD /usr/local/apache-tomcat-9.0.45/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.45/logs/catalina.out # 设置启动后执行的命令

# 构建
[root@localhost tomcat]# docker build -t mytomcat:1.0 .

# 运行镜像
[root@localhost tomcat]# docker run -p 8080:8080 -d -v /home/tomcat/test:/usr/local/apache-tomcat-9.0.45/webapps/test -v /home/tomcat/tomcatlogs:/usr/local/apache-tomcat-9.0.45/logs  --name tomcat01 mytomcat:1.0
25a6840964ba3c78f37fcbeb1f6156ef12e0b62655b128f845f39ad36392bcb0

# 测试
[root@localhost tomcat]# docker exec -it 25a6840964ba3c78f37fcbeb1f6156ef12e0b62655b128f845f39ad36392bcb0 /bin/bash
[root@25a6840964ba local]# curl http://localhost:8080
```

## 发布镜像

1. 发布到DockerHub 

   `docker push 容器名`

2. 发布到云服务器

   官网有教程

# Docker网络

```shell
[root@localhost tomcat]# ip addr
# 本级回环地址
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
# 内网地址
2: enp0s3: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:0a:25:1f brd ff:ff:ff:ff:ff:ff
    inet 192.168.31.37/24 brd 192.168.31.255 scope global noprefixroute dynamic enp0s3
       valid_lft 40178sec preferred_lft 40178sec
    inet6 fe80::ebe7:c288:c484:a011/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
# docker0地址
3: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
    link/ether 02:42:88:9a:e4:bb brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
    inet6 fe80::42:88ff:fe9a:e4bb/64 scope link 
       valid_lft forever preferred_lft forever
```

这里有三个网络

## docker是如何处理容器网络访问的？

```shell
# 测试，运行一个tomcat
[root@localhost tomcat]# docker run -d -P --name tomcat01 tomcat:9.0.45
5cb8a312e628d954554aadeb3dad880791ae2521fa7aa7bedd10428be83a56a6
[root@localhost tomcat]# docker exec -it 5cb8a312e628d954554aadeb3dad880791ae2521fa7aa7bedd10428be83a56a6 ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
24: eth0@if25: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever

# linux可以ping通容器内部
[root@localhost tomcat]# ping 172.17.0.2
PING 172.17.0.2 (172.17.0.2) 56(84) bytes of data.
64 bytes from 172.17.0.2: icmp_seq=1 ttl=64 time=0.044 ms
64 bytes from 172.17.0.2: icmp_seq=2 ttl=64 time=0.031 ms
64 bytes from 172.17.0.2: icmp_seq=3 ttl=64 time=0.032 ms

# 容器内部也可以ping通linux
[root@localhost tomcat]# docker exec -it 5cb8a312e628d954554aadeb3dad880791ae2521fa7aa7bedd10428be83a56a6 /bin/bash
root@5cb8a312e628:/usr/local/tomcat# ping 192.168.31.37
PING 192.168.31.37 (192.168.31.37) 56(84) bytes of data.
64 bytes from 192.168.31.37: icmp_seq=1 ttl=64 time=0.089 ms
64 bytes from 192.168.31.37: icmp_seq=2 ttl=64 time=0.038 ms
64 bytes from 192.168.31.37: icmp_seq=3 ttl=64 time=0.032 ms
```

**原理**

我们每启动一个docker容器，docker就会给docker容器分配一个ip，我们只要安装了docker，就会有一个docker0桥接模式，使用的技术是**veth-pair**技术。

https://www.cnblogs.com/bakari/p/10613710.html

```shell
# 再启动一个tomcat（目前启动了两个容器），可以发现多出了两对网络
[root@localhost tomcat]# docker ps 
CONTAINER ID   IMAGE           COMMAND             CREATED              STATUS              PORTS                                         NAMES
a6debd18313a   tomcat:9.0.45   "catalina.sh run"   About a minute ago   Up About a minute   0.0.0.0:49155->8080/tcp, :::49155->8080/tcp   tomcat02
5cb8a312e628   tomcat:9.0.45   "catalina.sh run"   10 minutes ago       Up 10 minutes       0.0.0.0:49154->8080/tcp, :::49154->8080/tcp   tomcat01
[root@localhost ~]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: enp0s3: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:0a:25:1f brd ff:ff:ff:ff:ff:ff
    inet 192.168.31.37/24 brd 192.168.31.255 scope global noprefixroute dynamic enp0s3
       valid_lft 38204sec preferred_lft 38204sec
    inet6 fe80::ebe7:c288:c484:a011/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
3: docker0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:88:9a:e4:bb brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
    inet6 fe80::42:88ff:fe9a:e4bb/64 scope link 
       valid_lft forever preferred_lft forever
# 多出的两对网络
25: veth500cbc9@if24: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue master docker0 state UP group default 
    link/ether 32:ad:a7:a1:8a:68 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet6 fe80::30ad:a7ff:fea1:8a68/64 scope link 
       valid_lft forever preferred_lft forever
27: veth155bd11@if26: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue master docker0 state UP group default 
    link/ether 46:b6:d2:3c:ac:7d brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet6 fe80::44b6:d2ff:fe3c:ac7d/64 scope link 
       valid_lft forever preferred_lft forever
```

容器的网卡都是成对出现的
veth-pair 就是一对的虚拟设备接口，他们都是成对出现的，一端连着协议，一端彼此相连
正因为有这个特性 veth-pair 充当一个桥梁，连接各种虚拟网络设备的
OpenStac,Docker容器之间的连接，OVS的连接，都是使用evth-pair技术

> 测试tomcat01和tomcat02是否可以ping通？

```shell
# tomcat01
[root@localhost tomcat]# docker exec -it 5cb8a312e628 /bin/bash
root@5cb8a312e628:/usr/local/tomcat# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
24: eth0@if25: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
root@5cb8a312e628:/usr/local/tomcat# ping 172.17.0.3
PING 172.17.0.3 (172.17.0.3) 56(84) bytes of data.
64 bytes from 172.17.0.3: icmp_seq=1 ttl=64 time=0.055 ms
64 bytes from 172.17.0.3: icmp_seq=2 ttl=64 time=0.036 ms

# tomcat02
root@a6debd18313a:/usr/local/tomcat# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
26: eth0@if27: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:03 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.3/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
root@a6debd18313a:/usr/local/tomcat# ping 172.17.0.2
PING 172.17.0.2 (172.17.0.2) 56(84) bytes of data.
64 bytes from 172.17.0.2: icmp_seq=1 ttl=64 time=0.029 ms
64 bytes from 172.17.0.2: icmp_seq=2 ttl=64 time=0.041 ms
```

所以容器之间是可以互相ping通的

## docker网络模型图

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker%E7%BD%91%E7%BB%9C01.png)

结论：tomcat01和tomcat02共用一个路由器（docker0）

所有的容器不指定网络的情况下，都是docker0路由的，docker会给我们的容器分配一个默认可用的ip。

Docker使用的是Linux的桥接模式，宿主机是一个Docker容器的网桥docker0

![](https://cdn.jsdelivr.net/gh/mrlsss/images//Docker/docker%E7%BD%91%E7%BB%9C02.png)

Docker中所有网络接口都是虚拟的，虚拟的转发效率高（内网传递文件）

只要容器删除，对应的网桥一对就没了。

> 我们编写了一个微服务，database url=ip: 项目不重启，数据库ip换了，我们希望可以处理这个问题，可以通过名字来进行访问容器？

## --link

```shell
# 测试，直接通过容器名称来ping

[root@localhost tomcat]# docker exec -it tomcat02 ping tomcat01
ping: tomcat01: No address associated with hostname # 无法ping通

# 运行一个tomcat01 --link tomcat02
[root@localhost tomcat]# docker run -d -P --name tomcat03 --link tomcat02 tomcat:9.0.45
24988d101183d9741b328ca783563b55cdd00507bc09d5e6aa3a8d3be85fe4d9
[root@localhost tomcat]# docker exec -it tomcat03 ping tomcat02
PING tomcat02 (172.17.0.2) 56(84) bytes of data. # 通过容器名称可以ping通
64 bytes from tomcat02 (172.17.0.2): icmp_seq=1 ttl=64 time=0.049 ms
64 bytes from tomcat02 (172.17.0.2): icmp_seq=2 ttl=64 time=0.035 ms
# 通过 docker run --link 运行的容器，可以通过容器名称ping通

# 而tomcat02是不能ping通tomcat03的
[root@localhost tomcat]# docker exec -it tomcat02 ping tomcat03
ping: tomcat03: No address associated with hostname

# 原因
[root@localhost tomcat]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
2c71cb1bfda1   bridge    bridge    local
de3443baca54   host      host      local
c2d53c2daf47   none      null      local
[root@localhost tomcat]# docker network inspect 2c71cb1bfda1
"Containers": {
    "24988d101183d9741b328ca783563b55cdd00507bc09d5e6aa3a8d3be85fe4d9": {
    "Name": "tomcat03",
    "EndpointID": "18d51f7830237f414604f605e3696c676228031cfc79f10ac44deb9d7f7770ca",
    "MacAddress": "02:42:ac:11:00:04",
    "IPv4Address": "172.17.0.4/16",
    "IPv6Address": ""
  },
    "34db2a3e0cd18845ac747e5166fcfd0c299c8a5a90502c5b71a1e10a2af1b759": {
    "Name": "tomcat02",
    "EndpointID": "c18e26a0cd2a2041e599a4766f79d4804552a6cfa006390a47503eef451c5d6a",
    "MacAddress": "02:42:ac:11:00:02",
    "IPv4Address": "172.17.0.2/16",
    "IPv6Address": ""
  },
    "fb676056629edbaed02e2fb135371df5dc58f905d2214591fe761437b1221525": {
    "Name": "tomcat01",
    "EndpointID": "d19b58032ce234d41a8ea904e2d7e198af312b3a3a6a54ad3c5e9ebb3ac46a69",
    "MacAddress": "02:42:ac:11:00:03",
    "IPv4Address": "172.17.0.3/16",
    "IPv6Address": ""
  }
},
3个tomcat的网段都相同

[root@localhost tomcat]# docker inspect tomcat03
"Links": [
	"/tomcat02:/tomcat03/tomcat02"
],
# 查看tomcat03的hosts文件，可以看到其中配置的有tomcat02的地址
[root@localhost tomcat]# docker exec tomcat03 cat /etc/hosts
127.0.0.1       localhost
::1     localhost ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters
172.17.0.2      tomcat02 34db2a3e0cd1
172.17.0.4      24988d101183
# 所以 --link本质就是在hosts配置中添加了对应的映射
```

## 自定义网络

```shell
docker network
connect     -- Connect a container to a network
create      -- Creates a new network with a name specified by the
disconnect  -- Disconnects a container from a network
inspect     -- Displays detailed information on a network
ls          -- Lists all the networks created by the user
prune       -- Remove all unused networks
rm          -- Deletes one or more networks
```

查看docker所有的网络

```shell
[root@localhost tomcat]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
2c71cb1bfda1   bridge    bridge    local
de3443baca54   host      host      local
c2d53c2daf47   none      null      local
```

### 网络模式

- bridge：桥接 docker（默认，自己创建也是用bridge）
- none：不配置网络，一般不用
- host：和宿主机共享网络
- container：容器网络连通（用得少）

测试

```shell
# 我们直接启动的命令 --net bridge，这个就是我们的docker0
# bridge就是docker0
docker run -d -P --name tomcat01 tomcat 等价于 => docker run -d -P --name tomcat01 --net bridge tomcat
```

docker0特点：默认，域名不能访问，可以通过 --link连接，但是很麻烦。

自定义一个网络

```shell
[root@localhost tomcat]# docker network create --help
Options:
      --attachable           Enable manual container attachment
      --aux-address map      Auxiliary IPv4 or IPv6 addresses used by Network driver (default map[])
      --config-from string   The network from which to copy the configuration
      --config-only          Create a configuration only network
  -d, --driver string        Driver to manage the Network (default "bridge")
      --gateway strings      IPv4 or IPv6 Gateway for the master subnet
      --ingress              Create swarm routing-mesh network
      --internal             Restrict external access to the network
      --ip-range strings     Allocate container ip from a sub-range
      --ipam-driver string   IP Address Management Driver (default "default")
      --ipam-opt map         Set IPAM driver specific options (default map[])
      --ipv6                 Enable IPv6 networking
      --label list           Set metadata on a network
  -o, --opt map              Set driver specific options (default map[])
      --scope string         Control the network's scope
      --subnet strings       Subnet in CIDR format that represents a network segment
[root@localhost tomcat]# docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 mynet
746cfea596fdbe1f55ade529b2de69304b2dd4dd00015a04ef2be4f8237c3e0c
[root@localhost tomcat]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
2c71cb1bfda1   bridge    bridge    local
de3443baca54   host      host      local
746cfea596fd   mynet     bridge    local
c2d53c2daf47   none      null      local
[root@localhost tomcat]# docker network inspect mynet
[
    {
        "Name": "mynet",
        "Id": "746cfea596fdbe1f55ade529b2de69304b2dd4dd00015a04ef2be4f8237c3e0c",
        "Created": "2021-04-27T17:01:58.010998098+08:00",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "192.168.0.0/16",
                    "Gateway": "192.168.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {},
        "Options": {},
        "Labels": {}
    }
]
# 再启动两个tomcat
[root@localhost tomcat]# docker run -d --name tomcat01 -P --net mynet tomcat:9.0.45
659251c752e04be8698cec7ad431ad1aa1713ca9b3d8975272db5e9bf8ea093e
[root@localhost tomcat]# docker run -d --name tomcat02 -P --net mynet tomcat:9.0.45
0c7ab3348e2b9a66f7427511a36e7f9a29c2a214bfdcc5c427653548717a1411
[root@localhost tomcat]# docker network inspect mynet
[
    {
        "Name": "mynet",
        "Id": "746cfea596fdbe1f55ade529b2de69304b2dd4dd00015a04ef2be4f8237c3e0c",
        "Created": "2021-04-27T17:01:58.010998098+08:00",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "192.168.0.0/16",
                    "Gateway": "192.168.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "0c7ab3348e2b9a66f7427511a36e7f9a29c2a214bfdcc5c427653548717a1411": {
                "Name": "tomcat02",
                "EndpointID": "f14a13e913587d7c7c9c1d1c15df41cd56ce90fbd3709917ebbad59356eaa0f2",
                "MacAddress": "02:42:c0:a8:00:03",
                "IPv4Address": "192.168.0.3/16",
                "IPv6Address": ""
            },
            "659251c752e04be8698cec7ad431ad1aa1713ca9b3d8975272db5e9bf8ea093e": {
                "Name": "tomcat01",
                "EndpointID": "cbfe7444778a95307ad7f56c208884de6b2a30557f5ab33bfc48781d729917cc",
                "MacAddress": "02:42:c0:a8:00:02",
                "IPv4Address": "192.168.0.2/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]

# 在自定义网络下，服务可以互相ping通，不用使用--link
[root@localhost tomcat]# docker exec tomcat01 ping tomcat02
PING tomcat02 (192.168.0.3) 56(84) bytes of data.
64 bytes from tomcat02.mynet (192.168.0.3): icmp_seq=1 ttl=64 time=0.038 ms
64 bytes from tomcat02.mynet (192.168.0.3): icmp_seq=2 ttl=64 time=0.068 ms
[root@localhost tomcat]# docker exec tomcat02 ping tomcat01
PING tomcat01 (192.168.0.2) 56(84) bytes of data.
64 bytes from tomcat01.mynet (192.168.0.2): icmp_seq=1 ttl=64 time=0.021 ms
64 bytes from tomcat01.mynet (192.168.0.2): icmp_seq=2 ttl=64 time=0.051 ms
```

我们自定义的网络，docker为我们维护好了对应的关系，推荐平时这样使用网络。

好处：

- redis：不同的集群使用不同的网络，保证集群是安全和健康的

- mysql：不同的集群使用不同的网络，保证集群是安全和健康的

## 网络连通

```shell
docker network connect
# 测试两个不同的网络连通，再启动两个tomcat 使用默认网络，即docker0
[root@localhost tomcat]# docker run -d -P --name tomcat01 tomcat
[root@localhost tomcat]# docker run -d -P --name tomcat02 tomcat
# 此时无法ping通

# 要将tomcat01 连通 tomcat—net-01，就是把tomcat01加到mynet网络
# 一个容器两个ip
[root@localhost tomcat]# docker run -d -P --name tomcat01 tomcat:9.0.45
0324eed45de0939080ae99b8589a7ef1fd3b1751045113f2651239f946538c37
[root@localhost tomcat]# docker network connect mynet tomcat01
[root@localhost tomcat]# docker run -d -P --name tomcat-net-01 --net mynet tomcat:9.0.45
e91dfe8d7466ae43de7fb679284e4cbc79d8e5282f99beb3c8d2592d9be75a6b
[root@localhost tomcat]# docker exec tomcat-net-01 ping tomcat01
PING tomcat01 (192.168.0.2) 56(84) bytes of data.
64 bytes from tomcat01.mynet (192.168.0.2): icmp_seq=1 ttl=64 time=0.035 ms
64 bytes from tomcat01.mynet (192.168.0.2): icmp_seq=2 ttl=64 time=0.070 ms
[root@localhost tomcat]# docker exec tomcat01 ping tomcat-net-01
PING tomcat-net-01 (192.168.0.3) 56(84) bytes of data.
64 bytes from tomcat-net-01.mynet (192.168.0.3): icmp_seq=1 ttl=64 time=0.021 ms
64 bytes from tomcat-net-01.mynet (192.168.0.3): icmp_seq=2 ttl=64 time=0.119 ms
# 将tomcat01连通后，tomcat01和tomcat-net-01可以相互ping通
# 如果不连通 docker network connect ，则无法ping通
```

假设要跨网络操作别人，就需要使用docker network connect 连通！

# 部署Redis集群

通过shell脚本创建6个redis

```shell
for port in $(seq 1 6);\
do \
mkdir -p /mydata/redis/node-${port}/conf
touch /mydata/redis/node-${port}/conf/redis.conf
cat << EOF >> /mydata/redis/node-${port}/conf/redis.conf
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 192.168.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
done
```

通过脚本运行6个redis

```shell
for port in $(seq 1 6);\
do \
docker run -p 637${port}:6379 -p 1667${port}:16379 --name redis-${port} \
-v /mydata/redis/node-${port}/data:/data \
-v /mydata/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 192.168.0.1${port} redis:6.0.10 redis-server /etc/redis/redis.conf
done


```

构建集群

```shell
docker exec -it redis-1 /bin/sh #redis默认没有bash
redis-cli --cluster create 192.168.0.11:6379 192.168.0.12:6379 192.168.0.13:6379 192.168.0.14:6379 192.168.0.15:6379 192.168.0.16:6379  --cluster-replicas 1
```

