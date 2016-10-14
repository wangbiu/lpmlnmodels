# lpmlnmodels 推理机参数
```lpmlnmodels <params> <lpmln file>```
 
+ `marginal` 可选，计算边缘概率分布，该参数不指定时，计算最大可能稳定模型
+ `showall` 可选，显示全部稳定模型
+ `asp clingo | dlv` 必选，指定使用的ASP推理机
+ `transtype strong | weak` 可选，指定翻译的类型，默认是强翻译
+ `transout <filename>` 可选，将翻译结果输出到指定文件中

# LPMLN Experiments 
+ Bird 
+ Friend 

Files with suffix `origin` are original LPMLN programs, 
files with suffix `clingo` are ASP programs in Clingo syntax,
files with suffix `dlv` are ASP programs in Clingo syntax.

# Version
+ Clingo 4.3.0
+ DLV  build Dec 17 2012

# Command line
+ clingo 0 --opt-mode enum \<file\>
+ dlv -costbound=_,_ \<file\>

# Note
Syntax about weak constraint is slightly different between Clingo and DLV, 
but the clingo syntax is standard ASP syntax.

