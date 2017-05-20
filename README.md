# FxyEasy
基于RxJava + Retrofit Android基础框架

基本思路都来源于此项目XShow。根据自己的需求稍作喜修改。感谢Xshowz作者.

注：该框架引用了日志系统和公共工具库，这两个库都很轻量级，
具体使用详情可分别参考https://github.com/xiaoyaoyou1212/ViseLog
和https://github.com/xiaoyaoyou1212/ViseUtils。

使用方法:

allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
	compile 'com.github.MiyuShiroki:FxyEasy:v1.0'
	}
